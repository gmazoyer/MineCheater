package fr.respawner.minecheater.worker;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.packet.Packet;

public final class PacketProcessor extends Thread {
    private static final Logger log;

    private final Queue<Packet> packets;

    private long processedPackets;
    private long chunksReceived;

    private boolean running;

    static {
        log = Logger.getLogger(PacketProcessor.class);
    }

    public PacketProcessor() {
        this.packets = new LinkedList<>();
        this.processedPackets = 0;
        this.chunksReceived = 0;
        this.running = true;
    }

    private synchronized boolean canProcess() {
        return (this.packets.size() > 0);
    }

    private synchronized void processPacket() throws IOException {
        Packet packet;
        Packet response;

        /*
         * Get the packet to process.
         */
        packet = packets.poll();

        /*
         * Should not happen.
         */
        if (packet == null) {
            return;
        }

        /*
         * Actual processing.
         */
        switch (packet.getID()) {
        case (byte) 0x33:
            this.chunksReceived++;
        case (byte) 0x00:
        case (byte) 0x01:
        case (byte) 0x02:
        case (byte) 0x03:
        case (byte) 0x04:
        case (byte) 0x05:
        case (byte) 0x06:
        case (byte) 0x08:
        case (byte) 0x09:
        case (byte) 0x0D:
        case (byte) 0x11:
        case (byte) 0x12:
        case (byte) 0x14:
        case (byte) 0x15:
        case (byte) 0x16:
        case (byte) 0x17:
        case (byte) 0x18:
        case (byte) 0x19:
        case (byte) 0x1A:
        case (byte) 0x1C:
        case (byte) 0x1D:
        case (byte) 0x1E:
        case (byte) 0x1F:
        case (byte) 0x20:
        case (byte) 0x21:
        case (byte) 0x22:
        case (byte) 0x23:
        case (byte) 0x26:
        case (byte) 0x28:
        case (byte) 0x29:
        case (byte) 0x2A:
        case (byte) 0x2B:
        case (byte) 0x32:
        case (byte) 0x34:
        case (byte) 0x35:
        case (byte) 0x36:
        case (byte) 0x3C:
        case (byte) 0x3D:
        case (byte) 0x46:
        case (byte) 0x47:
        case (byte) 0x67:
        case (byte) 0x68:
        case (byte) 0xC8:
        case (byte) 0xC9:
        case (byte) 0xFE:
        case (byte) 0xFF:
            break;
        default:
            log.warn("Unknown packet " + String.format("%02X", packet.getID())
                    + "!");
            return;
        }

        /*
         * Extract the data from the packet.
         */
        packet.process();

        log.debug("Received: " + packet);

        /*
         * Get the packet we need to send to respond.
         */
        response = packet.response();
        if (response != null) {
            response.process();
            response.write();

            log.debug("Sent: " + response);
        }

        /*
         * Increment the number of processed packets.
         */
        this.processedPackets++;
    }

    public synchronized void addPacketToQueue(Packet packet) {
        boolean inserted;

        inserted = false;
        while (!inserted) {
            /*
             * Until the packet is in the queue, try to insert it.
             */
            inserted = this.packets.offer(packet);
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    public void stopProcessor() {
        this.running = false;
    }

    @Override
    public void run() {
        /*
         * Process to all the packets even if the thread has to end.
         */
        while (this.running || !this.packets.isEmpty()) {
            /*
             * Test if there is at least one packet to process.
             */
            if (this.canProcess()) {
                try {
                    this.processPacket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("Received " + this.chunksReceived + " chunks.");
        log.info("Processed " + this.processedPackets + " packets.");
        log.info("Shutting down packet processor.");
    }
}

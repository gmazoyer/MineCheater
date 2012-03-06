package fr.respawner.minecheater.worker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.MinecraftClient;
import fr.respawner.minecheater.World;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.Packet.PacketAction;
import fr.respawner.minecheater.packet.clientpacket.Player;
import fr.respawner.minecheater.packet.clientpacket.PlayerLook;
import fr.respawner.minecheater.packet.clientpacket.PlayerPosition;
import fr.respawner.minecheater.packet.clientpacket.ServerListPing;
import fr.respawner.minecheater.packet.common.ChatMessage;
import fr.respawner.minecheater.packet.common.DisconnectKick;
import fr.respawner.minecheater.packet.common.Handshake;
import fr.respawner.minecheater.packet.common.KeepAlive;
import fr.respawner.minecheater.packet.common.LoginRequest;
import fr.respawner.minecheater.packet.common.PlayerPositionAndLook;
import fr.respawner.minecheater.packet.common.Respawn;
import fr.respawner.minecheater.packet.serverpacket.AddObjectVehicle;
import fr.respawner.minecheater.packet.serverpacket.Animation;
import fr.respawner.minecheater.packet.serverpacket.BlockAction;
import fr.respawner.minecheater.packet.serverpacket.BlockChange;
import fr.respawner.minecheater.packet.serverpacket.CollectItem;
import fr.respawner.minecheater.packet.serverpacket.DestroyEntity;
import fr.respawner.minecheater.packet.serverpacket.Entity;
import fr.respawner.minecheater.packet.serverpacket.EntityEffect;
import fr.respawner.minecheater.packet.serverpacket.EntityEquipment;
import fr.respawner.minecheater.packet.serverpacket.EntityHeadLook;
import fr.respawner.minecheater.packet.serverpacket.EntityLook;
import fr.respawner.minecheater.packet.serverpacket.EntityLookAndRelativeMove;
import fr.respawner.minecheater.packet.serverpacket.EntityMetadata;
import fr.respawner.minecheater.packet.serverpacket.EntityPainting;
import fr.respawner.minecheater.packet.serverpacket.EntityRelativeMove;
import fr.respawner.minecheater.packet.serverpacket.EntityStatus;
import fr.respawner.minecheater.packet.serverpacket.EntityTeleport;
import fr.respawner.minecheater.packet.serverpacket.EntityVelocity;
import fr.respawner.minecheater.packet.serverpacket.Experience;
import fr.respawner.minecheater.packet.serverpacket.ExperienceOrb;
import fr.respawner.minecheater.packet.serverpacket.Explosion;
import fr.respawner.minecheater.packet.serverpacket.IncrementStatistic;
import fr.respawner.minecheater.packet.serverpacket.MapChunk;
import fr.respawner.minecheater.packet.serverpacket.MobSpawn;
import fr.respawner.minecheater.packet.serverpacket.MultiBlockChange;
import fr.respawner.minecheater.packet.serverpacket.NamedEntitySpawn;
import fr.respawner.minecheater.packet.serverpacket.NewOrInvalidState;
import fr.respawner.minecheater.packet.serverpacket.PickupSpawn;
import fr.respawner.minecheater.packet.serverpacket.PlayerListItem;
import fr.respawner.minecheater.packet.serverpacket.PreChunk;
import fr.respawner.minecheater.packet.serverpacket.RemoveEntityEffect;
import fr.respawner.minecheater.packet.serverpacket.SetSlot;
import fr.respawner.minecheater.packet.serverpacket.SoundParticleEffect;
import fr.respawner.minecheater.packet.serverpacket.SpawnPosition;
import fr.respawner.minecheater.packet.serverpacket.Thunderbold;
import fr.respawner.minecheater.packet.serverpacket.TimeUpdate;
import fr.respawner.minecheater.packet.serverpacket.UpdateHealth;
import fr.respawner.minecheater.packet.serverpacket.UseBed;
import fr.respawner.minecheater.packet.serverpacket.WindowItems;

public final class PacketsHandler extends Thread implements IHandler {
    private static final Logger log;
    private static final PrintStream stdout;

    private MinecraftClient client;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private long receivedPackets;
    private boolean running;
    private World world;

    static {
        log = Logger.getLogger(PacketsHandler.class);
        stdout = System.out;
    }

    public PacketsHandler(MinecraftClient client) {
        this.client = client;
        this.receivedPackets = 0;
        this.running = true;
        this.world = new World();
    }

    /**
     * Get a complete packet from its ID. This method is only used for packets
     * that are sent from the server to the client.
     */
    private Packet packetFromID(byte id) throws IOException {
        Packet packet;

        packet = null;

        switch (id) {
        case (byte) 0x00:
            packet = new KeepAlive(this);
            break;

        case (byte) 0x01:
            packet = new LoginRequest(this);
            break;

        case (byte) 0x02:
            packet = new Handshake(this);
            break;

        case (byte) 0x03:
            packet = new ChatMessage(this);
            break;

        case (byte) 0x04:
            packet = new TimeUpdate(this);
            break;

        case (byte) 0x05:
            packet = new EntityEquipment(this);
            break;

        case (byte) 0x06:
            packet = new SpawnPosition(this);
            break;

        case (byte) 0x08:
            packet = new UpdateHealth(this);
            break;

        case (byte) 0x09:
            packet = new Respawn(this);
            break;

        case (byte) 0x0D:
            packet = new PlayerPositionAndLook(this);
            break;

        case (byte) 0x11:
            packet = new UseBed(this);
            break;

        case (byte) 0x12:
            packet = new Animation(this);
            break;

        case (byte) 0x14:
            packet = new NamedEntitySpawn(this);
            break;

        case (byte) 0x15:
            packet = new PickupSpawn(this);
            break;

        case (byte) 0x16:
            packet = new CollectItem(this);
            break;

        case (byte) 0x17:
            packet = new AddObjectVehicle(this);
            break;

        case (byte) 0x18:
            packet = new MobSpawn(this);
            break;

        case (byte) 0x19:
            packet = new EntityPainting(this);
            break;

        case (byte) 0x1A:
            packet = new ExperienceOrb(this);
            break;

        case (byte) 0x1C:
            packet = new EntityVelocity(this);
            break;

        case (byte) 0x1D:
            packet = new DestroyEntity(this);
            break;

        case (byte) 0x1E:
            packet = new Entity(this);
            break;

        case (byte) 0x1F:
            packet = new EntityRelativeMove(this);
            break;

        case (byte) 0x20:
            packet = new EntityLook(this);
            break;

        case (byte) 0x21:
            packet = new EntityLookAndRelativeMove(this);
            break;

        case (byte) 0x22:
            packet = new EntityTeleport(this);
            break;

        case (byte) 0x23:
            packet = new EntityHeadLook(this);
            break;

        case (byte) 0x26:
            packet = new EntityStatus(this);
            break;

        case (byte) 0x28:
            packet = new EntityMetadata(this);
            break;

        case (byte) 0x29:
            packet = new EntityEffect(this);
            break;

        case (byte) 0x2A:
            packet = new RemoveEntityEffect(this);
            break;

        case (byte) 0x2B:
            packet = new Experience(this);
            break;

        case (byte) 0x32:
            packet = new PreChunk(this);
            break;

        case (byte) 0x33:
            packet = new MapChunk(this);
            break;

        case (byte) 0x34:
            packet = new MultiBlockChange(this);
            break;

        case (byte) 0x35:
            packet = new BlockChange(this);
            break;

        case (byte) 0x36:
            packet = new BlockAction(this);
            break;

        case (byte) 0x3C:
            packet = new Explosion(this);
            break;

        case (byte) 0x3D:
            packet = new SoundParticleEffect(this);
            break;

        case (byte) 0x46:
            packet = new NewOrInvalidState(this);
            break;

        case (byte) 0x47:
            packet = new Thunderbold(this);
            break;

        case (byte) 0x67:
            packet = new SetSlot(this);
            break;

        case (byte) 0x68:
            packet = new WindowItems(this);
            break;

        case (byte) 0xC8:
            packet = new IncrementStatistic(this);
            break;

        case (byte) 0xC9:
            packet = new PlayerListItem(this);
            break;

        case (byte) 0xFF:
            packet = new DisconnectKick(this);
            break;

        default:
            log.warn("Unknown packet with ID " + String.format("%02X", id)
                    + " ignored.");
            break;
        }

        if (packet != null) {
            packet.read();
            packet.process();

            log.debug("Received: " + packet);

            this.receivedPackets++;
        }

        return packet;
    }

    /**
     * Respond to a packet if we need to.
     */
    private void respondToPacket(Packet packet) throws IOException {
        final Packet response;

        response = packet.response();

        if (response != null) {
            response.setAction(PacketAction.WRITING);
            response.write();

            log.debug("Sent: " + response);
        }
    }

    @Override
    public DataInputStream getInput() {
        return this.in;
    }

    @Override
    public DataOutputStream getOutput() {
        return this.out;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void stopHandler() {
        this.running = false;
    }

    public void sendPacket(byte id, Object... args) {
        Packet packet;

        packet = null;

        switch (id) {
        case (byte) 0x02:
            packet = new Handshake(this);
            break;

        case (byte) 0x03:
            if ((args.length < 1) || !(args[0] instanceof String)) {
                log.warn("Packet 0x03 needs a string in parameters.");
            } else {
                packet = new ChatMessage(this, (String) args[0]);
            }
            break;

        case (byte) 0x09:
            packet = new Respawn(this);
            break;

        case (byte) 0x0A:
            if ((args.length < 1) || !(args[0] instanceof Boolean)) {
                packet = new Player(this);
            } else {
                packet = new Player(this, (boolean) args[0]);
            }
            break;

        case (byte) 0x0B:
            packet = new PlayerPosition(this);
            break;

        case (byte) 0x0C:
            packet = new PlayerLook(this);
            break;

        case (byte) 0x0D:
            if ((args.length < 1) || !(args[0] instanceof Boolean)) {
                log.warn("Packet 0x0D needs a boolean in parameters.");
            } else {
                packet = new PlayerPositionAndLook(this, (boolean) args[0]);
            }
            break;

        case (byte) 0xFE:
            packet = new ServerListPing(this);
            break;

        case (byte) 0xFF:
            packet = new DisconnectKick(this);
            try {
                packet.setAction(PacketAction.WRITING);
                packet.write();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.stopHandler();
            packet = null;
            break;

        default:
            log.error("Can't send packet " + String.format("%02X", id));
            break;
        }

        if (packet != null) {
            packet.setAction(PacketAction.WRITING);
            try {
                packet.write();
                log.debug("Sent: " + packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void println(Object... messages) {
        if (messages.length == 0) {
            stdout.println();
        } else {
            for (Object message : messages) {
                stdout.println(message);
            }
        }
    }

    @Override
    public void run() {
        final String ip;
        final int port;
        final Timer timer;

        InetAddress address;
        int readByte;
        byte packetID;

        ip = this.client.getIP();
        port = this.client.getPort();
        address = null;

        try {
            /*
             * Translate the given string to a usable address.
             */
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            log.warn("Can't find server at " + ip + ".");
        }

        try {
            /*
             * Try to open the connection with the server.
             */
            this.socket = new Socket(address, port);
            this.socket.setTcpNoDelay(true);
        } catch (IOException e) {
            log.warn("Can't connect to server at " + ip + " with port " + port
                    + ".");
        }

        try {
            /*
             * Open the IO channels to be able to communicate.
             */
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * Start the communication by sending a handshake.
         */
        this.sendPacket((byte) 0x02);

        timer = new Timer();
        timer.scheduleAtFixedRate(new AutoPacketSender(), 0, 50);

        while (this.running) {
            Packet packet;

            packet = null;

            try {
                /*
                 * Get a first byte that should be the packet ID.
                 */
                readByte = this.in.read();
                if (readByte == -1) {
                    throw new IOException(
                            "The stream has been closed by the server.");
                }

                /*
                 * Get the ID of the packet.
                 */
                packetID = (byte) readByte;

                /*
                 * Get the packet and read it from the network.
                 */
                packet = this.packetFromID(packetID);
                if (packet != null) {
                    this.respondToPacket(packet);
                }
            } catch (IOException e) {
                log.error("Can't read packet from the network.");
                e.printStackTrace();

                packet = null;

                timer.cancel();
                timer.purge();

                this.stopHandler();
            }
        }

        /*
         * Stop the automatic sending of packets.
         */
        timer.cancel();
        timer.purge();

        /*
         * Close the streams.
         */
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            log.error("Streams already closed?");
        }

        log.info("Received and processed " + this.receivedPackets + " packets.");
        log.info("Shutting down packets handler.");
    }

    private class AutoPacketSender extends TimerTask {
        @Override
        public void run() {
            final PacketsHandler handler;

            handler = PacketsHandler.this;

            /*
             * We need to wait for the login to complete before sending packets.
             */
            if (!handler.world.isLoggedIn()) {
                return;
            }

            /*
             * Send packets containing our position regularly.
             */
            handler.sendPacket((byte) 0x0A, true);
            handler.sendPacket((byte) 0x0D, false);
        }
    }
}

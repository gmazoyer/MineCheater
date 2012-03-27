/*
 * Copyright (c) 2012 Guillaume Mazoyer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.respawner.minecheater.worker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.MinecraftClient;
import fr.respawner.minecheater.World;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.Packet.PacketAction;
import fr.respawner.minecheater.packet.common.ChatMessage;
import fr.respawner.minecheater.packet.common.DisconnectKick;
import fr.respawner.minecheater.packet.common.Handshake;
import fr.respawner.minecheater.packet.common.KeepAlive;
import fr.respawner.minecheater.packet.common.LoginRequest;
import fr.respawner.minecheater.packet.common.PlayerAbilities;
import fr.respawner.minecheater.packet.common.PlayerPositionAndLook;
import fr.respawner.minecheater.packet.common.PluginMessage;
import fr.respawner.minecheater.packet.common.Respawn;
import fr.respawner.minecheater.packet.common.UpdateSign;
import fr.respawner.minecheater.packet.serverpacket.Animation;
import fr.respawner.minecheater.packet.serverpacket.BlockAction;
import fr.respawner.minecheater.packet.serverpacket.BlockChange;
import fr.respawner.minecheater.packet.serverpacket.ChangeGameState;
import fr.respawner.minecheater.packet.serverpacket.CollectItem;
import fr.respawner.minecheater.packet.serverpacket.DestroyEntity;
import fr.respawner.minecheater.packet.serverpacket.Entity;
import fr.respawner.minecheater.packet.serverpacket.EntityEffect;
import fr.respawner.minecheater.packet.serverpacket.EntityEquipment;
import fr.respawner.minecheater.packet.serverpacket.EntityHeadLook;
import fr.respawner.minecheater.packet.serverpacket.EntityLook;
import fr.respawner.minecheater.packet.serverpacket.EntityLookAndRelativeMove;
import fr.respawner.minecheater.packet.serverpacket.EntityMetadata;
import fr.respawner.minecheater.packet.serverpacket.EntityRelativeMove;
import fr.respawner.minecheater.packet.serverpacket.EntityStatus;
import fr.respawner.minecheater.packet.serverpacket.EntityTeleport;
import fr.respawner.minecheater.packet.serverpacket.EntityVelocity;
import fr.respawner.minecheater.packet.serverpacket.Explosion;
import fr.respawner.minecheater.packet.serverpacket.IncrementStatistic;
import fr.respawner.minecheater.packet.serverpacket.MapChunks;
import fr.respawner.minecheater.packet.serverpacket.MapColumnAllocation;
import fr.respawner.minecheater.packet.serverpacket.MultiBlockChange;
import fr.respawner.minecheater.packet.serverpacket.PlayerListItem;
import fr.respawner.minecheater.packet.serverpacket.RemoveEntityEffect;
import fr.respawner.minecheater.packet.serverpacket.SetExperience;
import fr.respawner.minecheater.packet.serverpacket.SetSlot;
import fr.respawner.minecheater.packet.serverpacket.SetWindowItems;
import fr.respawner.minecheater.packet.serverpacket.SoundParticleEffect;
import fr.respawner.minecheater.packet.serverpacket.SpawnDroppedItem;
import fr.respawner.minecheater.packet.serverpacket.SpawnExperienceOrb;
import fr.respawner.minecheater.packet.serverpacket.SpawnMob;
import fr.respawner.minecheater.packet.serverpacket.SpawnNamedEntity;
import fr.respawner.minecheater.packet.serverpacket.SpawnObjectVehicle;
import fr.respawner.minecheater.packet.serverpacket.SpawnPainting;
import fr.respawner.minecheater.packet.serverpacket.SpawnPosition;
import fr.respawner.minecheater.packet.serverpacket.Thunderbold;
import fr.respawner.minecheater.packet.serverpacket.TimeUpdate;
import fr.respawner.minecheater.packet.serverpacket.UpdateHealth;
import fr.respawner.minecheater.packet.serverpacket.UpdateTileEntity;
import fr.respawner.minecheater.packet.serverpacket.UseBed;
import fr.respawner.minecheater.worker.TickClock.ClockReceiver;

public final class PacketsHandler extends Thread implements IHandler,
        ClockReceiver {
    private static final Logger log;
    private static final PrintStream stdout;

    private MinecraftClient client;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private long ticks;
    private long receivedPackets;
    private boolean running;
    private World world;

    static {
        log = Logger.getLogger(PacketsHandler.class);
        stdout = System.out;
    }

    public PacketsHandler(MinecraftClient client) {
        this.client = client;
        this.ticks = 0;
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
        case Packet.KEEP_ALIVE:
            packet = new KeepAlive(this);
            break;

        case Packet.LOGIN_REQUEST:
            packet = new LoginRequest(this);
            break;

        case Packet.HANDSHAKE:
            packet = new Handshake(this);
            break;

        case Packet.CHAT_MESSAGE:
            packet = new ChatMessage(this);
            break;

        case Packet.TIME_UPDATE:
            packet = new TimeUpdate(this);
            break;

        case Packet.ENTITY_EQUIPMENT:
            packet = new EntityEquipment(this);
            break;

        case Packet.SPAWN_POSITION:
            packet = new SpawnPosition(this);
            break;

        case Packet.UPDATE_HEALTH:
            packet = new UpdateHealth(this);
            break;

        case Packet.RESPAWN:
            packet = new Respawn(this);
            break;

        case Packet.PLAYER_POSITION_AND_LOOK:
            packet = new PlayerPositionAndLook(this);
            break;

        case Packet.USE_BED:
            packet = new UseBed(this);
            break;

        case Packet.ANIMATION:
            packet = new Animation(this);
            break;

        case Packet.SPAWN_NAMED_ENTITY:
            packet = new SpawnNamedEntity(this);
            break;

        case Packet.SPAWN_DROPPED_ITEM:
            packet = new SpawnDroppedItem(this);
            break;

        case Packet.COLLECT_ITEM:
            packet = new CollectItem(this);
            break;

        case Packet.SPAWN_OBJECT_VEHICLE:
            packet = new SpawnObjectVehicle(this);
            break;

        case Packet.SPAWN_MOB:
            packet = new SpawnMob(this);
            break;

        case Packet.SPAWN_PAINTING:
            packet = new SpawnPainting(this);
            break;

        case Packet.SPAWN_EXPERIENCE_ORB:
            packet = new SpawnExperienceOrb(this);
            break;

        case Packet.ENTITY_VELOCITY:
            packet = new EntityVelocity(this);
            break;

        case Packet.DESTROY_ENTITY:
            packet = new DestroyEntity(this);
            break;

        case Packet.ENTITY:
            packet = new Entity(this);
            break;

        case Packet.ENTITY_RELATIVE_MOVE:
            packet = new EntityRelativeMove(this);
            break;

        case Packet.ENTITY_LOOK:
            packet = new EntityLook(this);
            break;

        case Packet.ENTITY_LOOK_AND_RELATIVE_MOVE:
            packet = new EntityLookAndRelativeMove(this);
            break;

        case Packet.ENTITY_TELEPORT:
            packet = new EntityTeleport(this);
            break;

        case Packet.ENTITY_HEAD_LOOK:
            packet = new EntityHeadLook(this);
            break;

        case Packet.ENTITY_STATUS:
            packet = new EntityStatus(this);
            break;

        case Packet.ENTITY_METADATA:
            packet = new EntityMetadata(this);
            break;

        case Packet.ENTITY_EFFECT:
            packet = new EntityEffect(this);
            break;

        case Packet.REMOVE_ENTITY_EFFECT:
            packet = new RemoveEntityEffect(this);
            break;

        case Packet.SET_EXPERIENCE:
            packet = new SetExperience(this);
            break;

        case Packet.MAP_COLUMN_ALLOCATION:
            packet = new MapColumnAllocation(this);
            break;

        case Packet.MAP_CHUNKS:
            packet = new MapChunks(this);
            break;

        case Packet.MULTI_BLOCK_CHANGE:
            packet = new MultiBlockChange(this);
            break;

        case Packet.BLOCK_CHANGE:
            packet = new BlockChange(this);
            break;

        case Packet.BLOCK_ACTION:
            packet = new BlockAction(this);
            break;

        case Packet.EXPLOSION:
            packet = new Explosion(this);
            break;

        case Packet.SOUND_PARTICLE_EFFECT:
            packet = new SoundParticleEffect(this);
            break;

        case Packet.CHANGE_GAME_STATE:
            packet = new ChangeGameState(this);
            break;

        case Packet.THUNDERBOLT:
            packet = new Thunderbold(this);
            break;

        case Packet.SET_SLOT:
            packet = new SetSlot(this);
            break;

        case Packet.SET_WINDOW_ITEMS:
            packet = new SetWindowItems(this);
            break;

        case Packet.UPDATE_SIGN:
            packet = new UpdateSign(this);
            break;

        case Packet.UPDATE_TILE_ENTITY:
            packet = new UpdateTileEntity(this);
            break;

        case Packet.INCREMENT_STATISTIC:
            packet = new IncrementStatistic(this);
            break;

        case Packet.PLAYER_LIST_ITEM:
            packet = new PlayerListItem(this);
            break;

        case Packet.PLAYER_ABILITIES:
            packet = new PlayerAbilities(this);
            break;

        case Packet.PLUGIN_MESSAGE:
            packet = new PluginMessage(this);
            break;

        case Packet.DISCONNECT_KICK:
            packet = new DisconnectKick(this);
            break;

        default:
            log.warn("Unknown packet with ID " + String.format("0x%02X", id)
                    + " ignored.");
            break;
        }

        if (packet != null) {
            packet.read();
            packet.process();

            log.debug(packet);

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
            response.send();

            log.debug(response);
        }
    }

    public void sendPacket(byte id, Object... args) {
        Packet packet;

        packet = null;

        switch (id) {
        case Packet.HANDSHAKE:
            packet = new Handshake(this);
            break;

        case Packet.CHAT_MESSAGE:
            if ((args.length < 1) || !(args[0] instanceof String)) {
                log.warn("Packet 0x03 needs a string in parameters.");
            } else {
                packet = new ChatMessage(this, (String) args[0]);
            }
            break;

        case Packet.RESPAWN:
            packet = new Respawn(this);
            break;

        case Packet.PLAYER_POSITION_AND_LOOK:
            packet = new PlayerPositionAndLook(this);
            break;

        case Packet.DISCONNECT_KICK:
            packet = new DisconnectKick(this);
            break;

        default:
            log.error("Can't send packet " + String.format("0x%02X", id));
            break;
        }

        if (packet != null) {
            packet.setAction(PacketAction.WRITING);

            try {
                packet.send();
                packet.free();

                log.debug(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        /*
         * Tell the server that we leave.
         */
        this.world.setLoggedIn(false);
        this.sendPacket(Packet.DISCONNECT_KICK);

        this.running = false;
    }

    @Override
    public void tick() {
        this.ticks++;

        /*
         * We need to wait for the login to complete before sending packets.
         */
        if (!this.world.isLoggedIn()) {
            return;
        }

        this.sendPacket((byte) 0x0D);
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
        final TickClock clock;

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

            this.running = false;

            return;
        }

        try {
            /*
             * Try to open the connection with the server.
             */
            this.socket = new Socket(address, port);
            this.socket.setTcpNoDelay(true);
        } catch (IOException e) {
            log.warn("Can't connect to server at " + ip + " on port " + port
                    + ".");

            this.running = false;

            return;
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
        this.sendPacket(Packet.HANDSHAKE);

        /*
         * Listen for ticks.
         */
        clock = new TickClock();
        clock.addReceiver(this);
        clock.start();

        while (this.running) {
            Packet packet;

            packet = null;

            try {
                /*
                 * Get a first byte that should be the packet ID.
                 */
                readByte = this.in.read();
                if (readByte == -1) {
                    log.warn("The connection has been closed by the server.");
                    break;
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
                    packet.free();
                }
            } catch (IOException e) {
                log.error("Can't read packet from the network.");
                e.printStackTrace();

                packet = null;
                this.running = false;
            }
        }

        /*
         * Stop the listening of ticks.
         */
        clock.removeReceiver(this);
        clock.stop();

        try {
            /*
             * Read and drop data until the server closes the connection.
             */
            while (this.in.read() != -1) {
                /*
                 * This is what we called 'busy waiting / spinning'. It's
                 * generally bad to do so but here it looks like the only
                 * solution.
                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * Since the connection is closed, release our objects.
         */
        try {
            this.socket.shutdownInput();
            this.socket.shutdownOutput();

            this.in.close();
            this.out.close();

            this.socket.close();
        } catch (IOException e) {
            log.error("Streams already closed?");
        }

        log.info("Run during " + this.ticks + " ticks.");
        log.info("Received and processed " + this.receivedPackets + " packets.");
        log.info("Shutting down packets handler.");
    }
}

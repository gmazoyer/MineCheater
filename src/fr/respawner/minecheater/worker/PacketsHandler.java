package fr.respawner.minecheater.worker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import fr.respawner.minecheater.World;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.clientpacket.Player;
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
import fr.respawner.minecheater.packet.serverpacket.WindowItems;

public final class PacketsHandler extends Thread {
	private static final PrintStream stdout;

	private final PacketProcessor processor;

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private boolean running;
	private World world;

	static {
		stdout = System.out;
	}

	public PacketsHandler(Socket socket) {
		this.socket = socket;
		this.in = null;
		this.out = null;
		this.processor = new PacketProcessor();
		this.running = true;
		this.world = new World();

		/*
		 * Get input and output streams.
		 */
		try {
			this.in = new DataInputStream(this.socket.getInputStream());
			this.out = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			stdout.println("Can't get IO channels to communicate over the network.");
		}
	}

	/**
	 * Get a complete packet from its ID. This method is only used for packets
	 * that are sent from the server to the client.
	 */
	private Packet packetFromID(byte id) {
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
			stdout.println("Unknown packet with ID " + String.format("%x", id)
					+ " ignored.");
			break;
		}

		return packet;
	}

	public DataInputStream getInput() {
		return this.in;
	}

	public DataOutputStream getOutput() {
		return this.out;
	}

	public World getWorld() {
		return this.world;
	}

	public boolean isRunning() {
		return this.running;
	}

	public void stopHandler() {
		this.running = false;
	}

	public void println(Object message) {
		stdout.println(message);
	}

	public void sendPacket(byte id, Object... args) {
		Packet packet;

		packet = null;

		switch (id) {
		case (byte) 0x03:
			if (args.length != 1) {
				stdout.println("Packet 0x03 needs a string in parameters.");
			} else {
				packet = new ChatMessage(this);
				((ChatMessage) packet).setMessage((String) args[0]);
			}
			break;

		case (byte) 0x09:
			packet = new Respawn(this);
			break;

		case (byte) 0x0A:
			packet = new Player(this);
			break;

		case (byte) 0xFF:
			packet = new DisconnectKick(this);
			try {
				packet.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.processor.stopProcessor();
			this.stopHandler();
			packet = null;
			break;

		default:
			stdout.println("Can't send packet " + String.format("%x", id));
			break;
		}

		if (packet != null) {
			try {
				packet.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		final ServerListPing ping;
		final DisconnectKick pong;
		final String[] serverInfos;
		final Timer timer;

		byte packetID;

		this.processor.start();

		try {
			/*
			 * First we send a ping.
			 */
			ping = new ServerListPing(this);
			ping.write();

			/*
			 * And we get the pong.
			 */
			packetID = (byte) this.in.read();
			pong = new DisconnectKick(this);
			pong.read();

			/*
			 * Display infos.
			 */
			serverInfos = ((String) pong.getData()).split("§");
			stdout.println();
			stdout.println("  ** Server name:        " + serverInfos[0]);
			stdout.println("  ** Number of players:  " + serverInfos[1]);
			stdout.println("  ** Maximum of players: " + serverInfos[2]);
			stdout.println();

			/*
			 * Since the connection is closed, release our objects.
			 */
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			stdout.println("Can't ping the server.");
		}

		try {
			/*
			 * Open a new socket since the other one was closed. Also open the
			 * new IO channels to be able to communicate.
			 */
			this.socket = new Socket(this.socket.getInetAddress(),
					this.socket.getPort());
			this.in = new DataInputStream(this.socket.getInputStream());
			this.out = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			/*
			 * Start the communication by sending a handshake.
			 */
			new Handshake(this).write();
		} catch (IOException e) {
			stdout.println("Can't send handshake.");
		}

		timer = new Timer();
		timer.scheduleAtFixedRate(new AutoPacketSender(), 1000, 6000);

		while (this.running) {
			Packet packet;

			packet = null;

			try {
				/*
				 * Get the ID of the packet.
				 */
				packetID = (byte) this.in.read();

				/*
				 * Get the packet and read it from the network.
				 */
				packet = this.packetFromID(packetID);
				if (packet != null) {
					packet.read();
				}
			} catch (IOException e) {
				stdout.println("Can't read packet from the network.");
				e.printStackTrace();

				packet = null;

				timer.cancel();
				timer.purge();

				this.processor.stopProcessor();
				this.stopHandler();
			}

			if (packet != null) {
				/*
				 * Put the packet in the processing queue.
				 */
				this.processor.addPacketToQueue(packet);

				if (packet.getID() == (byte) 0xFF) {
					this.stopHandler();
				}
			}
		}

		/*
		 * Stop the automatic sending of packets.
		 */
		timer.cancel();
		timer.purge();

		/*
		 * Stop the processor if it wasn't already done.
		 */
		if (this.processor.isRunning()) {
			this.processor.stopProcessor();
		}

		try {
			/*
			 * Wait for the processor to stop.
			 */
			this.processor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * Close the streams.
		 */
		try {
			this.in.close();
			this.out.close();
		} catch (IOException e) {
			stdout.println("Streams already closed?");
		}

		stdout.println("Shutting down packets handler.");
	}

	private class AutoPacketSender extends TimerTask {
		@Override
		public void run() {
			PacketsHandler.this.sendPacket((byte) 0x0A);
		}
	}
}

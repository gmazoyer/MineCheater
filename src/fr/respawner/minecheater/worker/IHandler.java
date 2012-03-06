package fr.respawner.minecheater.worker;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import fr.respawner.minecheater.World;

public interface IHandler {
    public void println(Object... messages);

    public DataInputStream getInput();

    public DataOutputStream getOutput();

    public World getWorld();

    public void stopHandler();
}

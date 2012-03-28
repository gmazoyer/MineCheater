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
package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.web.MCLogin;
import fr.respawner.minecheater.worker.IHandler;

public final class Handshake extends Packet {
    private String usernameAndHostOrHash;

    public Handshake(IHandler handler) {
        super(handler, HANDSHAKE);

        this.usernameAndHostOrHash = String.format("%s;%s:%d", Config.USERNAME,
                Config.SERVER_HOST, Config.SERVER_PORT);
    }

    @Override
    public void read() throws IOException {
        this.usernameAndHostOrHash = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.writeUnicodeString(this.usernameAndHostOrHash);
    }

    @Override
    public void process() {
        final MCLogin login;

        if (!this.usernameAndHostOrHash.equals("-")) {
            login = this.handler.getClient().getLogin();

            /*
             * Login failed.
             */
            if (!login.connectToServer(this.usernameAndHostOrHash)) {
                this.handler.stopHandler();
            }
        }
    }

    @Override
    public Packet response() {
        return new LoginRequest(this.handler);
    }

    @Override
    public String getDataAsString() {
        return (this.usernameAndHostOrHash.equals("-") ? "Name authentication disabled."
                : this.usernameAndHostOrHash);
    }
}

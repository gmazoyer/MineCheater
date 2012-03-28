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
package fr.respawner.minecheater.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;

public final class MCLogin {
    private static final Logger log;

    private boolean loggedIn;
    private String sessionID;
    private String error;

    static {
        log = Logger.getLogger(MCLogin.class);
    }

    private static final String post(String target, String parameters) {
        final StringBuilder builder;
        final URL url;
        final HttpURLConnection connection;
        final DataOutputStream output;
        final BufferedReader reader;
        String line;

        builder = new StringBuilder();

        try {
            url = new URL(target);

            /*
             * Open a connection with the correct protocol.
             */
            if (url.getProtocol().equals("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            /*
             * Set connection properties.
             */
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",
                    Integer.toString(parameters.length()));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            /*
             * Connection.
             */
            connection.connect();

            /*
             * Send parameters.
             */
            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(parameters);
            output.close();

            /*
             * Read the response.
             */
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(Packet.LINE_SEPARATOR);
            }

            reader.close();

            /*
             * Disconnect.
             */
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ((builder.length() == 0) ? null : builder.toString().trim());
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public String getError() {
        return this.error;
    }

    public boolean doLogin() {
        final boolean success;
        final String response;
        String parameters;

        try {
            /*
             * Build the parameters for the request.
             */
            parameters = "user=" + URLEncoder.encode(Config.USERNAME, "UTF-8")
                    + "&password="
                    + URLEncoder.encode(Config.PASSWORD, "UTF-8") + "&version="
                    + Config.CLIENT_VERSION;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            parameters = "";
        }

        /*
         * Try to authenticate.
         */
        response = post("https://login.minecraft.net/", parameters);

        log.debug("Received response from login = " + response);

        if (response == null) {
            /*
             * Failed.
             */
            success = false;
        } else {
            if (!response.contains(":")) {
                /*
                 * Failed, wrong login, password, etc...
                 */
                success = false;
                this.error = response;
            } else {
                /*
                 * We are in.
                 */
                success = true;
                this.sessionID = response.split(":")[3];
            }
        }

        this.loggedIn = success;

        return success;
    }

    public boolean connectToServer(String serverID) {
        final boolean success;
        final String response;
        String parameters;

        try {
            /*
             * Build the parameters for the request.
             */
            parameters = "user=" + URLEncoder.encode(Config.USERNAME, "UTF-8")
                    + "&sessionId="
                    + URLEncoder.encode(this.sessionID, "UTF-8") + "&serverId="
                    + URLEncoder.encode(serverID, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            parameters = "";
        }

        /*
         * Try to authenticate.
         */
        response = post("http://session.minecraft.net/game/joinserver.jsp",
                parameters);

        log.debug("Received response from login = " + response);

        if (response == null) {
            /*
             * Failed.
             */
            success = false;
        } else {
            if (response.equals("OK")) {
                /*
                 * We are in.
                 */
                success = true;
            } else {
                /*
                 * Failed, wrong login, password, etc...
                 */
                success = false;
                this.error = response;
            }
        }

        return success;
    }
}

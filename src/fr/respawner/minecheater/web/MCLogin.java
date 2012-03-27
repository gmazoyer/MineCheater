package fr.respawner.minecheater.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
        final HttpsURLConnection connection;
        final DataOutputStream output;
        final BufferedReader reader;
        String line;

        builder = new StringBuilder();

        try {
            url = new URL(target);

            /*
             * Set connection properties.
             */
            connection = (HttpsURLConnection) url.openConnection();
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

        return ((builder.length() == 0) ? null : builder.toString());
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

        log.debug("Received response from login = "
                + ((response != null) ? response.trim() : null));

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
                this.sessionID = response.split(":")[3].trim();
            }
        }

        this.loggedIn = success;

        return success;
    }
}

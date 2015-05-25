import jexxus.client.ClientConnection;
import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.server.ServerConnection;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Elias on 5/25/2015.
 */
public class Main {
    public static void main(String[] args) {
        ClientConnection conn = new ClientConnection(new ConnectionListener() {
            @Override
            public void connectionBroken(Connection broken, boolean forced) {

            }

            @Override
            public void receive(byte[] data, Connection from) {
                JOptionPane.showMessageDialog(null, new String(data));
            }

            @Override
            public void clientConnected(ServerConnection conn) {

            }
        }, "92.220.161.232", 1999, true);
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

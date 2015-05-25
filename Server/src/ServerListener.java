import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.server.ServerConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 5/25/2015.
 */
public class ServerListener implements ConnectionListener{

    List<ConnectionListenerClass> list = new ArrayList<ConnectionListenerClass>();

    public void connectionBroken(Connection broken, boolean forced) {

    }

    public void receive(byte[] data, Connection from) {

    }

    public void clientConnected(ServerConnection conn) {
        Console.println("Client connected on IP: "+conn.getIP());

    }
}

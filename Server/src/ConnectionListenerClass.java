import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.server.ServerConnection;

import java.io.Serializable;

/**
 * Created by Elias on 5/25/2015.
 */
public class ConnectionListenerClass implements ConnectionListener{

    public String name;
    public ServerConnection conn;

    @Override
    public void connectionBroken(Connection broken, boolean forced) {
        
    }

    @Override
    public void receive(byte[] data, Connection from) {
    }

    @Override
    public void clientConnected(ServerConnection conn) {

    }
} class Message implements Serializable{
    public final long serialVersionUID = 1L;
    Object obj[];
}

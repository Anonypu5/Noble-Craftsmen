import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.server.ServerConnection;

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
        name = new String (data);
        Console.println("Assigning ip \""+conn.getIP()+"\" the name \""+name+"\"");
    }

    @Override
    public void clientConnected(ServerConnection conn) {

    }
}

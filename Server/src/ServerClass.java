import jexxus.server.Server;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Elias on 5/25/2015.
 */
public class ServerClass implements Runnable{

    public static boolean running;
    public static Server server;

    public void run() {
        if(!running){
            running = true;
            Console.println("Starting server on port: " + Save.port);
            server = new Server(new ServerListener(), Save.port, true);
            server.startServer();
        }else{
            Console.printErr("Server already running");
        }
    }

    public static void stop() {
        if(running){
            server.shutdown(true);
            ServerListener.list = new ArrayList<ConnectionListenerClass>();
            Console.println("Server shut down");
            running = false;
        }else{
            Console.printErr("Server not running");
        }
    }
}

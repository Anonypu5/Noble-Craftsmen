import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.common.Delivery;
import jexxus.server.Server;
import jexxus.server.ServerConnection;

import java.io.*;

/**
 * Created by Elias on 5/25/2015.
 */
public class ConnectionListenerClass implements ConnectionListener{

    public String name;
    public ServerConnection conn;
    public boolean loggedIn;

    @Override
    public void connectionBroken(Connection broken, boolean forced) {
        for(int i = 0; i < ServerListener.list.size(); i++){
            if(ServerListener.list.get(i).equals(this)){
                Console.println("Player (" + i + ", " + ServerListener.list.get(i).conn.getIP() + ", " + (ServerListener.list.get(i).loggedIn ? ServerListener.list.get(i).name : "not logged in")+") left the server");
                ServerListener.list.remove(i);
            }
        }
    }

    @Override
    public void receive(byte[] data, Connection from) {
        Console.println("Received message from ip \""+conn.getIP()+"\"");
        try {
            ByteArrayInputStream b = new ByteArrayInputStream(data);
            ObjectInputStream o = new ObjectInputStream(b);
            Message message = (Message) o.readObject();
            if (!loggedIn) {
                if(message.title.equals("login")){
                    String usr = (String)message.obj[0][0];
                    String psw = (String)message.obj[0][1];
                    if(usr.toLowerCase().equals("username") && psw.toLowerCase().equals("password")){
                        send("loginAnsw", new Object[][]{{1}});
                        loggedIn = true;
                        Console.println("ip \""+conn.getIP()+"\" logged in as \""+usr+"\"");
                        name = usr;
                    }else{
                        Console.println("ip \""+conn.getIP()+"\" tried to login as \""+usr+"\", but failed");
                        send("loginAnsw", new Object[][]{{0}});
                    }
                }else{
                    Console.printErr("Received \""+message.title +"\" when expected login from ip \""+conn.getIP()+"\"");
                }
            }
        }catch(Exception e){
            Console.printStackTrace(e);
        }
    }

    @Override
    public void clientConnected(ServerConnection conn) {

    }
    
    public void send(String title, Object obj[][]){
        try {
            Message message = new Message();
            message.obj = obj;
            message.title = title;
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(message);
            byte[] bytes = b.toByteArray();
            conn.send(bytes, Delivery.RELIABLE);
        }catch(Exception e){

        }
    }
} class Message implements Serializable {
    public final long serialVersionUID = 1L;
    String title;
    Object obj[][];
}

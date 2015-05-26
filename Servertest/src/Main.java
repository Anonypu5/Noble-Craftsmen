import jexxus.client.ClientConnection;
import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.common.Delivery;
import jexxus.server.ServerConnection;

import javax.swing.*;
import java.io.*;

/**
 * Created by Elias on 5/25/2015.
 */
public class Main {

    public static boolean loggedIn;
    public static ServerConnection conn = null;

    public static void main(String[] args) {
        ClientConnection conn = new ClientConnection(new ConnectionListener() {

            @Override
            public void connectionBroken(Connection broken, boolean forced) {

            }

            @Override
            public void receive(byte[] data, Connection from) {
                try{

                    ByteArrayInputStream b = new ByteArrayInputStream(data);
                    ObjectInputStream o = new ObjectInputStream(b);
                    Message message = (Message) o.readObject();
                    if (!loggedIn) {
                        if(message.title.equals("loginAnsw")){
                            int answ = (int)message.obj[0][0];
                            if(answ == 1){
                                loggedIn = true;
                                JOptionPane.showMessageDialog(null, "Congrats!! Your logged in");
                            }else{
                                JOptionPane.showMessageDialog(null, "Wrong username or password");
                                String usr = JOptionPane.showInputDialog("Username");
                                String psw = JOptionPane.showInputDialog("Password");
                                Main.send("login",new Object[][]{{usr},{psw}});
                            }
                        }else{
                        }
                    }
                }catch(Exception e){
                }
            }

            @Override
            public void clientConnected(ServerConnection conn) {
                Main.conn = conn;
            }
        }, "92.220.161.232", 1999, true);
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String usr = JOptionPane.showInputDialog("Username");
        String psw = JOptionPane.showInputDialog("Password");
        Message message = new Message();
        message.obj = new Object[1][2];
        message.title = "login";
        message.obj[0][0] = usr;
        message.obj[0][1] = psw;
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(message);
            byte[] bytes = b.toByteArray();
            conn.send(bytes, Delivery.RELIABLE);
        }catch(Exception e){

        }

    }

    public static void send(String title, Object obj[][]){
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

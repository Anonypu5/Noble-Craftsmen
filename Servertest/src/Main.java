import jexxus.client.ClientConnection;
import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.common.Delivery;
import jexxus.server.ServerConnection;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Elias on 5/25/2015.
 */
public class Main {

    public static boolean loggedIn;

    public static void main(String[] args) {
        ClientConnection conn = new ClientConnection(new ConnectionListener() {
            @Override
            public void connectionBroken(Connection broken, boolean forced) {

            }

            @Override
            public void receive(byte[] data, Connection from) {

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
        while(loggedIn){
            String usr = JOptionPane.showInputDialog("Username");
            String psw = JOptionPane.showInputDialog("Password");
            Message message = new Message();
            message.obj = new Object[2][2];
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ObjectOutputStream o = new ObjectOutputStream(b);
                o.writeObject(file);
                byte[] bytes = b.toByteArray();
            }catch(Exception e){

            }
        }
    }
} class Message implements Serializable {
    public final long serialVersionUID = 1L;
    String tittle;
    Object obj[][];
}

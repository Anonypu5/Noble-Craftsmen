
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object.*;

/**
 * Created by Elias on 5/23/2015.
 */
public class Save {

    public static String osd, os, folder;

    public Save(){
        List<String> list = new ArrayList<String>();
        Object a = list;

        os = System.getProperty("os.name");
        Console.println("Running on platform using "+os);

        if(os.startsWith("Windows")){
            osd = "\\";
            folder = System.getProperty("user.home")+"\\AppData\\Roaming\\.NC-Server";
        }else if(os.startsWith("Mac")){
            osd = "/";
            folder = System.getProperty("user.home")+"/NC-Server";
        }else {
            Console.println("NC-Server Does not currently support this platform");
        }

        java.io.File file = new java.io.File(folder);
        if(!file.exists()){
            Console.println("Could not find any directory at \""+folder+"\", would you like to create a new server or set new directory path: create/new?");
            String answer = Console.requestNext();
            while(!answer.equals("create")&&!answer.equals("new")){
                Console.printErr("Please write new or create");
                answer = Console.requestNext();
            }

            if(answer.equals("create")){
                file.mkdirs();
            }
        }

        Console.println("\nListing files in chosen directory \""+folder+"\":");
        for(String x: file.list()){
            Console.println("    - "+x);
        }
        Console.println("Done\n");

        if(!new java.io.File(file.getPath()+"Settings.ncs").exists()){
            Console.println("Creating Settings.ncs with following settings:" +
                    "\n    - Port: 1999" +
                    "\n");
            creatingSettings(1999);
        }
    }

    public void creatingSettings(int port){
        File file = new File();
        file.obj = new Object[1][2];
        file.obj[0][0] = "Port";
        file.obj[0][1] = port;
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(file);
        }catch(Exception e){

            for (StackTraceElement x: e.getStackTrace()){
                Console.printErr();
            }
        }

    }

}class File implements Serializable {
    static final long serialVersionUID = 1L;
    Object obj[][] = new Object[0][];
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 5/23/2015.
 */
public class Save {

    public static String osd, os, folder;

    public static int port;
    public static String name = "NC-ServerClass";

    public Save() {
        List<String> list = new ArrayList<String>();
        Object a = list;

        os = System.getProperty("os.name");
        Console.println("Running on platform using "+os);

        if(os.startsWith("Windows")){
            osd = "\\";
            folder = System.getProperty("user.home")+"\\AppData\\Roaming\\.NC-ServerClass";
        }else if(os.startsWith("Mac")){
            osd = "/";
            folder = System.getProperty("user.home")+"/NC-ServerClass";
        }else {
            Console.println("NC-ServerClass Does not currently support this platform");
        }

        File dir = new File(folder);
        if(!dir.exists()){
            dir.mkdirs();
        }
        dir = new File(dir.getPath()+osd+"Settings.ncs");
        if(!dir.exists()){
            Console.println("Creating and saving settings...");
            port = 1999;
            saveSettings();
        }else{
            loadSettings();
        }
    }

    private void loadSettings() {
        try {
            FileInputStream i = new FileInputStream(folder+osd+"Settings.ncs");
            long length = new File(folder+osd+"Settings.ncs").length();
            byte[] bytes = new byte[(int)length];
            i.read(bytes);
            ByteArrayInputStream b = new ByteArrayInputStream(bytes);
            ObjectInputStream o = new ObjectInputStream(b);
            SaveFile file = (SaveFile) o.readObject();
            port = (int) file.obj[0][1];
            name = (String) file.obj[1][1];
        } catch (Exception e) {
            Console.printErr(e.toString());
            for (StackTraceElement x: e.getStackTrace()){
                Console.printErr("    "+x.toString());
            }
            e.printStackTrace();
            Console.println("The process above failed, due to the error above \n Do you want to try again y/n?");
            String awnser = Console.requestNext();
            while(!awnser.equals("y")&&!awnser.equals("n")){
                Console.println("Please write y or n");
                awnser = Console.requestNext();
            }
            if(awnser.equals("y")){
                loadSettings();
            }else{
                Console.exit();
            }
        }
    }

    public static void saveSettings(){
        Console.println("Saved settings:" +
                "\nPort: "+Save.port+
                "\nName: "+Save.name);
        SaveFile file = new SaveFile();
        file.obj = new Object[2][2];
        file.obj[0][0] = "Port";
        file.obj[0][1] = port;
        file.obj[1][0] = "Name";
        file.obj[1][1] = name;
        byte[] bytes;
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(file);
            bytes = b.toByteArray();
            FileOutputStream fos = new FileOutputStream(folder+osd+"Settings.ncs");
            fos.write(bytes);
            fos.close();
        }catch(Exception e){
            Console.printErr(e.toString());
            for (StackTraceElement x: e.getStackTrace()){
                Console.printErr("    "+x.toString());
            }
            e.printStackTrace();
            Console.println("The process above failed, due to the error above \n Do you want to try again y/n?");
            String awnser = Console.requestNext();
            while(!awnser.equals("y")&&!awnser.equals("n")){
                Console.println("Please write y or n");
                awnser = Console.requestNext();
            }
            if(awnser.equals("y")){
                saveSettings();
            }else{
                Console.exit();
            }
        }

    }

}class SaveFile implements Serializable {
    static final long serialVersionUID = 1L;
    Object obj[][] = new Object[0][];
}

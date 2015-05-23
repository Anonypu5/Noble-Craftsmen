
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

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

        File file = new File(folder);
        if(!file.exists()){
            Console.println("Could not find any directory at \""+folder+"\", would you like to create a new server or set new directory path: create/new?");
            String answer = Console.requestNext();
            while(!answer.equals("create")&&!answer.equals("other")){
                Console.printErr("Please write other or create");
                answer = Console.requestNext();
            }

            if(answer.equals("create")){

            }
        }
    }

}class file implements Serializable {
    static final long serialVersionUID = 1L;
}

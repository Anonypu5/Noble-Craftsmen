import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Elias on 5/23/2015.
 */


public class Main {

    public static void main(String[] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://10.246.17.21:3306/grabit_no", "grabsit_no", "asd123");
            System.out.println("shit");
//            PreparedStatement statement = con.prepareStatement("select * from Noble_Craftsmen");
//            ResultSet result = statement.executeQuery();
//            while(result.next()){
//                System.out.println(result.getString(1) + "    " + result.getString(2));
//            }
        }catch (Exception e){
            System.out.println(e);
            e.getStackTrace();
        }
        new Console();
        Console.println("Console started successfully");
        new Save();
    }

}

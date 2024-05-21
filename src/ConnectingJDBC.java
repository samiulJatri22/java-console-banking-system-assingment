import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectingJDBC {
    public Connection connectToJDBC (){
        Connection con = null;
        String databaseUrl = "jdbc:postgresql://localhost:5432/banking_system";
        String user = "postgres";
        String password = "postgres";

        try{
            con = DriverManager.getConnection(databaseUrl,user,password);
            if(con != null){
                System.out.println("Database Connected Successfully");
            }else{
                System.out.println("Database Connection Failed");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return con;
    }
}

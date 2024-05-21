import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Transaction {
    Connection con;

    Transaction(Connection con) {
        this.con = con;
    }
    public void deposit(int accountId, int amount) {
        try{
            double currentBalance;

            String sql = "select balance from balance where accountId=" + accountId;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void withdraw(int accountId, int amount) {}
    public void transfer() {}
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Account {
    Connection con;
    Account (Connection con){
        this.con = con;
    }

    public void createAccount(String accountHolderName, double balance){
        try {
            String sql = "INSERT INTO account (account_holder_name, balance) VALUES (?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accountHolderName);
            ps.setDouble(2, balance);

            int res = ps.executeUpdate();

            if(res == 0){
                System.out.println("Account creation failed");
            }

            System.out.println("Account created successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAccountDetails(int account_id){
        try{
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("account_id"));
                System.out.println(rs.getString("account_holder_name"));
                System.out.println(rs.getDouble("balance"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

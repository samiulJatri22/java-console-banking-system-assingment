import java.sql.Connection;
import java.sql.Statement;

public class DBTableInitializer {
    Connection conn;

    DBTableInitializer(Connection conn) {
        this.conn = conn;
    }

    public void createAccountTable () {
        try{
            String sql = "create table if not exists account ("
                        + "account_id varchar(10) primary key, "
                        + "account_holder_name varchar(55) not null, "
                        + "balance decimal(10,2) not null "
                        + ")";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Account table created.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createTransactionTable () {
        try {
            String sql = "create table if not exists transaction ("
                    + "transaction_id serial primary key, "
                    + "account_id varchar(10) not null, "
                    + "transaction_type varchar(10) not null, "
                    + "amount decimal(10,2) not null, "
                    + "timestamp timestamp default current_timestamp, "
                    + "foreign key (account_id) references account(account_id) "
                    + ")";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Transaction table created.");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

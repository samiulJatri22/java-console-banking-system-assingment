import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /**
         * connection create with database
         * */
        ConnectingJDBC conObj = new ConnectingJDBC();
        Connection conn = conObj.connectToJDBC();

        /**
         * creating tables
         * */
        DBTableInitializer dbTable = new DBTableInitializer(conn);
        dbTable.createAccountTable();
        dbTable.createTransactionTable();

        /**
         * account
         */
        Account acc = new Account(conn);
        acc.createAccount("Samiul Islam", 5000.00);
        acc.getAccountDetails(1);
        System.out.println("---------------------------------------------------------------");

        acc.createAccount("Akash", 5000.00);
        acc.getAccountDetails(11);
        System.out.println("---------------------------------------------------------------");

        /**
         * transaction
         */
        Transaction tran = new Transaction(conn);
        tran.deposit(1, 1000.00);
        acc.getAccountDetails(1);
        System.out.println("---------------------------------------------------------------");

        tran.withdraw(1, 500.00);
        acc.getAccountDetails(1);
        System.out.println("---------------------------------------------------------------");

        tran.transfer(1, 2, 1000.00);
        acc.getAccountDetails(1);
        System.out.println("---------------------------------------------------------------");
        acc.getAccountDetails(2);
        System.out.println("---------------------------------------------------------------");

        /**
         * using thread on transaction
         */

        Thread t1 = new Thread(() -> {
            try {
                tran.deposit(1, 1000.00);
                acc.getAccountDetails(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                tran.withdraw(2, 500.00);
                acc.getAccountDetails(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
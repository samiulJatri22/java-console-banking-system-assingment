import java.sql.Connection;

public class Main {
    public static void main(String[] args)  {
        /**
         * connection create with database
         * */
        ConnectingJDBC conObj = new ConnectingJDBC();
        Connection conn = conObj.connectToJDBC();

        /**
         * creating tables
         * */
//        DBTableInitializer dbTable = new DBTableInitializer(conn);
//        dbTable.createAccountTable();
//        dbTable.createTransactionTable();

        /**
         * account
         */
//        Account acc = new Account(conn);
//        acc.createAccount("Samiul Islam", 5000.00);
//        acc.getAccountDetails(11);

        /**
         * transaction
         */
        Transaction tran = new Transaction(conn);
        tran.deposit(12, 129);
    }
}
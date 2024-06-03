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

    }
}
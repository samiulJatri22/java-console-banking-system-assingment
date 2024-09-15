import java.sql.*;

public class Transaction {
    Connection con;

    Transaction(Connection con) {
        this.con = con;
    }

    public void deposit(String accountId, double amount) throws SQLException {
        try{
            this.con.setAutoCommit(false);

            String sql = "select balance from account where account_id=" + accountId;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next()){
                throw new Exception("Account not found!");
            }

            double currentBalance = rs.getDouble("balance");
            String updateSql = "update account set balance=" + (currentBalance+amount) + " where account_id=" + accountId;
            int balanceUpdate = stmt.executeUpdate(updateSql);

            if(balanceUpdate == 0){
                throw new Exception("Deposit failed!");
            }

            this.createTransaction(accountId, "DEPOSIT", amount);
            this.con.commit();
        }catch(Exception e){
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(String accountId, double amount) throws SQLException {
        try{
            this.con.setAutoCommit(false);

            String sql = "select balance from account where account_id=" + accountId;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next()){
                throw new Exception("Account not found!");
            }

            double currentBalance = rs.getDouble("balance");
            if(currentBalance < amount){
                throw new Exception("Insufficient funds!");
            }

            String updateSql = "update account set balance=" + (currentBalance-amount) + " where account_id=" + accountId;
            int balanceUpdate = stmt.executeUpdate(updateSql);
            if(balanceUpdate == 0){
                throw new Exception("Deposit failed!");
            }

            this.createTransaction(accountId, "WITHDRAW", amount);

            this.con.commit();
        }catch(Exception e){
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }

    public void transfer(String sourceAccountId, String targetAccountId, double amount) throws SQLException {
        try{
            this.con.setAutoCommit(false);

            String sourceAccountSql = "select balance from account where account_id=" + sourceAccountId;
            Statement stmt = con.createStatement();
            ResultSet sourceAccountRs = stmt.executeQuery(sourceAccountSql);

            if(!sourceAccountRs.next()){
                throw new Exception("Sender Account not found!");
            }

            double currentBalance = sourceAccountRs.getDouble("balance");
            if(currentBalance < amount){
                throw new Exception("Insufficient funds!");
            }

            String targetAccountSql = "select balance from account where account_id=" + targetAccountId;
            ResultSet targetAccountRs = stmt.executeQuery(targetAccountSql);
            if(!targetAccountRs.next()){
                throw new Exception("Receiver Account not found!");
            }

            double currentBalance2 = targetAccountRs.getDouble("balance");
            System.out.println("currentBalance2"+currentBalance2);

            String senderBalanceSubTractionSql = "update account set balance=" + (currentBalance-amount) + " where account_id=" + sourceAccountId;
            stmt.executeUpdate(senderBalanceSubTractionSql);

            String targetBalanceSumTractionSql = "update account set balance=" + (amount+ currentBalance2) + " where account_id=" + targetAccountId;
            stmt.executeUpdate(targetBalanceSumTractionSql);

            this.createTransaction(sourceAccountId, "TRANSFER", amount);

            this.con.commit();
        }catch (Exception e){
            this.con.rollback();
            System.out.println(e.getMessage());
        }
    }

    public void createTransaction(String accountId, String transactionType, double amount) {
        try{
            String sql = "insert into transaction (account_id,transaction_type,amount) values(?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, accountId);
            pstmt.setString(2, transactionType);
            pstmt.setDouble(3, amount);

            int res =  pstmt.executeUpdate();
            if(res == 0){
                throw new Exception("Transaction creation failed!");
            }

            System.out.println(" Transaction Created Successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

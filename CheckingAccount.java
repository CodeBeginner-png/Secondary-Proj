/**
 * CheckingAccount: A specialized account designed for frequent transactions.
 * 
 * Features:
 * - Charges a per-transaction fee for withdrawals
 * - Supports overdraft protection (allows going slightly negative)
 * - Enforces Liskov Substitution: can be used anywhere Account is expected
 */
public class CheckingAccount extends Account {
    private double withdrawalFee;
    private double overdraftLimit;
    private int transactionCount;
    
    public CheckingAccount(String owner, double initialBalance, double withdrawalFee, double overdraftLimit) {
        super(owner, initialBalance);
        this.withdrawalFee = withdrawalFee;
        this.overdraftLimit = overdraftLimit;
        this.transactionCount = 0;
    }
    
    /**
     * Overrides withdraw to apply per-transaction fees and enforce overdraft limits
     * @param amount the amount to withdraw
     * @return true if withdrawal successful, false otherwise
     */
    @Override
    public boolean withdraw(double amount) {
        double totalWithdrawal = amount + withdrawalFee;
        
        // Check if withdrawal would exceed overdraft limit
        if (balance - totalWithdrawal < -overdraftLimit) {
            System.out.println("  [BLOCKED] Withdrawal would exceed overdraft limit of $" + overdraftLimit);
            return false;
        }
        
        if (amount > 0) {
            balance -= totalWithdrawal;
            onWithdraw(amount);
            transactionCount++;
            return true;
        }
        return false;
    }
    
    /**
     * Hook method to apply per-transaction fee display
     * @param amount the amount withdrawn (before fee)
     */
    @Override
    protected void onWithdraw(double amount) {
        // Fee already deducted, just track the transaction
    }
    
    /**
     * Gets the number of transactions made on this account
     * @return transaction count
     */
    public int getTransactionCount() {
        return transactionCount;
    }
    
    /**
     * Gets the current per-transaction fee
     * @return withdrawal fee amount
     */
    public double getWithdrawalFee() {
        return withdrawalFee;
    }
    
    /**
     * Determines if account is overdrawn
     * @return true if balance is negative
     */
    public boolean isOverdrawn() {
        return balance < 0;
    }
    
    @Override
    public String toString() {
        String status = isOverdrawn() ? " [OVERDRAWN]" : "";
        return String.format("%s [Owner: %s, Balance: $%.2f, Fee: $%.2f, Transactions: %d]%s",
            this.getClass().getSimpleName(), owner, balance, withdrawalFee, transactionCount, status);
    }
}

/**
 * Base Account class demonstrating inheritance and overridable methods.
 * This class uses composition with an InterestStrategy to support runtime strategy changes.
 */
public abstract class Account {
    protected String owner;
    protected double balance;
    
    /**
     * Constructor for Account
     * @param owner name of the account holder
     * @param initialBalance starting balance
     */
    public Account(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }
    
    /**
     * Deposits money into the account
     * Subclasses can override to add additional behavior (e.g., logging, fees).
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            onDeposit(amount);
        }
    }
    
    /**
     * Withdraws money from the account
     * Subclasses can override to add restrictions (e.g., withdrawal limits).
     * @param amount the amount to withdraw
     * @return true if withdrawal successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            onWithdraw(amount);
            return true;
        }
        return false;
    }
    
    /**
     * Gets current account balance
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Gets account owner name
     * @return the owner name
     */
    public String getOwner() {
        return owner;
    }
    
    /**
     * Protected hook method called after a successful deposit.
     * Subclasses can override to implement custom deposit behavior.
     * @param amount the amount that was deposited
     */
    protected void onDeposit(double amount) {
        // Default: do nothing. Subclasses can override.
    }
    
    /**
     * Protected hook method called after a successful withdrawal.
     * Subclasses can override to implement custom withdrawal behavior.
     * @param amount the amount that was withdrawn
     */
    protected void onWithdraw(double amount) {
        // Default: do nothing. Subclasses can override.
    }
    
    /**
     * Returns a formatted string representation of the account
     * @return account details
     */
    @Override
    public String toString() {
        return String.format("%s [Owner: %s, Balance: $%.2f]", 
            this.getClass().getSimpleName(), owner, balance);
    }
}

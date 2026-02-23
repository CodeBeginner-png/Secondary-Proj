/**
 * SavingsAccount: A specialized account designed for saving money with interest.
 * 
 * Features:
 * - Earns interest based on an InterestStrategy
 * - Limits the number of withdrawals per month (max 6)
 * - Enforces Liskov Substitution: can be used anywhere Account is expected
 */
public class SavingsAccount extends Account {
    private InterestStrategy interestStrategy;
    private int withdrawalsThisMonth;
    private static final int MAX_WITHDRAWALS_PER_MONTH = 6;
    
    public SavingsAccount(String owner, double initialBalance, InterestStrategy strategy) {
        super(owner, initialBalance);
        this.interestStrategy = strategy;
        this.withdrawalsThisMonth = 0;
    }
    
    /**
     * Sets the interest strategy at runtime.
     * Demonstrates the Strategy pattern: can swap implementations without changing Account.
     * @param strategy the new interest strategy
     */
    public void setInterestStrategy(InterestStrategy strategy) {
        this.interestStrategy = strategy;
    }
    
    /**
     * Applies interest to the balance based on the current strategy
     */
    public void applyInterest() {
        double interest = interestStrategy.calculate(balance);
        balance += interest;
    }
    
    /**
     * Returns the current interest strategy name
     * @return strategy name
     */
    public String getActiveStrategy() {
        return interestStrategy.getStrategyName();
    }
    
    /**
     * Overrides withdraw to enforce withdrawal limits
     * @param amount the amount to withdraw
     * @return true if withdrawal successful, false otherwise
     */
    @Override
    public boolean withdraw(double amount) {
        if (withdrawalsThisMonth >= MAX_WITHDRAWALS_PER_MONTH) {
            System.out.println("  [BLOCKED] Maximum monthly withdrawals (" + MAX_WITHDRAWALS_PER_MONTH + ") reached");
            return false;
        }
        
        boolean success = super.withdraw(amount);
        if (success) {
            withdrawalsThisMonth++;
        }
        return success;
    }
    
    /**
     * Resets monthly withdrawal counter (call at month-end)
     */
    public void resetMonthlyWithdrawals() {
        withdrawalsThisMonth = 0;
    }
    
    /**
     * Gets remaining withdrawals for this month
     * @return number of withdrawals left
     */
    public int getRemainingWithdrawals() {
        return MAX_WITHDRAWALS_PER_MONTH - withdrawalsThisMonth;
    }
    
    @Override
    public String toString() {
        return String.format("%s [Owner: %s, Balance: $%.2f, Withdrawals Used: %d/%d, Strategy: %s]",
            this.getClass().getSimpleName(), owner, balance, withdrawalsThisMonth, 
            MAX_WITHDRAWALS_PER_MONTH, interestStrategy.getStrategyName());
    }
}

/**
 * Simple interest strategy: calculates a fixed percentage of the balance.
 * Interest = balance * rate
 */
public class SimpleInterest implements InterestStrategy {
    private double annualRate;  // e.g., 0.02 for 2% annual rate
    
    public SimpleInterest(double annualRate) {
        this.annualRate = annualRate;
    }
    
    @Override
    public double calculate(double balance) {
        return balance * annualRate;
    }
    
    @Override
    public String getStrategyName() {
        return String.format("Simple Interest (%.2f%%)", annualRate * 100);
    }
}

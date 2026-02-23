/**
 * Strategy interface for calculating interest on account balance.
 * Different implementations allow for different interest calculation algorithms.
 */
public interface InterestStrategy {
    /**
     * Calculates interest based on the account balance
     * @param balance the current account balance
     * @return the interest amount to be added
     */
    double calculate(double balance);
    
    /**
     * Returns a description of this strategy
     * @return strategy name/description
     */
    String getStrategyName();
}

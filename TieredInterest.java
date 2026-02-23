/**
 * Tiered interest strategy: applies different rates based on balance tiers.
 * This demonstrates more complex strategy logic.
 * - Balance < $1000: 0.5% interest
 * - Balance $1000-$5000: 1.5% interest
 * - Balance >= $5000: 2.5% interest
 */
public class TieredInterest implements InterestStrategy {
    
    @Override
    public double calculate(double balance) {
        if (balance < 1000) {
            return balance * 0.005;  // 0.5%
        } else if (balance < 5000) {
            return balance * 0.015;  // 1.5%
        } else {
            return balance * 0.025;  // 2.5%
        }
    }
    
    @Override
    public String getStrategyName() {
        return "Tiered Interest (0.5% < $1K, 1.5% $1K-$5K, 2.5% >= $5K)";
    }
}

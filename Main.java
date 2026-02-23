import java.util.ArrayList;

/**
 * Main demonstration class showcasing:
 * 1. Polymorphism: Using different Account subclasses through a common interface
 * 2. Strategy Pattern: Runtime strategy swapping without modifying Account classes
 * 3. Open/Closed Principle: New strategies can be added without changing Account code
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("BANKING SYSTEM DEMO - Polymorphism & Strategy Pattern");
        System.out.println("========================================\n");
        
        // ===== PART 1: Polymorphism Demo =====
        System.out.println("PART 1: POLYMORPHISM DEMONSTRATION");
        System.out.println("----------------------------------");
        System.out.println("Creating a polymorphic ArrayList<Account> with mixed subclasses:\n");
        
        // Create accounts of different types
        SimpleInterest simpleInterest = new SimpleInterest(0.02);  // 2% simple interest
        TieredInterest tieredInterest = new TieredInterest();
        
        SavingsAccount savingsAccount1 = new SavingsAccount("Alice Johnson", 5000, simpleInterest);
        CheckingAccount checkingAccount1 = new CheckingAccount("Bob Smith", 2000, 2.50, 500);
        SavingsAccount savingsAccount2 = new SavingsAccount("Charlie Brown", 1500, tieredInterest);
        
        // Add all accounts to a polymorphic list
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(savingsAccount1);
        accounts.add(checkingAccount1);
        accounts.add(savingsAccount2);
        
        // Demonstrate polymorphism: call shared methods, subclass behavior differs
        System.out.println("Initial Account States:");
        for (Account acc : accounts) {
            System.out.println("  " + acc);
        }
        
        System.out.println("\nPerforming deposits on all accounts:");
        for (Account acc : accounts) {
            acc.deposit(500);
            System.out.println("  Deposited $500 to " + acc.getOwner() + " -> Balance: $" + acc.getBalance());
        }
        
        System.out.println("\nPerforming withdrawals on all accounts:");
        for (Account acc : accounts) {
            boolean success = acc.withdraw(300);
            String status = success ? "SUCCESS" : "FAILED";
            System.out.println("  Withdrew $300 from " + acc.getOwner() + " [" + status + "] -> Balance: $" + acc.getBalance());
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PART 2: STRATEGY PATTERN - RUNTIME STRATEGY SWAPPING");
        System.out.println("=".repeat(60) + "\n");
        
        // ===== PART 2: Strategy Swapping Demo =====
        System.out.println("Creating a SavingsAccount with SimpleInterest strategy:");
        SavingsAccount savingsDemo = new SavingsAccount("Diana Prince", 10000, 
            new SimpleInterest(0.02));  // 2% simple interest
        
        System.out.println(savingsDemo);
        System.out.println("  Active Strategy: " + savingsDemo.getActiveStrategy());
        
        System.out.println("\nApplying interest with SimpleInterest strategy:");
        savingsDemo.applyInterest();
        double interestEarned = 10000 * 0.02;
        System.out.println("  Interest earned: $" + String.format("%.2f", interestEarned));
        System.out.println("  New balance: $" + savingsDemo.getBalance());
        
        // Runtime strategy swap - the key feature of the Strategy pattern
        System.out.println("\n>>> SWAPPING STRATEGY AT RUNTIME <<<");
        System.out.println("Changing to TieredInterest strategy:");
        savingsDemo.setInterestStrategy(new TieredInterest());
        System.out.println("  New Active Strategy: " + savingsDemo.getActiveStrategy());
        
        System.out.println("\nApplying interest with TieredInterest strategy:");
        System.out.println("  Current balance: $" + savingsDemo.getBalance());
        double currentBalance = savingsDemo.getBalance();
        savingsDemo.applyInterest();
        // Tiered interest: balance >= $5000, so 2.5%
        double newInterest = currentBalance * 0.025;
        System.out.println("  Interest earned: $" + String.format("%.2f", newInterest));
        System.out.println("  New balance: $" + savingsDemo.getBalance());
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("OPEN/CLOSED PRINCIPLE EXPLANATION:");
        System.out.println("=".repeat(60));
        System.out.println("""
            The Strategy Pattern follows the Open/Closed Principle:
            - OPEN for extension: New interest strategies (e.g., CompoundInterest, NoInterest)
              can be created by implementing InterestStrategy without any changes.
            - CLOSED for modification: The Account and SavingsAccount classes don't need
              to be modified to support new strategies.
            
            Example: To add NoInterest strategy, just create a new class:
              public class NoInterest implements InterestStrategy {
                  public double calculate(double balance) { return 0; }
                  public String getStrategyName() { return "No Interest"; }
              }
            
            Then use it: savingsAccount.setInterestStrategy(new NoInterest());
            No Account class changes needed!
            """);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PART 3: LISKOV SUBSTITUTION PRINCIPLE");
        System.out.println("=".repeat(60) + "\n");
        
        System.out.println("Testing that SavingsAccount and CheckingAccount work anywhere Account is expected:");
        testBankOperations(savingsAccount1);
        testBankOperations(checkingAccount1);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMONSTRATION COMPLETE");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Generic method that works with any Account subclass.
     * Demonstrates Liskov Substitution: subclasses can be used interchangeably.
     * @param account any Account type
     */
    private static void testBankOperations(Account account) {
        System.out.println("Testing with: " + account.getClass().getSimpleName() + " (" + account.getOwner() + ")");
        System.out.println("  Starting balance: $" + account.getBalance());
        
        account.deposit(1000);
        System.out.println("  After $1000 deposit: $" + account.getBalance());
        
        boolean withdrew = account.withdraw(300);
        System.out.println("  After $300 withdrawal attempt: " + (withdrew ? "SUCCESS" : "FAILED"));
        System.out.println("  Final balance: $" + account.getBalance());
        
        if (account instanceof SavingsAccount) {
            SavingsAccount savings = (SavingsAccount) account;
            System.out.println("  Remaining monthly withdrawals: " + savings.getRemainingWithdrawals());
            savings.applyInterest();
            System.out.println("  After interest applied: $" + account.getBalance());
        } else if (account instanceof CheckingAccount) {
            CheckingAccount checking = (CheckingAccount) account;
            System.out.println("  Withdrawal fee per transaction: $" + checking.getWithdrawalFee());
            System.out.println("  Total transactions: " + checking.getTransactionCount());
        }
        System.out.println();
    }
}

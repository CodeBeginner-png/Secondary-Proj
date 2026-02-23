# Banking System - Polymorphism & Strategy Pattern Demo

A comprehensive Java project demonstrating **polymorphism**, the **Strategy pattern**, and **SOLID principles** through a practical banking system.

## Overview

This project showcases key object-oriented programming concepts:
- **Polymorphism**: Different account types behave appropriately through a common interface
- **Strategy Pattern**: Runtime algorithm swapping for interest calculations
- **Liskov Substitution Principle (LSP)**: Subclasses work seamlessly as their parent type
- **Open/Closed Principle (OCP)**: New strategies can be added without modifying existing code
- **Single Responsibility Principle (SRP)**: Each class has a focused, well-defined purpose

## Project Structure

```
├── Account.java                 # Abstract base class for all accounts
├── SavingsAccount.java          # Savings account with withdrawal limits & interest
├── CheckingAccount.java         # Checking account with transaction fees
├── InterestStrategy.java        # Strategy interface for interest calculations
├── SimpleInterest.java          # Fixed-rate interest implementation
├── TieredInterest.java          # Tiered interest implementation
├── Main.java                    # Demonstration and test cases
└── README.md                    # This file
```

## Key Classes

### `Account` (Abstract Base Class)
The foundation for all account types.

**Fields:**
- `owner: String` - Account holder name
- `balance: double` - Current account balance

**Methods:**
- `deposit(double amount)` - Add funds to account
- `withdraw(double amount)` - Remove funds from account
- `getBalance()` - Retrieve current balance
- `getOwner()` - Retrieve owner name
- `onDeposit(double amount)` - Protected hook method for subclass customization
- `onWithdraw(double amount)` - Protected hook method for subclass customization

### `SavingsAccount` extends `Account`
Designed for long-term saving with interest and withdrawal restrictions.

**Features:**
- Earns interest based on an `InterestStrategy`
- Limits withdrawals to 6 per month (enforces Liskov Substitution)
- Strategy can be swapped at runtime

**Key Methods:**
- `setInterestStrategy(InterestStrategy strategy)` - Change strategy at runtime
- `applyInterest()` - Apply current strategy's interest calculation
- `getRemainingWithdrawals()` - Check monthly withdrawal limit

### `CheckingAccount` extends `Account`
Designed for frequent transactions with transaction fees.

**Features:**
- Charges a per-transaction withdrawal fee
- Supports overdraft protection (configurable limit)
- Tracks transaction count

**Key Methods:**
- `isOverdrawn()` - Check if account balance is negative
- `getWithdrawalFee()` - Get the per-transaction fee
- `getTransactionCount()` - Get number of transactions

### `InterestStrategy` (Interface)
The Strategy pattern interface allowing runtime algorithm selection.

```java
public interface InterestStrategy {
    double calculate(double balance);
    String getStrategyName();
}
```

### Strategy Implementations

**`SimpleInterest`**: Fixed percentage rate on balance
```
Interest = balance × annualRate
Example: 2% annual rate on $10,000 = $200 interest
```

**`TieredInterest`**: Different rates based on balance tiers:
- Balance < $1,000: 0.5% interest
- Balance $1,000–$5,000: 1.5% interest  
- Balance ≥ $5,000: 2.5% interest

## How to Run

### Compile and Run

```bash
cd "path/to/project"
javac *.java
java Main
```

### Expected Output

The program demonstrates:
1. **Polymorphism**: Creating mixed account types in a single ArrayList and calling the same methods
2. **Strategy Swapping**: Changing interest strategies at runtime and observing different results
3. **Liskov Substitution**: Both SavingsAccount and CheckingAccount work correctly anywhere Account is expected

### Sample Output

```
========================================
BANKING SYSTEM DEMO - Polymorphism & Strategy Pattern
========================================

PART 1: POLYMORPHISM DEMONSTRATION
----------------------------------
Initial Account States:
  SavingsAccount [Owner: Alice Johnson, Balance: $5000.00, ...]
  CheckingAccount [Owner: Bob Smith, Balance: $2000.00, ...]
  SavingsAccount [Owner: Charlie Brown, Balance: $1500.00, ...]

Performing deposits on all accounts:
  Deposited $500 to Alice Johnson -> Balance: $5500.0
  ...

PART 2: STRATEGY PATTERN - RUNTIME STRATEGY SWAPPING
============================================================

Creating a SavingsAccount with SimpleInterest strategy:
  Active Strategy: Simple Interest (2.00%)

Applying interest with SimpleInterest strategy:
  Interest earned: $200.00
  New balance: $10200.0

>>> SWAPPING STRATEGY AT RUNTIME <<<
Changing to TieredInterest strategy:
  New Active Strategy: Tiered Interest (0.5% < $1K, 1.5% $1K-$5K, 2.5% >= $5K)

Applying interest with TieredInterest strategy:
  Interest earned: $255.00
  New balance: $10455.0
```

## Design Patterns & Principles

### Strategy Pattern
The core pattern—allows selecting an algorithm (interest calculation) at runtime.

✅ **Benefits:**
- Same Account class works with different interest strategies
- New strategies can be added without modifying Account
- Strategies are easily swappable: `account.setInterestStrategy(newStrategy)`

✅ **Open/Closed Principle**: Adding a new strategy doesn't require code changes:
```java
// No Account.java modifications needed!
public class CompoundInterest implements InterestStrategy {
    public double calculate(double balance) { /* ... */ }
    public String getStrategyName() { return "Compound Interest"; }
}

// Just use it:
savingsAccount.setInterestStrategy(new CompoundInterest());
```

### Liskov Substitution Principle (LSP)
Both `SavingsAccount` and `CheckingAccount` can be used anywhere `Account` is expected:
```java
ArrayList<Account> accounts = new ArrayList<>();
accounts.add(new SavingsAccount(...));      // Valid
accounts.add(new CheckingAccount(...));     // Valid

for (Account acc : accounts) {
    acc.deposit(100);  // Works for both types appropriately
    acc.withdraw(50);  // Each subclass implements correctly
}
```

### Single Responsibility Principle (SRP)
- `Account` → Basic banking operations
- `SavingsAccount` → Withdrawal limits and interest
- `CheckingAccount` → Transaction fees and overdraft
- `InterestStrategy` → Interest calculation logic only

## Extension Examples

### Adding a New Strategy
```java
public class NoInterest implements InterestStrategy {
    @Override
    public double calculate(double balance) {
        return 0;  // No interest
    }
    
    @Override
    public String getStrategyName() {
        return "No Interest";
    }
}

// Usage: savingsAccount.setInterestStrategy(new NoInterest());
```

### Adding Compound Interest
```java
public class CompoundInterest implements InterestStrategy {
    private double monthlyRate;
    
    public CompoundInterest(double annualRate) {
        this.monthlyRate = annualRate / 12;
    }
    
    @Override
    public double calculate(double balance) {
        return balance * (Math.pow(1 + monthlyRate, 1) - 1);
    }
    
    @Override
    public String getStrategyName() {
        return "Compound Interest";
    }
}
```

## Requirements Met

✅ **Base Class (`Account`):**
- Fields: `owner`, `balance`
- Methods: `deposit()`, `withdraw()`, `getBalance()`
- Overridable methods & protected hooks for subclass customization

✅ **Subclasses:**
- `SavingsAccount`: Interest and withdrawal limits
- `CheckingAccount`: Transaction fees and overdraft
- Liskov Substitution enforced

✅ **Strategy Pattern:**
- Interface: `InterestStrategy`
- Two implementations: `SimpleInterest`, `TieredInterest`
- Composed into `Account`
- Setter for runtime swapping

✅ **Polymorphism Demonstration:**
- ArrayList with mixed account types
- Shared methods work correctly on each subclass

✅ **Strategy Swapping at Runtime:**
- Create account with one strategy
- Swap to another strategy
- Results differ appropriately

✅ **Main Demo:**
- Multiple SavingsAccount and CheckingAccount instances
- Deposits, withdrawals, interest/fee calculations
- Clear output showing each operation

✅ **SOLID Principles:**
- Single Responsibility: Each class has focused purpose
- Open/Closed: New strategies without existing class changes
- Liskov Substitution: Subclasses work as base type
- Interface Segregation: Strategy interface is compact
- Dependency Inversion: Account depends on interface, not concrete strategies

## Compilation & Testing

```bash
# Compile
javac *.java

# Run
java Main

# Clean up compiled files
rm *.class
```

## Technologies Used

- **Language**: Java (Standard Edition)
- **Paradigms**: Object-Oriented Programming, Design Patterns
- **Key Concepts**: Inheritance, Polymorphism, Composition, Interfaces

## Notes

- No external libraries required (pure Java)
- No static global variables used
- Each account maintains its own strategy instance
- Classes follow single responsibility principle
- Code is well-documented with JavaDoc comments

## Future Enhancements

Possible extensions without modifying existing code:
- `CompoundInterest` strategy
- `NoInterest` strategy
- `MultiTierFeeStrategy` for checking accounts
- Account statement history tracking
- Transaction logging with strategy auditing
- Minimum balance enforcement

## Author

Created as a comprehensive demonstration of polymorphism and the Strategy design pattern in Java.

---

**Note**: This is an educational project demonstrating design patterns and SOLID principles. For a real banking system, additional features like persistence, security, and compliance would be necessary.

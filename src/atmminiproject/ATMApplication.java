package atmminiproject;


import java.util.Scanner;

abstract class Account {
    protected float balance, interestRate;
    protected int pin = 123;

    public abstract void addInterest();
    public abstract void checkBalance();
    public abstract void depositMoney(float amount);
    public abstract void withdrawMoney(float amount);
    
    public boolean checkPin(int pin) {
        return this.pin == pin;
    }

    public void changePin(int newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully.");
    }
    
    public void set(float initialBalance, float interestRate) {
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }
}

class ATMAccount extends Account {
    
    @Override
    public void checkBalance() { 
        System.out.println("Balance: " + balance); 
    }
    
    @Override
    public void depositMoney(float amount) {
        balance += amount;
        System.out.println("Deposited successfully. New balance: " + balance);
    }
    
    @Override
    public void withdrawMoney(float amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }
    
    @Override
    public void addInterest() {
        float interest = (balance * interestRate) / 100;
        balance += interest;
        System.out.println("Interest added. Current balance: " + balance);
    }
}

public class ATMApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account atm = new ATMAccount();
        
        while (true) {
            System.out.println("Enter PIN: ");
            if (atm.checkPin(sc.nextInt())) break;//If the PIN is correct (true), the break statement exits the loop.
						  //If the PIN is incorrect, "Invalid PIN. Try again." is printed, and the loop repeats.
            System.out.println("Invalid PIN. Try again.");
        }
        
        System.out.println("Enter initial balance: ");
        float initialBalance = sc.nextFloat();
        System.out.println("Enter interest rate: ");
        float interestRate = sc.nextFloat();
        atm.set(initialBalance, interestRate);
        
        while (true) {
            System.out.println("\n** ATM Menu **");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Add Interest");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.println("Enter deposit amount:");
                    float depositAmount = sc.nextFloat();
                    atm.depositMoney(depositAmount);
                    break;
                case 3:
                    System.out.println("Enter withdrawal amount:");
                    float withdrawAmount = sc.nextFloat();
                    atm.withdrawMoney(withdrawAmount);
                    break;
                case 4:
                    atm.addInterest();
                    break;
                case 5:
                    System.out.println("Enter new PIN:");
                    int newPin = sc.nextInt();
                    ((ATMAccount) atm).changePin(newPin);
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

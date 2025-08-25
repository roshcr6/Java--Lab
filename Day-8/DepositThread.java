class BankAccount {
    private int balance = 1000; 

    public synchronized void updateBalance(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() +
                           " updated balance. Current Balance = " + balance);
    }
}

class DepositThread extends Thread {
    BankAccount account;

    DepositThread(BankAccount acc) {
        account = acc;
    }

    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                account.updateBalance(500); 
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            System.out.println("Deposit interrupted.");
        }
    }
}

class WithdrawThread extends Thread {
    BankAccount account;

    WithdrawThread(BankAccount acc) {
        account = acc;
    }

    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                account.updateBalance(-300); 
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            System.out.println("Withdraw interrupted.");
        }
    }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();

        DepositThread d = new DepositThread(acc);
        WithdrawThread w = new WithdrawThread(acc);

        d.setName("DepositThread");
        w.setName("WithdrawThread");

        d.start();
        w.start();
    }
}


package concurrency.thread;

import java.util.Arrays;

/**
 * A bank with a number of bank accounts.
 * @date 2023/1/11
 * @reference Ch.12, Core Java I (Twelfth Edition)
 */
public class Bank {
    private final double[] accounts;

    /**
     * Constructs the bank.
     * @param n the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /**
     * Transfers money from one account to another.
     * @param from the account to transfer from
     * @param to the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread().getName());
        accounts[from] -= amount;
        System.out.printf("$%-6.2f from [%d] to [%d]\n", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: $%9.2f\n", getTotalBalance());
        System.out.println();
    }

    /**
     * Gets the sum of all account balances.
     * @return the total balance
     */
    public double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}

package com.vending.machine;

public class Customer {
    private double balance;

    public Customer(double balance) {
        this.balance = balance;
    }

    public boolean checkIfCustomerHasBalance() {
        return balance > 0;
    }

    public double getBalance() {
        return this.balance;
    }
}

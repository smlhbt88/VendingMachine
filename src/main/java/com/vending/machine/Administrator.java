package com.vending.machine;

public class Administrator {
    private VendingMachine vendingMachine;

    public Administrator(VendingMachine vendingMachine) {
        this.vendingMachine=vendingMachine;
    }

    public void withdrawMoney(double money) {
        vendingMachine.setBalance(vendingMachine.getBalance()-money);
    }

    public void depositMoney(double money) {
        vendingMachine.setBalance(vendingMachine.getBalance()+money);
    }
}

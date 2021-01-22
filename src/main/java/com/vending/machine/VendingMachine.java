package com.vending.machine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private double balance;
    private List<Item> items = new ArrayList<>();
    private Customer customer;

    public VendingMachine(Customer customer) {
        this.customer = customer;
    }

    public VendingMachine(){}

    public VendingMachine(double balance) {
        this.balance=balance;
    }

    public boolean checkItemsIsEmpty() {
        return items.isEmpty();
    }

    public void addItemsToTheStock(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public String checkIfItemExists(int itemCode) {
        for(Item item: items) {
            if(item.getItemCode() == itemCode) {
                return "item found";
            }
        }
        return "no such item";
    }

    public String checkInsufficientFund(int itemCode) {
        for(Item item: items) {
            if(itemCode == item.getItemCode()) {
                if(customer.getBalance() < item.getPrice()) {
                    return "insufficient fund";
                }
            }
        }
        return null;
    }

    public Map<Item, BigDecimal> getItemAndChange(int itemCode) {
        Map<Item, BigDecimal> result = new HashMap<>();
        for(Item item: items) {
            if(itemCode == item.getItemCode()) {
                BigDecimal balanceCustomer = new BigDecimal(customer.getBalance());
                BigDecimal itemPrice = new BigDecimal(item.getPrice());
                BigDecimal _c = balanceCustomer.subtract(itemPrice);
                MathContext mc = new MathContext(2);
                result.put(item,_c.round(mc));
                break;
            }
        }
        return result;
    }

    public double cancelTransaction() {
        return customer.getBalance();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

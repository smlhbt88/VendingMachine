package com.vending.machine;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingMachineTest {
    VendingMachine vendingMachine_no_arg = new VendingMachine();
    Customer customer_balance_arg = new Customer(3);
    VendingMachine vendingMachine_customer_arg = new VendingMachine(customer_balance_arg);
    VendingMachine vendingMachine_balance_arg = new VendingMachine(1000);
    Administrator administrator = new Administrator(vendingMachine_balance_arg);
    /**
     * Given there are no items in stock
     * When I check the vending machine
     * Then I should see it is empty
     */
    @Test
    public void checkNoItemsInStockIsEmpty_Administrator() {
        assertTrue(vendingMachine_no_arg.checkItemsIsEmpty());
    }

    /**
     * Given there are no items in stock
     * When I add some items
     * Then I should see them in the vending machine
     */
    @Test
    public void addItemsToVendingMachine_Administrator() {
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_no_arg.addItemsToTheStock(item1);
        vendingMachine_no_arg.addItemsToTheStock(item2);
        assertEquals(item1,vendingMachine_no_arg.getItems().get(0));
        assertEquals(item2,vendingMachine_no_arg.getItems().get(1));
    }

    /**
     * Given there are items in stock
     * When I check what is available
     * Then I should see the name, price and code of the items
     */
    @Test
    public void checkAvailableItemsInStock_Customer() {
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_no_arg.addItemsToTheStock(item1);
        vendingMachine_no_arg.addItemsToTheStock(item2);
        List<String> expectedItemList = new ArrayList<>();
        expectedItemList.add("Item{itemCode=1, itemName='Chocolate', price=4.99}");
        expectedItemList.add("Item{itemCode=2, itemName='Coca-Cola', price=2.49}");
        assertEquals(expectedItemList.get(0),vendingMachine_no_arg.getItems().get(0).toString());
        assertEquals(expectedItemList.get(1),vendingMachine_no_arg.getItems().get(1).toString());

    }

    /**
     * Given I have inserted some amount of money
     * When I enter a non-existent item code
     * Then the vending machine shows no such item
     */
    @Test
    public void checkIfNonExistentItemInStock_Customer() {
        assertTrue(customer_balance_arg.checkIfCustomerHasBalance());
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_customer_arg.addItemsToTheStock(item1);
        vendingMachine_customer_arg.addItemsToTheStock(item2);
        assertEquals("no such item",vendingMachine_customer_arg.checkIfItemExists(3));
    }

    /**
     * Given I have inserted some amount of money
     * When I enter an item code of an item that costs more that I have inserted
     * Then the vending machine shows insufficient fund
     */
    @Test
    public void insufficientFundWhenBuyingAnItem_Customer() {
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_customer_arg.addItemsToTheStock(item1);
        vendingMachine_customer_arg.addItemsToTheStock(item2);
        assertEquals("insufficient fund",vendingMachine_customer_arg.checkInsufficientFund(1));
    }

    /**
     * Given I have inserted some amount of money
     * When I enter an item code of an item that costs less that I have inserted
     * Then I get the item and the change
     */
    @Test
    public void getItemAndChangeIfHadBalance_Customer() {
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_customer_arg.addItemsToTheStock(item1);
        vendingMachine_customer_arg.addItemsToTheStock(item2);
        Map<Item, BigDecimal> changeMap = new HashMap<>();
        MathContext mc = new MathContext(2);
        changeMap.put(item2,new BigDecimal(0.51).round(mc));
        assertEquals(changeMap.get(item2),vendingMachine_customer_arg.getItemAndChange(2).get(item2));
        assertEquals(changeMap,vendingMachine_customer_arg.getItemAndChange(2));
    }

    /**
     * Given I have inserted some amount of money
     * When I press the cancel button
     * Then I get the full amount refunded
     */
    @Test
    public void cancelItemFullAmountRefunded_Customer() {
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_customer_arg.addItemsToTheStock(item1);
        vendingMachine_customer_arg.addItemsToTheStock(item2);
        assertEquals(3,vendingMachine_customer_arg.cancelTransaction(),0);
    }

    /**
     * Given there are some balance
     * When I check the balance of the vending machine
     * Then I should see the current balance
     */
    @Test
    public void checkBalanceOfVendingMachine_Administrator() {
        assertEquals(1000,vendingMachine_balance_arg.getBalance(),0);
    }

    /**
     * Given I have inserted some money into the vending machine
     * When I check amount inserted
     * Then I should see the amount
     */
    @Test
    public void checkBalance_Customer() {
        assertEquals(3,customer_balance_arg.getBalance(),0);
    }

    /**
     * When I withdraw money from the vending machine
     * Then I should see the balance decrease
     */
    @Test
    public void withdrawMoneyFromMachine_Administrator() {
        administrator.withdrawMoney(100);
        assertEquals(900, vendingMachine_balance_arg.getBalance(),0);
    }

    /**
     *When I deposit money to the vending machine
     * Then I should see the balance increase
     */
    @Test
    public void depositMoneyFromMachine_Administrator() {
        administrator.depositMoney(200);
        assertEquals(1200, vendingMachine_balance_arg.getBalance(),0);
    }

    /**
     * Given I have inserted enough money for an item
     * When I enter the item code
     * Then I get the item
     * And the balance of the vending machine goes up by that amount
     * And the item is removed from the stock
     */
    @Test
    public void purchaseAnItem_Customer() {
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine_customer_arg.addItemsToTheStock(item1);
        vendingMachine_customer_arg.addItemsToTheStock(item2);
        assertEquals(item1,vendingMachine_customer_arg.getItemByItemCode(1));
        assertEquals(1,vendingMachine_customer_arg.getItems().size());
        assertEquals(4.99,vendingMachine_customer_arg.getBalance(),0);

    }
}

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

    @Test
    public void checkNoItemsInStockIsEmpty_Administrator() {
        VendingMachine vendingMachine = new VendingMachine();
        assertTrue(vendingMachine.checkItemsIsEmpty());
    }

    @Test
    public void addItemsToVendingMachine_Administrator() {
        VendingMachine vendingMachine = new VendingMachine();
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine.addItemsToTheStock(item1);
        vendingMachine.addItemsToTheStock(item2);
        assertEquals(item1,vendingMachine.getItems().get(0));
        assertEquals(item2,vendingMachine.getItems().get(1));
    }

    @Test
    public void checkAvailableItemsInStock_Customer() {
        VendingMachine vendingMachine = new VendingMachine();
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine.addItemsToTheStock(item1);
        vendingMachine.addItemsToTheStock(item2);
        List<String> expectedItemList = new ArrayList<>();
        expectedItemList.add("Item{itemCode=1, itemName='Chocolate', price=4.99}");
        expectedItemList.add("Item{itemCode=2, itemName='Coca-Cola', price=2.49}");
        assertEquals(expectedItemList.get(0),vendingMachine.getItems().get(0).toString());
        assertEquals(expectedItemList.get(1),vendingMachine.getItems().get(1).toString());

    }

    @Test
    public void checkIfNonExistentItemInStock_Customer() {
        Customer customer = new Customer(100);
        VendingMachine vendingMachine = new VendingMachine(customer);
        assertTrue(customer.checkIfCustomerHasBalance());
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine.addItemsToTheStock(item1);
        vendingMachine.addItemsToTheStock(item2);
        assertEquals("no such item",vendingMachine.checkIfItemExists(3));
    }

    @Test
    public void insufficientFundWhenBuyingAnItem_Customer() {
        Customer customer = new Customer(3);
        VendingMachine vendingMachine = new VendingMachine(customer);
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine.addItemsToTheStock(item1);
        vendingMachine.addItemsToTheStock(item2);
        assertEquals("insufficient fund",vendingMachine.checkInsufficientFund(1));
    }

    @Test
    public void getItemAndChangeIfHadBalance_Customer() {
        Customer customer = new Customer(3);
        VendingMachine vendingMachine = new VendingMachine(customer);
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine.addItemsToTheStock(item1);
        vendingMachine.addItemsToTheStock(item2);
        Map<Item, BigDecimal> changeMap = new HashMap<>();
        MathContext mc = new MathContext(2);
        changeMap.put(item2,new BigDecimal(0.51).round(mc));
        assertEquals(changeMap.get(item2),vendingMachine.getItemAndChange(2).get(item2));
        assertEquals(changeMap,vendingMachine.getItemAndChange(2));
    }

    @Test
    public void cancelItemFullAmountRefunded_Customer() {
        Customer customer = new Customer(3);
        VendingMachine vendingMachine = new VendingMachine(customer);
        Item item1 = new Item(1,"Chocolate",4.99);
        Item item2 = new Item(2, "Coca-Cola", 2.49);
        vendingMachine.addItemsToTheStock(item1);
        vendingMachine.addItemsToTheStock(item2);
        assertEquals(3,vendingMachine.cancelTransaction(),0);
    }

}

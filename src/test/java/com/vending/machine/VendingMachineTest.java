package com.vending.machine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingMachineTest {

    @Test
    public void checkNoItemsInStockIsEmpty() {
        VendingMachine vendingMachine = new VendingMachine();
        assertTrue(vendingMachine.checkItemsIsEmpty());
    }

    @Test
    public void addItemsToVendingMachine() {
        VendingMachine vendingMachine = new VendingMachine();
        String expectedItemName = "Chocolate";
        String expectedItemName1 = "Coca-Cola";
        vendingMachine.addItemsToTheStock(expectedItemName);
        vendingMachine.addItemsToTheStock(expectedItemName1);
        assertEquals(expectedItemName,vendingMachine.getItems().get(0));
        assertEquals(expectedItemName1,vendingMachine.getItems().get(1));
    }
}

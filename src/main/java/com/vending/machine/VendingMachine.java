package com.vending.machine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<String> items = new ArrayList<>();

    public boolean checkItemsIsEmpty() {
        return items.isEmpty();
    }

    public void addItemsToTheStock(String itemName) {
        items.add(itemName);
    }

    public List<String> getItems() {
        return items;
    }
}

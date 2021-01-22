package com.vending.machine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Item> items = new ArrayList<>();

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
}

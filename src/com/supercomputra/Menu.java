package com.supercomputra;

import java.util.ArrayList;
import java.util.HashMap;

class Menu<T extends Menu.Item> {
    static class Item {
        String id;
        String name;
        Integer price;
    }

    static class SpecialItem extends Item {
        Integer discount;
    }

    private final ArrayList<T> menuItems;
    private final HashMap<String, Integer> menuLookups;

    Menu() {
        this.menuItems = new ArrayList<>();
        this.menuLookups = new HashMap<>();
    }

    ArrayList<T> getMenuItems() {
        return this.menuItems;
    }

    void addMenuItem(T item) throws DuplicateInstanceException {
        if (menuLookups.containsKey(item.id)) {
            throw new DuplicateInstanceException();
        }
        menuItems.add(item);
        menuLookups.put(item.id, menuItems.size() - 1);
    }

    void removeMenuItemWith(String id) throws NotFoundException {
        if (menuLookups.containsKey(id)) {
            Integer index = menuLookups.get(id);
            this.menuItems.remove(index);
            this.menuLookups.remove(id);
        } else {
            throw new NotFoundException();
        }
    }

    Boolean isEmpty() {
        return menuItems.isEmpty();
    }
}

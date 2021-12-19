package com.supercomputra;

import java.util.ArrayList;
import java.util.Vector;

class Menu<T extends Menu.Item> {
    static class Item {
        String id;
        String name;
        Integer price;
    }

    static class SpecialItem extends Item {
        Integer discount;
    }

    private final Vector<T> menuItems;

    Menu() {
        menuItems = new Vector<T>();
    }

    Vector<T> getMenuItems() {
        return menuItems;
    }

    private Integer indexForItemWith(String id) {
        for (Integer i = 0; i < menuItems.size(); i++) {
            T item = menuItems.get(i);
            if (item.id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    void addMenuItem(T item) throws DuplicateInstanceException {
        Integer index = indexForItemWith(item.id);
        if (index >= 0) {
            throw new DuplicateInstanceException();
        }

        menuItems.add(item);
    }

    void removeMenuItemWith(String id) throws NotFoundException {
        Integer index = indexForItemWith(id);
        if (index >= 0) {
            menuItems.removeElementAt(index);
        } else {
            throw new NotFoundException();
        }
    }

    Boolean isEmpty() {
        return menuItems.isEmpty();
    }
}

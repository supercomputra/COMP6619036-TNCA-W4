package com.supercomputra;

import java.util.*;

public class Application {
    final String title;
    private final Menu<Menu.Item> regularMenu;
    private final Menu<Menu.SpecialItem> specialMenu;

    Application(String title) {
        this.title = title;
        this.regularMenu = new Menu<Menu.Item>();
        this.specialMenu = new Menu<Menu.SpecialItem>();
    }

    static Set<Integer> defaultDiscounts() {
        return new HashSet<>(){{
            add(10);
            add(25);
            add(50);
        }};
    }

    void addRegularMenu() {
        System.out.println("Add Regular Menu");
        System.out.println(Application.separator());

        // Create menu item
        Menu.Item item = new Menu.Item();
        item.id = InputUtility.getString("Input Menu Code", 4, 4, "R");
        item.name = InputUtility.getString("Input Name", 5, 20, "");
        item.price = InputUtility.getInteger("Input Price", 10000, 100000);

        // Add item to the menu
        try {
            regularMenu.addMenuItem(item);
            System.out.println("Add Regular Menu Success!");
        } catch (DuplicateInstanceException exception) {
            System.out.println(exception.getMessage());
            addRegularMenu();
        }
    }

    void addSpecialMenu() {
        System.out.println("Add Special Menu");
        System.out.println(Application.separator());

        // Create menu item
        Menu.SpecialItem item = new Menu.SpecialItem();
        item.id = InputUtility.getString("Input Menu Code", 4, 4, "S");
        item.name = InputUtility.getString("Input Name", 5, 20, "");
        item.price = InputUtility.getInteger("Input Price", 10000, 100000);
        item.discount = InputUtility.getInteger("Input Menu Discount", Application.defaultDiscounts());

        // Add item to the menu
        try {
            specialMenu.addMenuItem(item);
            System.out.println("Add Special Menu Success!");
        } catch (DuplicateInstanceException exception) {
            System.out.println(exception.getMessage());
            addSpecialMenu();
        }
    }

    void presentAllMenu() {
        if (!regularMenu.isEmpty()) {
            System.out.println("Regular Menu");
            Table regularMenuTable = new Table();
            regularMenuTable.setShowVerticalLines(true);
            regularMenuTable.setHeaders("No.", "Code", "Name", "Price");
            Integer regularMenuTableCount = 0;
            for (Menu.Item item: regularMenu.getMenuItems()) {
                regularMenuTableCount++;
                regularMenuTable.addRow(regularMenuTableCount.toString(), item.id, item.name, item.price.toString());
            }
            regularMenuTable.print();
        }

        if (!specialMenu.isEmpty()) {
            System.out.println("Special Menu");
            Table specialMenuTable = new Table();
            specialMenuTable.setShowVerticalLines(true);
            specialMenuTable.setHeaders("No.", "Code", "Name", "Price", "Discount");
            Integer specialMenuTableCount = 0;
            for (Menu.SpecialItem item: specialMenu.getMenuItems()) {
                specialMenuTableCount++;
                specialMenuTable.addRow(specialMenuTableCount.toString(), item.id, item.name, item.price.toString(), item.discount.toString());
            }
            specialMenuTable.print();
        }
    }

    void removeRegularMenu() {
        System.out.println("Remove Regular Menu");
        System.out.println(Application.separator());
        String id = InputUtility.getString("Input Menu Code", 4, 4, "R");
        try {
            regularMenu.removeMenuItemWith(id);
            System.out.println("Regular Menu Successfully Removed!");
        } catch (NotFoundException exception) {
            System.out.println(exception.getMessage());
            removeRegularMenu();
        }
    }

    void removeSpecialMenu() {
        System.out.println("Remove Special Menu");
        System.out.println(Application.separator());
        String id = InputUtility.getString("Input Menu Code", 4, 4, "S");
        try {
            specialMenu.removeMenuItemWith(id);
            System.out.println("Special Menu Successfully Removed!");
        } catch (NotFoundException exception) {
            System.out.println(exception.getMessage());
            removeRegularMenu();
        }

    }

    static String separator() {
        return "==================================";
    }

    void run() {
        System.out.println(this.title);
        System.out.println(Application.separator());
        ArrayList<String> options = new ArrayList<>();
        options.add("1. Add Regular Menu");
        options.add("2. Add Special Menu");
        options.add("3. Show All Menu");
        options.add("4. Remove Regular Menu");
        options.add("5. Remove Special Menu");
        options.add("6. Exit");
        for (String option: options) {
            System.out.println(option);
        }

        Boolean selected = false;
        Integer option = 6;
        do {
            String query = String.format("Choice [%d-%d]: ", 1, options.size());
            System.out.print(query);
            Scanner scanner= new Scanner(System.in);
            option = scanner.nextInt();
            if (option <= options.size() && option >= 1) {
                selected = true;
            }
        } while (!selected);

        switch (option) {
            case 1:
                this.addRegularMenu();
                run();
            case 2:
                this.addSpecialMenu();
                run();
            case 3:
                this.presentAllMenu();
                run();
            case 4:
                this.removeRegularMenu();
                run();
            case 5:
                this.removeSpecialMenu();
                run();
            default:
                return;
        }
    }
}

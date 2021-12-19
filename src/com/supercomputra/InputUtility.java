package com.supercomputra;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class InputUtility {
    static Integer getInteger(String message, Set<Integer> options) {
        Boolean selected = false;
        Integer result = Integer.MIN_VALUE;
        do {
            // Print query
            Set<String> stringOptions = new HashSet<String>();
            for (Integer option: options) {
                stringOptions.add(Integer.toString(option));
            }

            String flattenedOptions = String.join(" | ", stringOptions);
            String query = String.format("%s [%s]: ", message, flattenedOptions);
            System.out.print(query);

            // Get input
            Scanner scanner= new Scanner(System.in);


            try {
                result = scanner.nextInt();
                if (options.contains(result)) {
                    selected = true;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Expecting an integer input. Try again!");
            }

        } while (!selected);
        return result;
    }

    static Integer getInteger(String message, Integer lowerBound, Integer upperBound) {
        Boolean selected = false;
        Integer result = Integer.MIN_VALUE;
        do {
            // Print query
            String query = String.format("%s [%d-%d]: ", message, lowerBound, upperBound);
            System.out.print(query);

            // Get input
            Scanner scanner= new Scanner(System.in);
            try {
                result = scanner.nextInt();
                if (result <= upperBound && result >= lowerBound) {
                    selected = true;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Expecting an integer input. Try again!");
            }
        } while (!selected);
        return result;
    }


    static String getString(String message, Integer lowerBound, Integer upperBound, String prefix) {
        Boolean selected = false;
        String result;
        do {
            // Print query
            String query;
            if (prefix.length() > 0) {
                query = String.format("%s [%s...]: ", message, prefix);
            } else {
                query = String.format("%s [%d-%d]: ", message, lowerBound, upperBound);
            }
            System.out.print(query);

            // Get input
            Scanner scanner= new Scanner(System.in);
            result = scanner.nextLine();
            if (result.length() <= upperBound && result.length() >= lowerBound) {
                if (prefix.length() > 0) {
                    selected = result.startsWith(prefix);
                } else {
                    selected = true;
                }
            }
        } while (!selected);
        return result;
    }
}

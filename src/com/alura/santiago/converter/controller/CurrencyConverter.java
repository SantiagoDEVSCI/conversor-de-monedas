package com.alura.santiago.converter.controller;

import com.google.gson.Gson;
import com.alura.santiago.converter.model.Currency;
import com.alura.santiago.converter.model.CurrencyAPI;
import com.alura.santiago.converter.service.CurrencyAPIService;

import java.util.Scanner;

public class CurrencyConverter {
    private static final Scanner scanner = new Scanner(System.in);
    private final CurrencyAPIService currencyAPIService = new CurrencyAPIService();

    public void startConversion() {
        int option;

        do {
            displayMenu();
            option = getOption();

            if (option != 7) {
                performConversion(option);
            }
        } while (option != 7);

        System.out.println("Thank you for using the currency converter. Goodbye!");
    }

    private void displayMenu() {
        System.out.println("********************************************");
        System.out.println("Welcome to the Currency Converter =]");
        System.out.println("1) Dollar => Argentine Peso");
        System.out.println("2) Argentine Peso => Dollar");
        System.out.println("3) Dollar => Brazilian Real");
        System.out.println("4) Brazilian Real => Dollar");
        System.out.println("5) Dollar => Colombian Peso");
        System.out.println("6) Colombian Peso => Dollar");
        System.out.println("7) Exit");
        System.out.print("Please choose a valid option: ");
    }

    private int getOption() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void performConversion(int option) {
        String baseCurrency;
        String targetCurrency;

        switch (option) {
            case 1 -> { baseCurrency = "USD"; targetCurrency = "ARS"; }
            case 2 -> { baseCurrency = "ARS"; targetCurrency = "USD"; }
            case 3 -> { baseCurrency = "USD"; targetCurrency = "BRL"; }
            case 4 -> { baseCurrency = "BRL"; targetCurrency = "USD"; }
            case 5 -> { baseCurrency = "USD"; targetCurrency = "COP"; }
            case 6 -> { baseCurrency = "COP"; targetCurrency = "USD"; }
            default -> {
                System.out.println("Invalid option. Please try again.");
                return;
            }
        }

        try {
            System.out.printf("Enter the amount of %s to convert: ", baseCurrency);
            double amount = getAmount();
            CurrencyAPI currencyAPI = currencyAPIService.fetchCurrency(baseCurrency, targetCurrency, amount);
            Currency currency = new Currency(currencyAPI);
            System.out.println(currency);
        } catch (Exception e) {
            System.out.println("Error performing conversion: " + e.getMessage());
        }
    }

    private double getAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Please enter a positive value.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
package com.alura.santiago.converter;

import com.alura.santiago.converter.controller.CurrencyConverter;

public class MainConverter {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.startConversion();
    }
}
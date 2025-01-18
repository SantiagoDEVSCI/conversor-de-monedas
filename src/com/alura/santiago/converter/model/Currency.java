package com.alura.santiago.converter.model;

import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("base_code")
    private String baseCurrency;
    @SerializedName("target_code")
    private String targetCurrency;
    @SerializedName("conversion_result")
    private double conversionResult;

    public Currency(String baseCurrency, String targetCurrency, double conversionResult) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionResult = conversionResult;
    }

    public Currency(CurrencyAPI currencyAPI) {
        this.baseCurrency = currencyAPI.base_code();
        this.targetCurrency = currencyAPI.target_code();
        this.conversionResult = currencyAPI.conversion_result();
    }

    @Override
    public String toString() {
        return String.format("Conversion from %s to %s: %.2f", baseCurrency, targetCurrency, conversionResult);
    }
}
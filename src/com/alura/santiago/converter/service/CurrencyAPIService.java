package com.alura.santiago.converter.service;

import com.google.gson.Gson;
import com.alura.santiago.converter.model.CurrencyAPI;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyAPIService {
    public CurrencyAPI fetchCurrency(String baseCurrency, String targetCurrency, double amount) {
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/f860b9d75546c819e945082b/pair/" + baseCurrency + "/" + targetCurrency + "/" + amount);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CurrencyAPI.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching currency data: " + e.getMessage());
        }
    }
}
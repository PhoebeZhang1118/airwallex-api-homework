package com.airwallex.api;

import com.airwallex.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {
    public static Response getFxRate(String buyCurrency, String sellCurrency, Double buyAmount, String authToken) {
        return RestAssured.given()
                .baseUri(Config.getBaseUrl())
                .basePath(Config.getRatesEndpoint())
                .header("Authorization", "Bearer " + authToken)
                .queryParam("buy_currency", buyCurrency)
                .queryParam("sell_currency", sellCurrency)
                .queryParam("buy_amount", buyAmount)
                .get();
    }

    public static Response getFxRate(String buyCurrency, String sellCurrency, String authToken) {
        return RestAssured.given()
                .baseUri(Config.getBaseUrl())
                .basePath(Config.getRatesEndpoint())
                .header("Authorization", "Bearer " + authToken)
                .queryParam("buy_currency", buyCurrency)
                .queryParam("sell_currency", sellCurrency)
                .get();
    }

    public static Response getFxRateWithInvalidAmount(String buyCurrency,
                                                      String sellCurrency,
                                                      String invalidAmount,
                                                      String authToken) {
        return RestAssured.given()
                .baseUri(Config.getBaseUrl())
                .basePath(Config.getRatesEndpoint())
                .header("Authorization", "Bearer " + authToken)
                .queryParam("buy_currency", buyCurrency)
                .queryParam("sell_currency", sellCurrency)
                .queryParam("buy_amount", invalidAmount)
                .get();
    }
}

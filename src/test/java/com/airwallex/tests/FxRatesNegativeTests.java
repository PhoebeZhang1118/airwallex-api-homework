package com.airwallex.tests;

import com.airwallex.actions.FxRatesActions;
import com.airwallex.api.ApiClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FxRatesNegativeTests extends BaseTest {
    protected FxRatesActions fxRatesActions;
    @BeforeMethod
    public void init(){
        fxRatesActions = new FxRatesActions();
    }
    @Test(description = "Test invalid buy currency")
    public void testInvalidBuyCurrency() {
        Response response = ApiClient.getFxRate("XYZ", "USD", authToken);
        fxRatesActions.verifyErrorResponse(response, "invalid_argument",
                "buy_currency");
    }

    @Test(description = "Test invalid sell currency")
    public void testInvalidSellCurrency() {
        Response response = ApiClient.getFxRate("AUD", "XYZ", authToken);
        fxRatesActions.verifyErrorResponse(response, "invalid_argument",
                "sell_currency");
    }

    @Test(description = "Test both currencies invalid")
    public void testBothCurrenciesInvalid() {
        Response response = ApiClient.getFxRate("XYZ", "ABC", authToken);
        fxRatesActions.verifyCurrencyErrorResponse(response);
    }

    @Test(description = "Test missing buy currency parameter")
    public void testMissingBuyCurrency() {
        Response response = ApiClient.getFxRate("", "USD", authToken);
        fxRatesActions.verifyErrorResponse(response, "invalid_argument",
                "buy_currency");
    }

    @Test(description = "Test invalid currency format (2 letters)")
    public void testInvalidCurrencyFormat() {
        Response response = ApiClient.getFxRate("US", "AUD", authToken);
        fxRatesActions.verifyErrorResponse(response,
                "invalid_argument",
                "buy_currency");
    }

    @Test(description = "Test invalid amount (non-number)")
    public void testInvalidAmount() {
        Response response = ApiClient.getFxRateWithInvalidAmount("AUD", "USD", "abc", authToken);
        fxRatesActions.verifyErrorResponse(response,
                "invalid_argument",
                "");
    }


}
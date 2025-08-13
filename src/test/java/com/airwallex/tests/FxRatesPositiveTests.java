package com.airwallex.tests;

import com.airwallex.actions.FxRatesActions;
import com.airwallex.api.ApiClient;
import com.airwallex.models.FxRateResponse;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FxRatesPositiveTests extends BaseTest {
    protected FxRatesActions fxRatesActions;
    @BeforeMethod
    public void init(){
        fxRatesActions = new FxRatesActions();
    }
    @Test(description = "Test valid currency pair AUD/USD with amount")
    public void testValidAudUsdWithAmount() {
        Response response = ApiClient.getFxRate("AUD", "USD", 100.0, authToken);
        FxRateResponse fxRateResponse = response.as(FxRateResponse.class);

        fxRatesActions.verifyResponseStatusCode(response, 200);
        fxRatesActions.verifyCurrencyPair(fxRateResponse, "AUD", "USD");
        fxRatesActions.verifyPositiveRateValue(fxRateResponse);
        fxRatesActions.verifyResponseStructure(fxRateResponse);
        fxRatesActions.verifyRateDetailsCalculation(fxRateResponse);
    }

    @Test(description = "Test valid currency pair HKD/EUR with amount")
    public void testValidHkdEurWithAmount() {
        Response response = ApiClient.getFxRate("HKD", "EUR", 500.0, authToken);
        FxRateResponse fxRateResponse = response.as(FxRateResponse.class);

        fxRatesActions.verifyResponseStatusCode(response, 200);
        fxRatesActions.verifyCurrencyPair(fxRateResponse, "HKD", "EUR");
        fxRatesActions.verifyPositiveRateValue(fxRateResponse);
        fxRatesActions.verifyResponseStructure(fxRateResponse);
        fxRatesActions.verifyRateDetailsCalculation(fxRateResponse);
    }

    @Test(description = "Test valid currency pair CNY/SGD with amount")
    public void testValidCnySgdWithAmount() {
        Response response = ApiClient.getFxRate("CNY", "SGD", 1000.0, authToken);
        FxRateResponse fxRateResponse = response.as(FxRateResponse.class);

        fxRatesActions.verifyResponseStatusCode(response, 200);
        fxRatesActions.verifyCurrencyPair(fxRateResponse, "CNY", "SGD");
        fxRatesActions.verifyPositiveRateValue(fxRateResponse);
        fxRatesActions.verifyResponseStructure(fxRateResponse);
        fxRatesActions.verifyRateDetailsCalculation(fxRateResponse);
    }

    @Test(description = "Test response structure validation")
    public void testResponseStructureValidation() {
        Response response = ApiClient.getFxRate("AUD", "USD", 100.0, authToken);
        FxRateResponse fxRateResponse = response.as(FxRateResponse.class);

        fxRatesActions.verifyResponseStatusCode(response, 200);
        fxRatesActions.verifyResponseStructure(fxRateResponse);
        fxRatesActions.verifyRateDetailsCalculation(fxRateResponse);
    }
}
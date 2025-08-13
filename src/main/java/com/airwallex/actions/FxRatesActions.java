package com.airwallex.actions;

import com.airwallex.models.FxRateResponse;
import io.restassured.response.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class FxRatesActions {

    public void verifyResponseStatusCode(Response response, int expectedStatusCode) {
        assertEquals(response.statusCode(), expectedStatusCode,
                String.format("Expected status code %d but got %d", expectedStatusCode, response.statusCode()));
    }

    public void verifyResponseStructure(FxRateResponse fxRateResponse) {
        assertThat(fxRateResponse.getBuyCurrency(), not(emptyOrNullString()));
        assertThat(fxRateResponse.getSellCurrency(), not(emptyOrNullString()));
        assertThat(fxRateResponse.getConversionDate(), not(emptyOrNullString()));
        assertThat(fxRateResponse.getCreatedAt(), not(emptyOrNullString()));
        assertThat(fxRateResponse.getCurrencyPair(), not(emptyOrNullString()));
        assertThat(fxRateResponse.getDealtCurrency(), not(emptyOrNullString()));
        assertThat(fxRateResponse.getRate(), notNullValue());
        assertThat(fxRateResponse.getRateDetails(), not(empty()));
    }

    public void verifyCurrencyPair(FxRateResponse fxRateResponse, String expectedBuyCurrency, String expectedSellCurrency) {
        assertThat(fxRateResponse.getBuyCurrency(), equalTo(expectedBuyCurrency));
        assertThat(fxRateResponse.getSellCurrency(), equalTo(expectedSellCurrency));
        assertThat(fxRateResponse.getCurrencyPair(), containsString(expectedBuyCurrency));
        assertThat(fxRateResponse.getCurrencyPair(), containsString(expectedSellCurrency));
    }

    public void verifyRateDetailsCalculation(FxRateResponse fxRateResponse) {
        FxRateResponse.RateDetail rateDetail = fxRateResponse.getRateDetails().get(0);
        double expectedSellAmount;
        if (rateDetail.getRate() >1){
            expectedSellAmount = roundWithBigDecimal(rateDetail.getBuyAmount() / rateDetail.getRate());
        } else {
            expectedSellAmount = roundWithBigDecimal(rateDetail.getBuyAmount() * rateDetail.getRate());
        }
        double actualSellAmount = roundWithBigDecimal(rateDetail.getSellAmount());

        assertThat(actualSellAmount, equalTo(expectedSellAmount));
    }

    public void verifyPositiveRateValue(FxRateResponse fxRateResponse) {
        assertThat(fxRateResponse.getRate(), greaterThan(0.0));
    }

    public void verifyErrorResponse(Response response,
                                    String expectedCode,
                                    String expectedSource) {
        verifyResponseStatusCode(response, 400);
        response.then()
                .body("code", equalTo(expectedCode))
                .body("source", equalTo(expectedSource))
                .body("message", not(emptyOrNullString()));
    }

    public void verifyCurrencyErrorResponse(Response response) {
        verifyResponseStatusCode(response, 400);
        response.then()
                .body("code", equalTo("invalid_argument"))
                .body("source", anyOf(
                        equalTo("buy_currency"),
                        equalTo("sell_currency")))
                .body("message", not(emptyOrNullString()));
    }

    public double roundWithBigDecimal(double value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
package com.airwallex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FxRateResponse {
    @JsonProperty("buy_currency")
    private String buyCurrency;

    @JsonProperty("conversion_date")
    private String conversionDate;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("currency_pair")
    private String currencyPair;

    @JsonProperty("dealt_currency")
    private String dealtCurrency;

    private double rate;

    @JsonProperty("rate_details")
    private List<RateDetail> rateDetails;

    @JsonProperty("sell_currency")
    private String sellCurrency;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RateDetail {
        @JsonProperty("buy_amount")
        private double buyAmount;

        private String level;
        private double rate;

        @JsonProperty("sell_amount")
        private double sellAmount;
    }
}


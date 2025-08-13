package com.airwallex.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("api.baseUrl");
    }

    public static String getAuthEndpoint() {
        return properties.getProperty("api.authEndpoint");
    }

    public static String getRatesEndpoint() {
        return properties.getProperty("api.ratesEndpoint");
    }

    public static String getClientId() {
        return properties.getProperty("auth.clientId");
    }

    public static String getApiKey() {
        return properties.getProperty("auth.apiKey");
    }
}

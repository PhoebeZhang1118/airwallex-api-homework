package com.airwallex.tests;
import com.airwallex.api.AuthManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(com.airwallex.listeners.TestListener.class)
public class BaseTest {
    protected static String authToken;

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://api-demo.airwallex.com";
        authToken = AuthManager.getAuthToken();
    }
}

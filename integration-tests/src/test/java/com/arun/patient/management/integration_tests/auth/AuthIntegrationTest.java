package com.arun.patient.management.integration_tests.auth;

import com.arun.patient.management.integration_tests.util.IntegrationTestUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = IntegrationTestUtility.API_GATEWAY_URL;
    }

    @Test
    public void shouldReturnOKWithValidToken() {
        // Arrange
        // Act
        // assert

        String loginPayload = """
                 {
                   "email": "testuser@test.com",
                   "password": "password123"
                 }
                """;
        Response response = given()
                .contentType("application/json")
                .body(loginPayload)  // up to Arrange
                .when()
                .post("/auth/login")  // Act
                .then()
                .statusCode(200)   // Assert
                .body("token", notNullValue())  // Assert
                .extract() // extract the response
                .response();  // returning response

        System.out.println("Generated Token: "+ response.jsonPath().get("token"));
    }

    @Test
    public void shouldReturnUnAuthorizedOnInvalidLogin() {
        String loginPayload = """
                 {
                   "email": "invalid@test.com",
                   "password": "invalid"
                 }
                """;
        given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}

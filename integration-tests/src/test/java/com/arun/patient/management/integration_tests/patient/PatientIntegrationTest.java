package com.arun.patient.management.integration_tests.patient;

import com.arun.patient.management.integration_tests.util.IntegrationTestUtility;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = IntegrationTestUtility.API_GATEWAY_URL;
    }

    @Test
    public void shouldReturnPatientsWithValidToken() {
        String loginPayload = IntegrationTestUtility.LOGIN_PAYLOAD;

        String token = given()
                .contentType("application/json").body(loginPayload)
                .when().post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token");
        given()
                .header("Authorization", "Bearer "+token)
                .when().get("/api/patients")
                .then()
                .statusCode(200)
                .body("patients", notNullValue());
    }
}

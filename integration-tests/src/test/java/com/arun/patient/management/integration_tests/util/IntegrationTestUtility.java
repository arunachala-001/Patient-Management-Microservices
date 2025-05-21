package com.arun.patient.management.integration_tests.util;

public class IntegrationTestUtility {

    public static final String API_GATEWAY_URL = "http://localhost:8888";

    public static final String LOGIN_PAYLOAD = """
                 {
                   "email": "testuser@test.com",
                   "password": "password123"
                 }
                """;
}

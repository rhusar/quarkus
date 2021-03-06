package io.quarkus.it.main;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class HealthTestCase {

    @Test
    public void testHealthCheck() {
        try {
            RestAssured.when().get("/health/live").then()
                    .contentType(ContentType.JSON)
                    .header("Content-Type", containsString("charset=UTF-8"))
                    .body("status", is("UP"),
                            "checks.status", containsInAnyOrder("UP", "UP"),
                            "checks.name", containsInAnyOrder("basic", "basic-with-builder"));

            RestAssured.when().get("/health/ready").then()
                    .contentType(ContentType.JSON)
                    .header("Content-Type", containsString("charset=UTF-8"))
                    .body("status", is("UP"),
                            "checks.status", containsInAnyOrder("UP"),
                            "checks.name", containsInAnyOrder("Database connection(s) health check"));
        } finally {
            RestAssured.reset();
        }
    }
}

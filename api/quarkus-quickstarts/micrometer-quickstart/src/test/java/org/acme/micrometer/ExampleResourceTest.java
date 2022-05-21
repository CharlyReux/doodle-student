package org.acme.microprofile.metrics;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    void testGaugeExample() {
        when().get("/example/gauge/1").then().statusCode(200);
        when().get("/example/gauge/2").then().statusCode(200);
        when().get("/example/gauge/4").then().statusCode(200);
        when().get("/q/metrics").then().statusCode(200)
                .body(containsString(
                        "example_list_size 2.0"));
        when().get("/example/gauge/6").then().statusCode(200);
        when().get("/example/gauge/5").then().statusCode(200);
        when().get("/example/gauge/7").then().statusCode(200);
        when().get("/q/metrics").then().statusCode(200)
                .body(containsString(
                        "example_list_size 1.0"));
    }

    @Test
    void testCounterExample() {
        when().get("/example/prime/-1").then().statusCode(200);
        when().get("/example/prime/0").then().statusCode(200);
        when().get("/example/prime/1").then().statusCode(200);
        when().get("/example/prime/2").then().statusCode(200);
        when().get("/example/prime/3").then().statusCode(200);
        when().get("/example/prime/15").then().statusCode(200);

        when().get("/q/metrics").then().statusCode(200)
                .body(containsString(
                        "example_prime_number_total{type=\"prime\",}"))
                .body(containsString(
                        "example_prime_number_total{type=\"not-prime\",}"))
                .body(containsString(
                        "example_prime_number_total{type=\"one\",}"))
                .body(containsString(
                        "example_prime_number_total{type=\"even\",}"))
                .body(containsString(
                        "example_prime_number_total{type=\"not-natural\",}"));
    }

    @Test
    void testTimerExample() {
        when().get("/example/prime/257").then().statusCode(200);
        when().get("/q/metrics").then().statusCode(200)
                .body(containsString(
                        "example_prime_number_test_seconds_sum"))
                .body(containsString(
                        "example_prime_number_test_seconds_max"))
                .body(containsString(
                        "example_prime_number_test_seconds_count 1.0"));
        when().get("/example/prime/7919").then().statusCode(200);
        when().get("/q/metrics").then().statusCode(200)
                .body(containsString(
                        "example_prime_number_test_seconds_count 2.0"));
    }
}

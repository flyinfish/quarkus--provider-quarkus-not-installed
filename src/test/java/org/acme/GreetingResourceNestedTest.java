package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class GreetingResourceNestedTest {

    @Nested
    class GET {
        @Test
        void testHelloEndpoint() {
            given().when().get("/hello").then().statusCode(200).body(is("Hello from Quarkus REST"));
        }
    }
}
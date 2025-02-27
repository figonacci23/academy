package com.ctw.workstation.resource;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeamResourceTest {
    @Test
    @Order(1)
    void createTeam() {
        String team = "{ \"product\": \"prodtest\", \"name\": \"nametest\", \"defaultLocation\": \"test\" }";
        given()
                .contentType(ContentType.JSON)
                .body(team).when().post("/workstation/teams").then()
                .statusCode(201)  // Expect a 201 status code.
                .body("id", equalTo(1))
                .body("product", equalTo("prodtest"))
                .body("name", equalTo("nametest"))
                .body("defaultLocation", equalTo("test"));
    }

    @Test
    @Order(2)
    void testTeamCount() {
        Log.info("Testing team count");
        given().when().get("/workstation/teams/count").then().statusCode(200).body(equalTo("1"));
    }

    @Test
    @Order(3)
    void testGetTeam() {
        Log.info("Testing Get Team");
        given()
                .contentType(ContentType.JSON)
                .body(1).when().get("/workstation/teams/1").then()
                .statusCode(200)
                .body("id", equalTo(1))  // Assert that the 'id' is 1.
                .body("product", equalTo("prodtest"))  // Assert that the 'product' field is 'prodtest'.
                .body("name", equalTo("nametest"))  // Assert that the 'name' field is 'nametest'.
                .body("defaultLocation", equalTo("test"));
    }

    @Test
    @Order(4)
    void testUpdateTeam() {
        Log.info("Testing Update Team");
        String team = "{ \"product\": \"prodtest\", \"name\": \"asdasdasd\", \"defaultLocation\": \"test\" }";
        given()
        .contentType(ContentType.JSON)
                .param("id", 1)
                .body(team).when().put("/workstation/teams/1").then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("product", equalTo("prodtest"))
                .body("name", equalTo("asdasdasd"))
                .body("defaultLocation", equalTo("test"));
    }

    @Test
    @Order(5)
    void testDeleteTeam() {
        Log.info("Testing Delete Team");
        given()
                .body(1).when().delete("/workstation/teams/1").then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    void testDeleteNonExistingTeam() {
        Log.info("Testing Delete Non Existing Team");
        given()
                .body(1).when().delete("/workstation/teams/1").then().statusCode(404);
    }
}
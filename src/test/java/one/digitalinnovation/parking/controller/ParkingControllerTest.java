package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
        RestAssured.authentication = RestAssured.preemptive().basic("admin", "Admin@@123456");
    }

    @Test
    void whenfindAllCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(200);
    }

    @Test
    void checkIfLoginIsOk() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

}
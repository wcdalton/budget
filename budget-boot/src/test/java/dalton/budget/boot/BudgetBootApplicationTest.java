package dalton.budget.boot;

import com.jayway.restassured.http.ContentType;
import dalton.budget.model.entity.Budget;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BudgetBootApplicationTest {

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {

    }

    @Test
    public void budgetShouldBeCreated() {
        given()
                .body(Budget.builder()
                        .year(2020)
                        .month(11)
                        .amount(5500d)
                        .build())
                        .contentType(ContentType.JSON)
                .when().post("http://localhost:" + port + "/budget")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void budgetShouldReturnData() {
        when().get("http://localhost:" + port + "/budget")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}

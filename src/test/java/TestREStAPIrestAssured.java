import com.jayway.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestREStAPIrestAssured {

    @Test
    @Order(1)
        public void getCheckEmpty() {
        when().
                get("http://localhost:3000/posts/3").
                then().
                statusCode(404).
                assertThat().body("{}", Matchers.nullValue());
    }

    @Test
    @Order(2)
    public void PostCreateNew() {
        given().
                contentType("application/json").body("{\n" +
                "      \"id\": 3,\n" +
                "      \"title\": \"New Element here...\",\n" +
                "      \"author\": \"Tester 404\"\n" +
                "    }\n").
                when().
                post("http://localhost:3000/posts").
                then().
                statusCode(201);
        when().
                get("http://localhost:3000/posts/3").
                then().
                statusCode(200).
                assertThat().body("author", equalTo("Tester 404"));
        Response response = get("http://localhost:3000/posts/3");
    }

    @Test
    @Order(3)
       public void getCheckNew() {
        when().
                get("http://localhost:3000/posts/3").
        then().
                statusCode(200).
                assertThat().body("author", equalTo("Tester 404"), "id", equalTo(3));
        Response response = get("http://localhost:3000/posts/3");
    }

    @Test
    @Order(4)
    public void putChangeNew() {
        given().
                contentType("application/json").body("{\n" +
                "      \"id\": 3,\n" +
                "      \"title\": \"THIS ITEM HAS BEEN CHANGED BY THE PUT METHOD...\",\n" +
                "      \"author\": \"Project Manager\"\n" +
                "    }\n").
                when().
                put("http://localhost:3000/posts/3").
                then().
                statusCode(200);
        when().
                get("http://localhost:3000/posts/3").
                then().
                statusCode(200).
                assertThat().body("title", equalTo("THIS ITEM HAS BEEN CHANGED BY THE PUT METHOD..."))
                .body("author", equalTo("Project Manager"));
        Response response = get("http://localhost:3000/posts/3");
    }

    @Test
    @Order(5)
    public void getCheckChanges() {
        when().
                get("http://localhost:3000/posts/3").
                then().
                statusCode(200).
                assertThat().body("author", equalTo("Project Manager"), "id", equalTo(3), "title", equalTo("THIS ITEM HAS BEEN CHANGED BY THE PUT METHOD..."));
        Response response = get("http://localhost:3000/posts/3");
    }

    @Test
    @Order(6)
    public void deleteNEW() {
        when().
                delete("http://localhost:3000/posts/3").
                then().
                statusCode(200).
                assertThat().body("{}", Matchers.nullValue());
    }

    @Test
    @Order(7)
    public void getCheckEmptyAfterDelete() {
        when().
                get("http://localhost:3000/posts/3").
                then().
                statusCode(404).
                assertThat().body("{}", Matchers.nullValue());
    }

}

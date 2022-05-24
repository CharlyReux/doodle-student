package fr.istic.tlc;

import java.util.List;
import fr.istic.tlc.dao.commentRepository;
import fr.istic.tlc.domain.comment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class commentTest {

    @Test
    public void testHelloEndpoint() {
        given()
        .when().get("/api/comment/hello")
        .then()
            .statusCode(200)
            .body(is("hello comment!")); 
    }

    @Test
    public void testGetAllcomments(){

    }

    @Test
    public void testCreateComment(){

    }

    @Test
    public void testgetcommentByID(){

    }

    @Test
    public void getEntityManager(){

    }

    @Test
    public void deletecommentById(){

    }
    @Test
    public void getAllCommentsFromPoll(){

    }
    @Test
    public void deleteAllCommentsFromPoll(){

    }

}
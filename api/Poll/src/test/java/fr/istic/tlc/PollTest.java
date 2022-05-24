package fr.istic.tlc;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PollTest {

    @Test
    public void testgetCommentTest() {
        given()
          .when().get("/api/poll/hellocomment")
          .then()
             .statusCode(200)
             .body(is("hello world!")); 
    }
    
    @Test
    public void getAllcomments() {

    }

    @Test
    public void createComment() {

    }
    
    @Test
    public void createComment4Poll() {

    }

    @Test
    public void getAllCommentsFromPoll() {

    }

    @Test
    public void getAllPolls() {

    }

    @Test
    public void createPoll() {

    }

    @Test
    public void getPollBySlug() {

    }

    @Test
    public void getPollByASlug() {

    }

    @Test
    public void updatePoll() {

    }

    @Test
    public void updatePollById() {

    }

    @Test
    public void deletePoll() {

    }

    @Test
    public void closePoll() {

    }

    @Test
    public void addChoiceUser() {

    }

    @Test
    public void retrievePadURL() {

    }

    @Test
    public void initPad() {

    }

    @Test
    public void getPadId() {

    }
}
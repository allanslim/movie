package com.disney.movie.api;

import com.disney.movie.api.model.Movie;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.jayway.restassured.filter.log.RequestLoggingFilter;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieEndpointTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = serverPort;
        RestAssured.replaceFiltersWith(ResponseLoggingFilter.responseLogger(), new RequestLoggingFilter());
    }

    @After
    public void tearDown() throws Exception {
        RestAssured.replaceFiltersWith(new ArrayList<>());
    }

    @Test
    public void shouldCreateMovie() {
        String movieTitle = "The Return of the Jedi";
        Movie movie = new Movie(movieTitle);
        movie.umid = "bdeef";
        movie.runtime = "2 hours";
        movie.releaseYear = "1979";
        movie.rating = "PG13";

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .content(movie)
                .post("/movies")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo(movieTitle))
                .body("id", notNullValue());

    }

    @Test
    public void shouldGetMovies() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/movies")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].title", equalTo("Snow White"));
    }

    @Test
    public void shouldGetMovie() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/movies/abc456")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo("Cinderella"));
    }
}

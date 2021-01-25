package GitHubAPI.tests;

import GitHubAPI.pojo.GitHub;
import GitHubAPI.utils.ConfigurationReader;
import GitHubAPI.utils.GitHubUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GitHub_Repo_CRUD {


    private static Map<String, Object> payloadMap;
    private static String repoName;
    private static String username;
    private static String token;

    @BeforeAll
    public static void setup(){
        baseURI = "https://api.github.com";
        payloadMap = GitHubUtil.getRandomGitHubRepo(); // calling random generated payload from utility class
        username = ConfigurationReader.getProperty("username"); // calling github username from properties file
        token = ConfigurationReader.getProperty("token"); // calling personal access token with particular scope from properties file
    }

    @AfterAll
    public static void tearDown(){
        reset(); // resetting baseURI
    }

    @Test @Order(1)
    public void createRepository(){
        // creating new repository using method chaining

        repoName = given()  //assigning extracted repository name to String
                           .accept("application/vnd.github.v3+json") //according to the API DOC we expect response in json
                           .header("Authorization", "Bearer " + token) //passing personal access token
                           .body(payloadMap) // passing generated payload
                           .log().all()
                  .when()
                           .post("user/repos")

                  .then()
                           .log().all()
                           .assertThat()
                           .statusCode(201) //expecting response code 201 according to documentation
                           .contentType(ContentType.JSON)
                           .body("name", equalTo(payloadMap.get("name"))) // validating some values of json with values of map
                           .body("description", is(payloadMap.get("description")))
                           .body("owner.login", equalTo(username))
                           .body("private", is(false))
                           .extract().jsonPath().getString("name"); // extracting the name of created repository

    }

    @Test @Order(2)
    public void getRepository(){
        // getting created repository
                  given()
                            .log().all()
                            .header("Authorization", "Bearer " + token)
                            .accept(ContentType.JSON)
                            .pathParam("repoName", repoName) // according to the doc we have to pass path parameters
                            .pathParam("username", username)
                 .when()
                           .get("repos/{username}/{repoName}")// passing repository name and username of github
                 .then()
                          .log().all()
                          .assertThat()
                           .statusCode(200)
                          .contentType(ContentType.JSON)
                          .body("name", equalTo(payloadMap.get("name")))
                          .body("description", is(payloadMap.get("description")))
                          .body("owner.login", equalTo(username))
                          .body("private", is(false));
    }

    @Test @Order(3)
    public void updateRepository(){
        // I want to have different payload so we can update
        //serialization process - java object -> json

        GitHub payload = new GitHub("UpdatedRepo", "UpdatedDescription", "https://github.com");

                 given()
                          .log().all()
                          .header("Authorization", "Bearer " + token)
                          .pathParam("repoName", repoName)
                          .pathParam("username", username)
                          .body(payload) // updated payload
                .when()
                          .patch("repos/{username}/{repoName}")
                .then()
                         .assertThat()
                         .statusCode(200)
                         .body("name", is("UpdatedRepo"));

           // in order to make sure the update actually happened
          given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .pathParam("repoName", "UpdatedRepo") // according to the doc we have to pass path parameters
                .pathParam("username", username)
          .when()
                .get("repos/{username}/{repoName}")// passing repository name and username of github
          .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("UpdatedRepo"));

    }


    @Test @Order(4)
    public void deleteRepository(){

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("repoName", "UpdatedRepo")
                .pathParam("username", username)
        .when()
                .delete("repos/{username}/{repoName}")
        .then()
                .statusCode(204);

        // in order to make sure the delete actually happened
        // i want to make another get request using repoName expect 404
        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("repoName", "UpdatedRepo")
                .pathParam("username", username)
        .when()
                .get("repos/{username}/{repoName}")
        .then()
                .assertThat()
                .statusCode(404);


    }

}

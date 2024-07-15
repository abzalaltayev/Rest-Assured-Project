Rest-Assured Project

Overview
This project demonstrates the use of Rest-Assured, a powerful Java library for testing RESTful web services. It provides a framework for writing and running automated tests to validate the functionality, reliability, and performance of API endpoints.

Features
Comprehensive API testing using Rest-Assured
Includes examples of GET, POST, PUT, DELETE requests
Validation of response status codes, headers, and payloads
Use of JSONPath for parsing JSON responses
Integration with TestNG for test execution and reporting

Prerequisites
Java Development Kit (JDK) 8 or higher
Maven for dependency management and build automation
An IDE such as IntelliJ IDEA or Eclipse for running and editing the project

Setup
Clone the repository:


git clone https://github.com/abzalaltayev/Rest-Assured-Project.git
cd Rest-Assured-Project

Install dependencies:

mvn clean install
Running the Tests
You can run the tests using Maven or directly from your IDE.

Using Maven

mvn test
Using IDE
Open the project in your IDE.
Navigate to the test class you want to run.
Right-click and select 'Run' or use the corresponding shortcut.
Project Structure
src/main/java: Contains the main application code (if any).
src/test/java: Contains the test classes and test suites.
pom.xml: Maven configuration file with dependencies.

Example Test
Here is an example of a simple GET request test:

java

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExampleTest {

    @Test
    public void testGetEndpoint() {
        RestAssured.baseURI = "https://api.example.com";

        given().
        when().
            get("/endpoint").
        then().
            assertThat().
            statusCode(200).
            body("key", equalTo("value"));
    }
}
Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For any questions or issues, please contact Abzal Altayev.


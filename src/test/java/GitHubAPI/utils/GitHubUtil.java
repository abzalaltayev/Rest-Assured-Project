package GitHubAPI.utils;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class GitHubUtil {

    public static Map<String, Object> getRandomGitHubRepo(){

        Faker faker = new Faker();

        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("name", faker.name().firstName());
        payloadMap.put("description", faker.company().industry());
        payloadMap.put("homepage", "https://github.com");
        payloadMap.put("private", false);

        return payloadMap;

    }

    public static void main(String[] args) {
        System.out.println(getRandomGitHubRepo());
    }



}

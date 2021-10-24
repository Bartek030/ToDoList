package pl_bartlomiej_swies;

import java.util.Optional;

class ServiceTest {

    static final String FALLBACK_NAME = "world";

    String prepareGreeting(String name) {
        return "Hello " + Optional.ofNullable(name).orElse(FALLBACK_NAME) + "!";
    }
}
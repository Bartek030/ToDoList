package pl_bartlomiej_swies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTestTest {
    private ServiceTest SUT = new ServiceTest();

    @Test
    public void test_prepareGreeting_null_returnGreetingsWithFallback() {
        // given + when
        var result = SUT.prepareGreeting(null);

        // then
        assertEquals("Hello " + ServiceTest.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnGreetingWithName() {
        // given
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name);

        // then
        assertEquals("Hello " + name + "!", result);
    }
}
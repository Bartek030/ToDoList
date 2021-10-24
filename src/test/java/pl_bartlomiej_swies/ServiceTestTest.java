package pl_bartlomiej_swies;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTestTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingsWithFallback() {
        // given
        var mockRepository = alwaysReturningLangRepository();
        var SUT = new ServiceTest(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(WELCOME + " " + ServiceTest.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        // given
        var mockRepository = alwaysReturningLangRepository();
        var SUT = new ServiceTest(mockRepository);
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name, "-1");

        // then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new ServiceTest(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + ServiceTest.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new ServiceTest(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + ServiceTest.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        // given
        var mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.empty();
            }
        };
        var SUT = new ServiceTest(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(ServiceTest.FALLBACK_LANG.getWelcomeMsg() + " " + ServiceTest.FALLBACK_NAME + "!", result);
    }

    private LangRepository alwaysReturningLangRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                if(id.equals(ServiceTest.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }
}
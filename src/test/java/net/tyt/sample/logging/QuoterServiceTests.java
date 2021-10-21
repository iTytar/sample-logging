package net.tyt.sample.logging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
class QuoterServiceTests {

    @Autowired
    private QuoterService service;

    @Autowired
    private QuoterProperties properties;

    private String quoteId;

    private String savedUri;

    @BeforeEach
    public void beforeEach() {
        quoteId = "1";
        savedUri = properties.getUri();
    }

    @AfterEach
    public void afterEach() {
        properties.setUri(savedUri);
    }

    /*=== Version #0 ===*/
    @Test
    void testGetQuota0() {
        String result = service.getQuote0(quoteId);
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota0Strange() {
        String result = service.getQuote0("abcd" + quoteId + "ZXC");
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota0WrongId() {
        quoteId = "wrongId";
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote0(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    @Test
    void testGetQuota0WrongURI() {
        properties.setUri("https://quoters.apps.pcfone.io.BAD/api");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote0(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    /*=== Version #1 ===*/
    @Test
    void testGetQuota1() {
        String result = service.getQuote1(quoteId);
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota1Strange() {
        String result = service.getQuote1("abcd" + quoteId + "ZXC");
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota1WrongId() {
        quoteId = "wrongId";
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote1(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    @Test
    void testGetQuota1WrongURI() {
        properties.setUri("https://quoters.apps.pcfone.io.BAD/api");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote1(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    /*=== Version #2 ===*/
    @Test
    void testGetQuota2() {
        String result = service.getQuote2(quoteId);
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota2Strange() {
        String result = service.getQuote2("abcd" + quoteId + "ZXC");
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota2WrongId() {
        quoteId = "wrongId";
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote2(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    @Test
    void testGetQuota2WrongURI() {
        properties.setUri("https://quoters.apps.pcfone.io.BAD/api");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote2(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    /*=== Version #3 ===*/
    @Test
    void testGetQuota3() {
        String result = service.getQuote3(quoteId);
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota3Strange() {
        String result = service.getQuote3("abcd" + quoteId + "ZXC");
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota3WrongId() {
        quoteId = "wrongId";
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote3(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    @Test
    void testGetQuota3WrongURI() {
        properties.setUri("https://quoters.apps.pcfone.io.BAD/api");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote3(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    /*=== Version #4 ===*/
    @Test
    void testGetQuota4() {
        String result = service.getQuote4(quoteId);
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota4Strange() {
        String result = service.getQuote4("abcd" + quoteId + "ZXC");
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuota4WrongId() {
        quoteId = "wrongId";
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote4(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    @Test
    void testGetQuota4WrongURI() {
        properties.setUri("https://quoters.apps.pcfone.io.BAD/api");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.getQuote4(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

}

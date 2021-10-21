package net.tyt.sample.logging;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class AbstractQuoterServiceTests {

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

    @Test
    void testGetQuota() {
        String result = getQuoteFunction().apply(quoteId);
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuotaStrange() {
        String result = getQuoteFunction().apply("abcd" + quoteId + "ZXC");
        assertThat(result, containsString("Spring"));
    }

    @Test
    void testGetQuotaWrongId() {
        quoteId = "wrongId";
        Exception thrown = assertThrows(
                Exception.class,
                () -> getQuoteFunction().apply(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }

    @Test
    void testGetQuotaWrongURI() {
        properties.setUri("https://quoters.apps.pcfone.io.BAD/api");
        Exception thrown = assertThrows(
                Exception.class,
                () -> getQuoteFunction().apply(quoteId));
        assertThat(thrown.getMessage(), containsStringIgnoringCase("error"));
    }
    
    protected abstract Function<String,String> getQuoteFunction();
}

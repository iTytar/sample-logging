package net.tyt.sample.logging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
class QuoterServiceTests {
    @Autowired
    private QuoterService service;
    
    private final String quoteId = "1";
    
    @Test
    void testGetLanguage0() {
        String result = service.getQuote0(quoteId);
        assertThat(result,containsString("Spring"));
    }

    @Test
    void testGetLanguage1() {
        String result = service.getQuote1(quoteId);
        assertThat(result,containsString("Spring"));
    }

    @Test
    void testGetLanguage2() {
        String result = service.getQuote2(quoteId);
        assertThat(result,containsString("Spring"));
    }
    @Test
    void testGetLanguage3() {
        String result = service.getQuote3(quoteId);
        assertThat(result,containsString("Spring"));
    }

}

package net.tyt.sample.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GithubServiceTests {
    @Autowired
    private GithubService service;
    
    @Test
    void testGetLanguage() {
        String result = service.getLanguage();
        assertEquals("Java",result);
    }

}

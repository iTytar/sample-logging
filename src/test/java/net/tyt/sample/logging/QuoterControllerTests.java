package net.tyt.sample.logging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class QuoterControllerTests {

    @LocalServerPort
    private int port;

    private final String qouteId = "1";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetV0_Ok() throws Exception {
        assertThat(restTemplate.getForObject(getApiUri("v",qouteId), String.class),
                containsString("Spring"));
    }

    @Test
    public void testGetV0_Fail() throws Exception {
        assertThat(restTemplate.getForObject(getApiUri("v","wrongRepoId"), String.class),
                containsString("500"));
    }

    private String getApiUri(String version, String repoId) {
        return String.join("","http://localhost:",String.valueOf(port),"/",version,"/",repoId);
    }
}

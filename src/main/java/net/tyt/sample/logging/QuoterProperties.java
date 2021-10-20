package net.tyt.sample.logging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 69TytarIA
 */
@Configuration
@ConfigurationProperties(prefix = "quoter")
@Data
public class QuoterProperties {
    private String uri;
}

package net.tyt.sample.logging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Igor Tytar <tytar@mail.ru>
 */
@Configuration
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {
    private String name;
    private String version;
    private String timestamp;
    private String branch;
    private String commit;
}

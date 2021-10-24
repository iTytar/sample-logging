package net.tyt.sample.logging;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

/**
 * Print application information
 * You should define following properties in the 'application.properties' file:
 * 
 * application.name=@project.artifactId@
 * build.version=@project.version@
 * build.timestamp=@maven.build.timestamp@
 * branch.name=@scmBranch@
 * commit.hash=@buildNumber@
 * spring.main.banner-mode=off
 * 
 * And add following to the 'pom.xml' file:
 * 
 *  <project>
 *      ...
 *      <scm>
 *          <connection>scm:git:file:///${basedir}</connection>
 *      </scm>
 *      ...
 *      <build>
 *          <plugins>
 *              ...
 *              <plugin>
 *                  <groupId>org.codehaus.mojo</groupId>
 *                  <artifactId>buildnumber-maven-plugin</artifactId>
 *                  <version>1.4</version>
 *                  <executions>
 *                      <execution>
 *                          <phase>validate</phase>
 *                          <goals>
 *                              <goal>create</goal>
 *                          </goals>
 *                      </execution>
 *                  </executions>
 *                  <configuration>
 *                      <doCheck>false</doCheck>
 *                      <doUpdate>false</doUpdate>
 *                  </configuration>
 *              </plugin>
 *              ...
 *          </plugins>
 *      </build>
 *  </project>
 * 
 * @author Igor Tytar <tytar@mail.ru>
 */
@Configuration
@Slf4j
public class AppInfoConfiguration {
    private static final String ENV_KEYS_FILE_NAME = "env-keys.properties";

    private final LocalTime startTime;

    private final ApplicationProperties applicationProperties;
    
    private static final Properties envKeys = new Properties();

    static {
        try (InputStream is = Application.class.getClassLoader().getResourceAsStream(ENV_KEYS_FILE_NAME)) {
            envKeys.load(is);
        } catch (Exception ex) {
            log.warn("error loading properties '{}'", ENV_KEYS_FILE_NAME, ex);
        }
    }
    
    public AppInfoConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        startTime = LocalTime.now();
    }
            
    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup(ApplicationReadyEvent event) {
        log.info("Started application {} v.{}, build timestamp {}, branch {}, commit {}",
                applicationProperties.getName(),
                applicationProperties.getVersion(),
                applicationProperties.getTimestamp(),
                applicationProperties.getBranch(),
                applicationProperties.getCommit());
        final Environment env = event.getApplicationContext().getEnvironment();
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        sources.stream()
                .filter(EnumerablePropertySource.class::isInstance)
                .map(EnumerablePropertySource.class::cast)
                .forEach(this::printSource);
    }

    private void printSource(EnumerablePropertySource eps) {
        String list = Arrays.stream(eps.getPropertyNames())
                .filter(prop -> envKeys.isEmpty() || envKeys.containsKey(prop)) //reduce parameters number
                .sorted()
                .map(prop -> prop + " = " + eps.getProperty(prop)).collect(Collectors.joining("\n"));
        if (!list.isBlank()) {
            log.info("PropertySource '{}':\n{}", eps.getName(), list);
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void beforeExit(ContextClosedEvent event) {
        log.info("Stopped application {} v.{}, build timestamp {}, branch {}, commit {}",
                applicationProperties.getName(),
                applicationProperties.getVersion(),
                applicationProperties.getTimestamp(),
                applicationProperties.getBranch(),
                applicationProperties.getCommit());
        log.info("Work time is {}", Duration.between(startTime, LocalTime.now()));
    }
}

package net.tyt.sample.logging;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class Application {

    private LocalTime startTime;

    @Value("${application.name}")
    private String title;

    @Value("${build.version}")
    private String version;

    @Value("${build.timestamp}")
    private String timestamp;

    @Value("${branch.name}")
    private String branch;

    @Value("${commit.hash}")
    private String commit;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup(ApplicationReadyEvent event) {
        startTime = LocalTime.now();

        log.info("Started application {} v.{}, build timestamp {}, branch {}, commit {}", title, version, timestamp, branch, commit);

        final Environment env = event.getApplicationContext().getEnvironment();
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        sources.stream()
                .filter(EnumerablePropertySource.class::isInstance)
                .map(EnumerablePropertySource.class::cast)
                .forEach(this::printSource);
    }

    private void printSource(EnumerablePropertySource eps) {
        log.info("PropertySource '{}':\n{}", eps.getName(),
                Arrays.stream(eps.getPropertyNames())
                        .map(prop -> prop + " = " + eps.getProperty(prop)).collect(Collectors.joining("\n"))
        );
    }

    @EventListener(ContextClosedEvent.class)
    public void beforeExit(ContextClosedEvent event) {
        log.info("Stopped application {} v.{}, build timestamp {}, branch {}, commit {}", title, version, timestamp, branch, commit);
        log.info("Work time is {}", Duration.between(startTime, LocalTime.now()));
    }

}

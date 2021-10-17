package net.tyt.sample.logging;

import java.util.Arrays;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup(ApplicationReadyEvent event) {
        final Environment env = event.getApplicationContext().getEnvironment();
        log.info("=== Application environment name ===");
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        sources.stream()
                .map(ps -> ps.getName())
                .forEach(log::info);
        log.info("=== Application environment values ===");
        StreamSupport.stream(sources.spliterator(), false)
                .filter(EnumerablePropertySource.class::isInstance)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                //                .filter(prop -> !(prop.contains("password")))
                .forEachOrdered(prop -> log.info("{} = {}", prop, env.getProperty(prop)));
    }
    
}

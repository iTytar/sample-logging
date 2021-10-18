package net.tyt.sample.logging;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Igor Tytar <tytar@mail.ru>
 */
@Service
@Slf4j
public class GithubService {
    private final RestTemplate restTemplate;
    private final String githubUri;
    
    public GithubService(RestTemplate restTemplate, @Value("${service.uri}") String githubUri) {
        this.restTemplate = restTemplate;
        this.githubUri = githubUri;
    }
    public String getLanguage() {
        log.info("try to get info from '{}'...",githubUri);
        GithubRepo repo = restTemplate.getForObject(githubUri, GithubRepo.class);
        log.info("info from '{}' is {}",githubUri,repo);
        return repo.getLanguage();
    }
}

package net.tyt.sample.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 69TytarIA
 */
@Controller
@Slf4j
public class QuoterController {

    private final QuoterService service;

    public QuoterController(QuoterService service) {
        this.service = service;
    }

    @GetMapping("v/{quoteId}")
    public @ResponseBody ResponseEntity<String> getQuote(@PathVariable String quoteId) {
        log.info("========= Version #0 =========");
        try {service.getQuote0(quoteId);} catch(Exception ex) {} finally {
            log.info("------------------------------");
        }
        log.info("========= Version #1 =========");
        try {service.getQuote1(quoteId);} catch(Exception ex) {} finally {
            log.info("------------------------------");
        }
        log.info("========= Version #2 =========");
        try {service.getQuote2(quoteId);} catch(Exception ex) {} finally {
            log.info("------------------------------");
        }
        log.info("========= Version #3 =========");
        try {
            return ResponseEntity.ok(buildMessage(quoteId,service.getQuote3(quoteId)));
        }  finally {
            log.info("------------------------------");
        }
    }
    
    @GetMapping("v0/{quoteId}")
    public @ResponseBody ResponseEntity<String> getQuote0(@PathVariable String quoteId) {
        return ResponseEntity.ok(buildMessage(quoteId,service.getQuote0(quoteId)));
    }
    @GetMapping("v1/{quoteId}")
    public @ResponseBody ResponseEntity<String> getLanguage1(@PathVariable String repo) {
        return ResponseEntity.ok(buildMessage(repo,service.getQuote1(repo)));
    }
    @GetMapping("v2/{quoteId}")
    public @ResponseBody ResponseEntity<String> getLanguage2(@PathVariable String repo) {
        return ResponseEntity.ok(buildMessage(repo,service.getQuote2(repo)));
    }
    @GetMapping("v3/{quoteId}")
    public @ResponseBody ResponseEntity<String> getLanguage3(@PathVariable String repo) {
        return ResponseEntity.ok(buildMessage(repo,service.getQuote3(repo)));
    }
    
    private static String buildMessage(String quoteId, String quote) {
        return String.join("","Quote #",quoteId," is ",quote);
    }
}

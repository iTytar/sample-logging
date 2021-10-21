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
    public @ResponseBody
    ResponseEntity<String> getQuote(@PathVariable String quoteId) {
        try {
            getQuote0(quoteId);
        } catch (Exception ex) {}
        try {
            getQuote1(quoteId);
        } catch (Exception ex) {}
        try {
            getQuote2(quoteId);
        } catch (Exception ex) {}
        try {
            getQuote3(quoteId);
        } catch (Exception ex) {}
        return getQuote4(quoteId);
    }

    @GetMapping("v0/{quoteId}")
    public @ResponseBody
    ResponseEntity<String> getQuote0(@PathVariable String quoteId) {
        log.info("========= Version #0 =========");
        try {
            return ResponseEntity.ok(buildMessage(quoteId, service.getQuote0(quoteId)));
        } finally {
            log.info("------------------------------");
        }
    }

    @GetMapping("v1/{quoteId}")
    public @ResponseBody
    ResponseEntity<String> getQuote1(@PathVariable String quoteId) {
        log.info("========= Version #1 =========");
        try {
            return ResponseEntity.ok(buildMessage(quoteId, service.getQuote1(quoteId)));
        } finally {
            log.info("------------------------------");
        }
    }

    @GetMapping("v2/{quoteId}")
    public @ResponseBody
    ResponseEntity<String> getQuote2(@PathVariable String quoteId) {
        log.info("========= Version #2 =========");
        try {
            return ResponseEntity.ok(buildMessage(quoteId, service.getQuote2(quoteId)));
        } finally {
            log.info("------------------------------");
        }
    }

    @GetMapping("v3/{quoteId}")
    public @ResponseBody
    ResponseEntity<String> getQuote3(@PathVariable String quoteId) {
        log.info("========= Version #3 =========");
        try {
            return ResponseEntity.ok(buildMessage(quoteId, service.getQuote3(quoteId)));
        } finally {
            log.info("------------------------------");
        }
    }

    @GetMapping("v4/{quoteId}")
    public @ResponseBody
    ResponseEntity<String> getQuote4(@PathVariable String quoteId) {
        log.info("========= Version #4 =========");
        try {
            return ResponseEntity.ok(buildMessage(quoteId, service.getQuote4(quoteId)));
        } finally {
            log.info("------------------------------");
        }
    }

    private static String buildMessage(String quoteId, String quote) {
        return String.join("", "Quote #", quoteId, " is ", quote);
    }
}

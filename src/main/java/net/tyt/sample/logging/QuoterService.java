package net.tyt.sample.logging;

import com.jcabi.aspects.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Igor Tytar <tytar@mail.ru>
 */
@Service
@Slf4j
public class QuoterService {

    private final QuoterProperties properties;
    private final RestTemplate restTemplate;

    public QuoterService(RestTemplate restTemplate, QuoterProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    /**
     * vesion #0 of getQuote method with no logging
     *
     * @param quoteId - quote identifier
     * @return quote
     */
    public String getQuote0(String quoteId) {
        return restTemplate.getForObject(getApiUri(quoteId), QuoteResponse.class).getValue().getQuote();
    }

    /**
     * vesion #1 of getQuote method with in code logging
     *
     * @param quoteId - quote identifier
     * @return quote
     */
    public String getQuote1(String quoteId) {
        log.info("get quote for '{}'...", quoteId);
        try {
            QuoteResponse response = restTemplate.getForObject(getApiUri(quoteId), QuoteResponse.class);
            String quote = response.getValue().getQuote();
            log.info("...get quote for '{}' return '{}'", quoteId, quote);
            return quote;
        } catch (Exception ex) {
            log.error("...get quote for '{}' throws '{}' with message '{}'", quoteId, ex.getClass().getName(), ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * version #2 of getQuote method with aspect logging
     *
     * @param quoteId - quote identifier
     * @return quote
     */
    @Loggable(value = Loggable.INFO, prepend = true)
    public String getQuote2(String quoteId) {
        return restTemplate.getForObject(getApiUri(quoteId), QuoteResponse.class).getValue().getQuote();
    }

    /**
     * version #3 of getQuote method with aspect deep logging
     *
     * @param quoteId - quote identifier
     * @return quote
     */
    @Loggable(value = Loggable.INFO, prepend = true)
    public String getQuote3(String quoteId) {
        return getQuoteResponse3(getApiUri(quoteId)).getValue().getQuote();
    }

    /**
     * wrap RestTempate call
     *
     * @param uri Quoter REST API URI
     * @return QuoteResponse bean
     */
    @Loggable(value = Loggable.INFO, prepend = true)
    private QuoteResponse getQuoteResponse3(String uri) {
        return restTemplate.getForObject(uri, QuoteResponse.class);
    }

    /**
     * vesion #4 of getQuote method with in code logging
     *
     * @param quoteId - quote identifier
     * @return quote
     */
    public String getQuote4(String quoteId) {
        log.info("get quote for '{}'...", quoteId);
        try {
            QuoteResponse response = getQuoteResponse4(getApiUri(quoteId));
            String quote = response.getValue().getQuote();
            log.info("...get quote for '{}' return '{}'", quoteId, quote);
            return quote;
        } catch (Exception ex) {
            log.error("...get quote for '{}' throws '{}' with message '{}'", quoteId, ex.getClass().getName(), ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * wrap RestTempate call
     *
     * @param uri Quoter REST API URI
     * @return QuoteResponse bean
     */
    private QuoteResponse getQuoteResponse4(String uri) {
        log.info("external service call '{}'...", uri);
        try {
            QuoteResponse response = restTemplate.getForObject(uri, QuoteResponse.class);
            log.info("...external service call '{}' return '{}'", uri, response);
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * build REST API URI for Quoter service interaction
     *
     * @param quoteId quote identifier
     * @return complete REST API URI
     */
    @Loggable(value = Loggable.DEBUG, prepend = true)
    private String getApiUri(String quoteId) {
        return String.join("/", properties.getUri(), quoteId.replaceAll("[a-zA-Z]", ""));
    }
}

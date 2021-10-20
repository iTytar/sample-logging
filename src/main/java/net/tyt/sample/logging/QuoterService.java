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
        log.info("getQuote1({})...", quoteId);
        try {
            QuoteResponse response = restTemplate.getForObject(getApiUri(quoteId), QuoteResponse.class);
            log.info("...getQuote1({}) = {}", quoteId, response);
            return response.getValue().getQuote();
        } catch (Exception ex) {
            log.error("error getting quote for '{}'", quoteId, ex);
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
        return getQuoteResponse(getApiUri(quoteId)).getValue().getQuote();
    }

    /**
     * wrap RestTempate call
     *
     * @param uri Quoter REST API URI
     * @return QuoteResponse bean
     */
    @Loggable(value = Loggable.INFO, prepend = true)
    private QuoteResponse getQuoteResponse(String uri) {
        return restTemplate.getForObject(uri, QuoteResponse.class);
    }

    /**
     * build REST API URI for Quoter service interaction
     *
     * @param quoteId quote identifier
     * @return complete REST API URI
     */
    @Loggable(value = Loggable.DEBUG, prepend = true)
    private String getApiUri(String quoteId) {
        return String.join("/",properties.getUri(),quoteId);
    }
}

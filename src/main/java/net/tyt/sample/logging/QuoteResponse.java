package net.tyt.sample.logging;

import lombok.Data;

/**
 *
 * @author Igor Tytar <tytar@mail.ru>
 */
@Data
public class QuoteResponse {
    private String type;
    private QuoteValue value;
}

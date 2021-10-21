package net.tyt.sample.logging;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Quoter1ServiceTests extends AbstractQuoterServiceTests {

    @Autowired
    private QuoterService service;

    @Override
    protected Function<String, String> getQuoteFunction() {
        return service::getQuote1;
    }


}

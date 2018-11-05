package au.com.cypto;

import au.com.crypto.model.CurrencyDocument;
import au.com.crypto.model.Quotes;
import au.com.crypto.model.SearchResult;
import au.com.crypto.repository.CurrencyRepository;
import au.com.crypto.service.CurrencyService;
import au.com.crypto.service.PostProcessingCallback;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CurrencyServiceTest {

    @Resource
    CurrencyService currencyService;

    @Resource
    CurrencyRepository currencyRepository;

    @Resource
    PostProcessingCallback callback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSearchCriteria() {

        List<Quotes> quotes = new ArrayList<>();
        Quotes q1 = new Quotes();
        q1.setPrice(34.98);
        q1.setTime("0915");
        quotes.add(q1);

        Quotes q2 = new Quotes();
        q1.setPrice(36.13);
        q1.setTime("1045");
        quotes.add(q2);

        Quotes q3 = new Quotes();
        q1.setPrice(37.01);
        q1.setTime("1230");
        quotes.add(q3);

        Quotes q4 = new Quotes();
        q1.setPrice(35.98);
        q1.setTime("1400");
        quotes.add(q4);

        Quotes q5 = new Quotes();
        q1.setPrice(33.56);
        q1.setTime("1530");
        quotes.add(q5);

        CurrencyDocument doc1 = new CurrencyDocument();
        doc1.setCurrency("BTC");
        doc1.setDate("20180507");
        doc1.setQuotes(quotes);
        List<CurrencyDocument> documents = new ArrayList<>();
        documents.add(doc1);
        CurrencyDocument filter = new CurrencyDocument();
        filter.setCurrency("BTC");
        Example example = Example.of(filter, ExampleMatcher.matching());
        Mockito.when(currencyRepository.findAll(Mockito.eq(example))).thenReturn(documents);

        List<SearchResult> result = currencyService.searchByCriteria(filter);
        Assert.assertNotNull(result);

    }


}

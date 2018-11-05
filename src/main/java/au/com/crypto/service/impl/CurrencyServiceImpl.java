package au.com.crypto.service.impl;


import au.com.crypto.model.CurrencyDocument;
import au.com.crypto.model.SearchCriteria;
import au.com.crypto.model.SearchResult;
import au.com.crypto.repository.CurrencyRepository;
import au.com.crypto.service.CurrencyService;
import au.com.crypto.service.PostProcessingCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class CurrencyServiceImpl implements CurrencyService{
    private final Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    @Resource
    CurrencyRepository webscale;
    @Autowired
    PostProcessingCallback callback;

    @Override
    public List<SearchResult> findAll() {
        List<SearchResult> results= new ArrayList<>();
        logger.info("finding all documents in mongodb");
        List<CurrencyDocument> list = webscale.findAll();
        list.stream().forEach(d -> {
            SearchResult r = callback.apply(d);
            results.add(r);
        });
        return results;
    }


    @Override
    public List<SearchResult> searchByCriteria(CurrencyDocument filter) {
        List<SearchResult> results= new ArrayList<>();
        ExampleMatcher matcher = buildExampleMatcher();
        Example<CurrencyDocument> example = Example.of(filter, matcher);
        List<CurrencyDocument> list = webscale.findAll(example);
        list.stream().forEach(d -> {
            SearchResult r = callback.apply(d);
            results.add(r);
        });
        return results;
    }



    private ExampleMatcher buildExampleMatcher() {
        return ExampleMatcher
                    .matching()
                    .withMatcher(SearchCriteria.CURRENCY_NAME.toString(), ignoreCase())
                    .withMatcher(SearchCriteria.DATE.toString(), ignoreCase())
                    .withIgnoreNullValues()
                    .withIgnorePaths("quotes");
    }
}

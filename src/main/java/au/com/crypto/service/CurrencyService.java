package au.com.crypto.service;


import au.com.crypto.model.CurrencyDocument;
import au.com.crypto.model.SearchResult;

import java.util.List;

public interface CurrencyService{
    /**
     * finds all documents in mongodb currency collection and applies analytics to find best day trade
     * @return
     */
    List<SearchResult> findAll();


    /**
     * find by search criteria
     * @param filter
     * @return
     */
    List<SearchResult> searchByCriteria(CurrencyDocument filter);
}

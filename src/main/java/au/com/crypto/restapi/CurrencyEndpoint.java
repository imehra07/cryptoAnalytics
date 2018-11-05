package au.com.crypto.restapi;


import au.com.crypto.model.CurrencyDocument;
import au.com.crypto.model.SearchResult;
import au.com.crypto.service.CurrencyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyEndpoint {

    @Resource
    CurrencyService currencyService;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SearchResult> findAll()  {
       return currencyService.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public List<SearchResult> searchByCriteria(@RequestBody CurrencyDocument criteria)  {
        return currencyService.searchByCriteria(criteria);
    }

}

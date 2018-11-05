package au.com.crypto.service;


import au.com.crypto.model.CurrencyDocument;
import au.com.crypto.model.Quotes;
import au.com.crypto.model.SearchResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class PostProcessingCallback implements Function<CurrencyDocument, SearchResult> {

    @Override
    public SearchResult apply(CurrencyDocument o) {
        SearchResult finalResult = new SearchResult(o.getDate(), o.getCurrency());
        if (o.getQuotes() == null || o.getQuotes().isEmpty()) {
            return finalResult;
        }

        int size = o.getQuotes().size();
        for (int i = 0; i < size; i++) {

            Quotes lowestPrice = new Quotes(o.getQuotes().get(i));
            lowestPrice.setType("Buy");
            List<Quotes> sublist = o.getQuotes().subList(i + 1, size);
            Quotes highestPrice = new Quotes(findHighestInSublist(lowestPrice, sublist));
            highestPrice.setType("Sell");
            double profit = calculateProfit(lowestPrice, highestPrice);
            List<Quotes> result = new ArrayList<>();
            result.add(lowestPrice);
            result.add(highestPrice);

            if (finalResult.getQuotes()== null || finalResult.getQuotes().isEmpty()) {
                finalResult.setQuotes(result);
                finalResult.setProfit(profit);
            } else if (finalResult.getProfit() < profit) {
                finalResult.setProfit(profit);
                finalResult.setQuotes(result);
            }
        }
        return finalResult;

    }

    private Quotes findHighestInSublist(Quotes q, List<Quotes> sublist) {
        return sublist.stream().reduce((quote1, quote2) -> quote1.getPrice() > quote2.getPrice() ? quote1 : quote2).orElse(q);
    }

    private double calculateProfit(Quotes minPriceTicker, Quotes maxPriceTicker) {
        double profit =maxPriceTicker.getPrice() - minPriceTicker.getPrice();
        return new BigDecimal(profit).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


}

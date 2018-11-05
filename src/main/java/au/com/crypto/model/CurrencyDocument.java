package au.com.crypto.model;


import org.jongo.marshall.jackson.oid.MongoId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "currency")
public class CurrencyDocument {
    @MongoId
    private String id;
    private String currency;
    private String date;
    private List<Quotes> quotes;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
       return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Quotes> getQuotes() {
        return quotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuotes(List<Quotes> quotes) {
        this.quotes = quotes;
    }
}

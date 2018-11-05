package au.com.crypto.model;


import java.util.List;

public class SearchResult {

    private String date;
    private String currency;
    private List<Quotes> quotes;
    //cant be null..use primitive type
    private double profit;

    public SearchResult() {

    }

    public SearchResult(String date, String currency) {
        this.date = date;
        this.currency = currency;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Quotes> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quotes> quotes) {
        this.quotes = quotes;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}

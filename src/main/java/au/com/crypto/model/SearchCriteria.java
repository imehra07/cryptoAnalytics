package au.com.crypto.model;


public enum SearchCriteria {
    CURRENCY_NAME("currency"),
    DATE("date"),
    QUOTES("quotes");

    private final String value;

    SearchCriteria(String value) {
        this.value = value;
    }
}

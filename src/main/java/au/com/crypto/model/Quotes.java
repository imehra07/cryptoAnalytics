package au.com.crypto.model;


import java.lang.reflect.Constructor;

public class Quotes{

    private String time;
    //cant be null..use primitive type
    private double price;
    private String type;

    public Quotes() {

    }
    public Quotes(Quotes quotes) {
        this.time = quotes.getTime();
        this.price = quotes.getPrice();
        this.type = quotes.getType();
    }

    public String getTime() {
       return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

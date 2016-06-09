package group.sisto.ambrose.price;

/**
 * Created by AMBROSE on 06-Mar-16.
 */
public class Items {

    private String name;
    private String price;
    private String date;

    public Items() {
        //empty constructor
    }

    public Items(String item_name, String item_price, String item_date) {
        this.name = item_name;
        this.price = item_price;
        this.date = item_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

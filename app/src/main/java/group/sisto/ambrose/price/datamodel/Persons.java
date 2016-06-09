package group.sisto.ambrose.price.datamodel;

/**
 * Created by AMBROSE on 29-Apr-16.
 */
public class Persons {

    private String username;
    private int userPhoneNumber;
    private int shopNumber;

    public int getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(int shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(int userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

}

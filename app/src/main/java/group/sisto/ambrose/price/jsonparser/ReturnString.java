package group.sisto.ambrose.price.jsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReturnString {
    public static String[] parseData(String s) {
        String[] items = null;
        try {
            JSONArray jsonArray = new JSONArray(s);
            items = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                items[i] = object.getString("itemName") + "\t\t" + object.getString("itemPrice") + "\n" + object.getString("date");
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

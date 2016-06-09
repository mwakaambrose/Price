package group.sisto.ambrose.price.jsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import group.sisto.ambrose.price.datamodel.Persons;

/**
 * Created by AMBROSE on 29-Apr-16.
 */
public class PersonJSONParser {
    Persons persons = new Persons();

    public String parseData(String s) {
        String res = "";
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                persons.setUsername(object.getString("username"));
                persons.setUserPhoneNumber(object.getInt("phone"));
                res = object.getString("username") + object.getInt("phone");
                return res;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
}

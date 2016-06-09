package group.sisto.ambrose.price.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import group.sisto.ambrose.price.Items;

/**
 * Created by AMBROSE on 14-Mar-16.
 */
public class JSONParser {

    public static List<Items> parseFeed(String feed) {
        List<Items> itemsList = new ArrayList<>();

//        try {
//            JSONArray jsonArray = new JSONArray(feed);
//            for (int i = 0; i < jsonArray.length(); i++){
//                JSONObject object = jsonArray.getJSONObject(i);
//
//                Items items = new Items();
//
//                items.setName(object.getString("id"));
//                items.setPrice(object.getString("title"));
//                items.setDate(object.getString("body"));
//
//                itemsList.add(items);
//
//                return itemsList;
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//            return null;
//        }

        try {
            JSONObject object = new JSONObject(feed);
            Items items = new Items();
            items.setName(object.getString("id"));
            items.setPrice(object.getString("title"));
            items.setDate(object.getString("body"));
            itemsList.add(items);
            return itemsList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

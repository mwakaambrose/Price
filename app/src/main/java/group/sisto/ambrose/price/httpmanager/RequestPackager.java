package group.sisto.ambrose.price.httpmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by AMBROSE on 23-Apr-16.
 */
public class RequestPackager {
    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

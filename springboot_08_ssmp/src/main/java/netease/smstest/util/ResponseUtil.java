package netease.smstest.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private static final HttpHeaders JSON_HEADERS = new HttpHeaders();

    static {
        JSON_HEADERS.add("Content-Type", "application/json; charset=utf-8");
    }

    public static ResponseEntity<JSONObject> genSuccessResponse(JSONObject result) {
        return new ResponseEntity<JSONObject>(result, JSON_HEADERS, HttpStatus.OK);
    }

    public static ResponseEntity<JSONObject> genSuccessResponse(JSONObject result, boolean ignoreResult) {
        if (!ignoreResult) {
//            WebLoggingBean.put("res", result);
        }
        return new ResponseEntity<>(result, JSON_HEADERS, HttpStatus.OK);
    }
}

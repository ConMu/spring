package netease.rcstest;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netease.rcstest.ao.CspRespBody;
import netease.rcstest.sdkparams.RcsAccessTokenUnicom5GVO;
import netease.rcstest.sdkparams.RespBodyRcsAccessToken;
import netease.rcstest.utils.HttpsUtils;
import netease.rcstest.utils.OkhttpKit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 测试发送HTTP请求给电信运营商
 *
 * @author mucongcong
 * @date 2022/06/30 09:41
 * @since
 **/
public class okhhtpTest {

//    static String url = "https://maap101.sc.rcs.189.cn/message/bot/v1/sip%3A10659930780054001%40botplatform.rcs.vnet.cn/accessToken";
    static String url = "http://localhost:81/demo";

    public static RespBodyRcsAccessToken requestAccessToken(String chatbotId, RcsAccessTokenUnicom5GVO unicom5GVO) {
        Map<String, String> headers = buildFundamentHeaders();
        CspRespBody respBody = OkhttpKit.postJson(url, JSON.toJSONString(unicom5GVO), headers);
        return convertCspRespBodyToRespRcsAccessToken(respBody);
    }

    private static RespBodyRcsAccessToken convertCspRespBodyToRespRcsAccessToken(CspRespBody respBody) {
        RespBodyRcsAccessToken respBodyRcsAccessToken = new RespBodyRcsAccessToken();
        respBodyRcsAccessToken.setHttpCode(respBody.getHttpCode());
        respBodyRcsAccessToken.setHttpMessage(respBody.getHttpMessage());
        JSONObject jsonObject = JSONObject.parseObject(respBody.getData());
        respBodyRcsAccessToken.setErrorCode(jsonObject.getInteger("errorCode"));
        respBodyRcsAccessToken.setErrorMessage(jsonObject.getString("errorMessage"));
        respBodyRcsAccessToken.setAccessToken(jsonObject.getString("accessToken"));
        respBodyRcsAccessToken.setUrl(jsonObject.getString("url"));
        respBodyRcsAccessToken.setExpires(jsonObject.getInteger("expires"));
        return respBodyRcsAccessToken;
    }

    private static Map<String, String> buildFundamentHeaders() {
        Map<String, String> headers = new HashMap<>(8);
        headers.put("content-type", "application/json");
        headers.put("host","maap101.sc.rcs.189.cn");
        //        headers.put("host","host");
        headers.put("accept","application/json");
        return headers;
    }

    public static void main(String[] args) {
        RcsAccessTokenUnicom5GVO rcsAccessTokenUnicom5GVO = new RcsAccessTokenUnicom5GVO();
        rcsAccessTokenUnicom5GVO.setAppId("dYON27Gr");
        rcsAccessTokenUnicom5GVO.setAppKey("217f15cfba7598743f7c2ef6ae4fd4c19a8ea6a2");
        RespBodyRcsAccessToken tocken = requestAccessToken(null, rcsAccessTokenUnicom5GVO);

    }
}

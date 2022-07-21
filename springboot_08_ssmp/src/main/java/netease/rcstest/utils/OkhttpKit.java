package netease.rcstest.utils;

import netease.rcstest.ao.CspRespBody;
import netease.rcstest.execption.CspHttpRequestException;
import netease.rcstest.execption.CspHttpRespCode;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2021/12/1 11:13
 */
public class OkhttpKit {
    //以KB为基础单位进行换算
    public static final long ONE_KILOBYTE = 1024;
    public static final long ONE_MEGABYTE = 1024 * ONE_KILOBYTE;
    public static final long ONE_GIGABYTE = 1024 * ONE_MEGABYTE;
    public static final long ONE_TERABYTE = 1024 * ONE_GIGABYTE;
    public static final String protocol_https = "https://";
    public static final String url_param_tag = "?";
    private static Logger logger = LoggerFactory.getLogger(OkhttpKit.class);
    private static HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();


    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .connectionPool(new ConnectionPool(32, 5, TimeUnit.MINUTES))
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            })
            .build();


    public static CspRespBody postJson(String url, String data, Map<String, String> headers) {
        return postJson(url, null, data, headers);
    }

//    public static CspRespBody postFile(String url, byte[] bytes, Map<String, String> headersMap, String filename) {
//        return postFile(url, null, bytes, headersMap, filename, null);
//    }

//    public static CspRespBody postFile(String url, byte[] bytes, Map<String, String> headersMap, String filename, FileContentType contentType) {
//        return postFile(url, null, bytes, headersMap, filename, contentType);
//    }

//    public static RcsMaterialRespBody getRequest(String url, Map<String, String> queryParas, Map<String, String> headersMap) {
//        Call call = null;
//        ResponseBody responseBody = null;
//        try {
//            String realUrl = buildUrlWithQueryString(url, queryParas);
//            Headers headers = GetHeaders(headersMap);
//            Request request = new Request.Builder().url(realUrl)
//                    .headers(headers)
//                    .build();
//
//
//            call = client.newCall(request);
//            Response execute = call.execute();
//            responseBody = execute.body();
//            RcsMaterialRespBody respBody = new RcsMaterialRespBody();
//            respBody.setHttpCode(execute.code());
//            respBody.setHttpMessage(execute.message());
//            if (logger.isDebugEnabled()) {
//                logger.debug("request :" + request.toString());
//                logger.debug("request header:" + request.headers().toString());
//                logger.debug(execute.headers().toString());
//            }
//            if (responseBody != null) {
////                respBody.setBytes(responseBody.bytes());
//                InputStream inputStream = responseBody.byteStream();
////                inputStream.reset();
//                respBody.setInputStream(inputStream);
//            }
//            return respBody;
//        } catch (Exception e) {
//            throw new CspHttpRequestException(e, CspHttpRespCode.REQUEST_WRONG, e.getMessage());
//        } finally {
////            if (call != null) {
////                call.cancel();
////            }
////            if (responseBody != null) {
////                responseBody.close();
////            }
//        }
//    }

//    public static CspRespBody postFile(String url, Map<String, String> queryParas, byte[] bytes, Map<String, String> headersMap, String filename, FileContentType contentType) {
//        Call call = null;
//        ResponseBody responseBody = null;
//        try {
//            String realUrl = buildUrlWithQueryString(url, queryParas);
//            Headers headers = GetHeaders(headersMap);
//            MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
//            RequestBody requestBody = RequestBody.create(contentType == null ? null : MediaType.parse(contentType.getCode()), bytes);
//            multiBuilder.setType(MultipartBody.FORM).addFormDataPart("file", filename + (contentType == null ? "" : contentType.getSuffix()), requestBody);
//            Request request = new Request.Builder().url(realUrl)
//                    .headers(headers)
//                    .post(multiBuilder.build())
//                    .build();
//
//
//            call = client.newCall(request);
//            Response execute = call.execute();
//            responseBody = execute.body();
//            CspRespBody respBody = new CspRespBody();
//            respBody.setHttpCode(execute.code());
//            respBody.setHttpMessage(execute.message());
//            if (responseBody != null) {
//                respBody.setData(responseBody.string());
//            }
//            if (logger.isDebugEnabled()) {
//
//                logger.debug("request :" + request.toString());
//                logger.debug("request header:" + request.headers().toString());
//                logger.debug("response:" + respBody.toString());
//            }
//            return respBody;
//        } catch (Exception e) {
//            throw new CspHttpRequestException(e, CspHttpRespCode.REQUEST_WRONG, e.getMessage());
//        } finally {
//            if (call != null) {
//                call.cancel();
//            }
//            if (responseBody != null) {
//                responseBody.close();
//            }
//        }
//    }
//
//    public static CspRespBody postFiles(String url, Map<String, String> headersMap, RcsChatbotMaterialUploadMobile5GVO mobile5GVO) {
//        Call call = null;
//        ResponseBody responseBody = null;
//        try {
//            MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
//            RequestBody tid = RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), mobile5GVO.getTid());
//            multiBuilder.setType(MultipartBody.FORM).addFormDataPart("tid", null, tid);
//            if (mobile5GVO.getThumbnail() != null) {
//                RequestBody thumbnail = RequestBody.create(MediaType.parse(mobile5GVO.getThumbnail().getFileContentType().getCode()), mobile5GVO.getThumbnail().getBytes());
//                multiBuilder.addFormDataPart("Thumbnail", "thumbnail" + mobile5GVO.getThumbnail().getFileName(), thumbnail);
//            }
//            RequestBody file = RequestBody.create(MediaType.parse(mobile5GVO.getFile().getFileContentType().getCode()), mobile5GVO.getFile().getBytes());
//            multiBuilder.addFormDataPart("File", mobile5GVO.getFile().getFileName(), file);
//
//            MultipartBody build = multiBuilder.build();
//            headersMap.put("Content-Length", String.valueOf(build.contentLength()));
//            Headers headers = GetHeaders(headersMap);
//
//
//            Request request = new Request.Builder().url(url)
//                    .headers(headers)
//                    .post(build)
//                    .build();
//
//
//            call = client.newCall(request);
//            Response execute = call.execute();
//            responseBody = execute.body();
//            CspRespBody respBody = new CspRespBody();
//            respBody.setHttpCode(execute.code());
//            respBody.setHttpMessage(execute.message());
//            if (responseBody != null) {
//                respBody.setData(responseBody.string());
//            }
//            if (logger.isDebugEnabled()) {
//
//                logger.debug("request header:" + request.headers().toString());
//                logger.debug("request :" + request.toString());
//                logger.debug("response:" + respBody.toString());
//            }
//            return respBody;
//        } catch (Exception e) {
//            throw new RcsHttpRequestException(e, RcsHttpRespCode.REQUEST_WRONG, e.getMessage());
//        } finally {
//            if (call != null) {
//                call.cancel();
//            }
//            if (responseBody != null) {
//                responseBody.close();
//            }
//        }
//    }


    public static CspRespBody postJson(String url, Map<String, String> queryParas, String data, Map<String, String> headersMap) {
        Call call = null;
        ResponseBody responseBody = null;
        try {
            String realUrl = buildUrlWithQueryString(url, queryParas);
            Headers headers = GetHeaders(headersMap);
            Request request = new Request.Builder().url(realUrl)
                    .headers(headers)
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), data))
                    .build();

            if (logger.isDebugEnabled()) {
                Buffer buffer = new Buffer();
                try {
                    logger.info("request :" + request.toString());
                    logger.debug("request header:" + request.headers().toString());
                    request.body().writeTo(buffer);
                    Charset charset = StandardCharsets.UTF_8;
                    String requestString = buffer.readString(charset);

                    logger.debug("body:" + requestString);
                } catch (IOException e) {
                    logger.error("print body info wrong:", e);
                }
            }
            call = client.newCall(request);
            Response execute = call.execute();
            responseBody = execute.body();
            CspRespBody respBody = new CspRespBody();
            respBody.setHttpCode(execute.code());
            respBody.setHttpMessage(execute.message());
            if (responseBody != null) {
                respBody.setData(responseBody.string());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("response:" + respBody.toString());
            }


            return respBody;
        } catch (Exception e) {
            throw new CspHttpRequestException(e, CspHttpRespCode.REQUEST_WRONG, e.getMessage());
        } finally {
            if (call != null) {
                call.cancel();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public static CspRespBody postForm(String url, Map<String, String> formBodyMap, Map<String, String> headersMap) throws IOException {
        return postForm(url, null, formBodyMap, headersMap);
    }

    public static CspRespBody postForm(String url, Map<String, String> queryParas, Map<String, String> formBodyMap, Map<String, String> headersMap) throws IOException {
        Call call = null;
        ResponseBody responseBody = null;
        try {
            String realUrl = buildUrlWithQueryString(url, queryParas);
            Headers headers = GetHeaders(headersMap);
            FormBody formBody = GetFormBody(formBodyMap);
            Request request = new Request.Builder().url(realUrl)
                    .headers(headers)
                    .post(formBody)
                    .build();
            call = client.newCall(request);
            Response execute = call.execute();
            responseBody = execute.body();
            CspRespBody respBody = new CspRespBody();
            respBody.setHttpCode(execute.code());
            respBody.setHttpMessage(execute.message());
            if (responseBody != null) {
                respBody.setData(responseBody.string());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("response:" + respBody.toString());
            }
            return respBody;
        } catch (IOException e) {
            logger.error("OkHttp Exception:", e);
            throw e;
        } finally {
            if (call != null) {
                call.cancel();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }


    private static FormBody GetFormBody(Map<String, String> formBodyMap) {
        FormBody.Builder builder = new FormBody.Builder();
        if (formBodyMap == null || formBodyMap.isEmpty()) {
            return builder.build();
        }
        for (Map.Entry<String, String> entry : formBodyMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.add(key, value);
        }
        return builder.build();
    }


    private static Headers GetHeaders(Map<String, String> headersParams) {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headersParams == null || headersParams.isEmpty()) {
            return headersBuilder.build();
        }
        for (Map.Entry<String, String> entry : headersParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            headersBuilder.add(key, value);
        }
        return headersBuilder.build();
    }


    /**
     * Build queryString of the url
     */
    private static String buildUrlWithQueryString(String url, Map<String, String> queryParas) {
        if (queryParas == null || queryParas.isEmpty()) {
            return url;
        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

        for (Map.Entry<String, String> entry : queryParas.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            urlBuilder.addQueryParameter(key, value);
        }

        return urlBuilder.build().toString();
    }


    public static CspRespBody deleteRequest(String url, Map<String, String> queryParas, Map<String, String> headersMap) {
        Call call = null;
        ResponseBody responseBody = null;
        try {
            String realUrl = buildUrlWithQueryString(url, queryParas);
            Headers headers = GetHeaders(headersMap);
            Request request = new Request.Builder().url(realUrl)
                    .headers(headers)
                    .delete()
                    .build();
            //用于打印http请求的信息
            if (logger.isDebugEnabled()) {
                Buffer buffer = new Buffer();
                try {
                    logger.debug("request :" + request.toString());
                    logger.debug("request header:" + request.headers().toString());
                    request.body().writeTo(buffer);
                    Charset charset = StandardCharsets.UTF_8;
                    String requestString = buffer.readString(charset);

                    logger.debug("body:" + requestString);
                } catch (IOException e) {
                    logger.error("print body info wrong:", e);
                }

            }


            call = client.newCall(request);
            Response execute = call.execute();
            responseBody = execute.body();
            CspRespBody respBody = new CspRespBody();
            respBody.setHttpCode(execute.code());
            respBody.setHttpMessage(execute.message());
            if (responseBody != null) {
                respBody.setData(responseBody.string());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("response:" + respBody.toString());
            }
            return respBody;
        } catch (Exception e) {
            throw new CspHttpRequestException(e, CspHttpRespCode.REQUEST_WRONG, e.getMessage());
        } finally {
            if (call != null) {
                call.cancel();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public static CspRespBody postXML(String url, Map<String, String> headersMap, String message) {
        Call call = null;
        ResponseBody responseBody = null;
        try {
            String realUrl = buildUrlWithQueryString(url, null);
            Headers headers = GetHeaders(headersMap);
            Request request = new Request.Builder().url(realUrl)
                    .headers(headers)
                    .post(RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), message))
                    .build();


            //用于打印http请求的信息
            if (logger.isDebugEnabled()) {
                Buffer buffer = new Buffer();
                try {
                    logger.debug("request :" + request.toString());
                    logger.debug("request header:" + request.headers().toString());
                    request.body().writeTo(buffer);
                    Charset charset = StandardCharsets.UTF_8;
                    String requestString = buffer.readString(charset);
                    logger.debug("body:" + requestString);
                } catch (IOException e) {
                    logger.error("print body info wrong:", e);
                }
            }

            call = client.newCall(request);
            Response execute = call.execute();
            responseBody = execute.body();
            CspRespBody respBody = new CspRespBody();
            respBody.setHttpCode(execute.code());
            respBody.setHttpMessage(execute.message());
            if (responseBody != null) {
                respBody.setData(responseBody.string());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("response:" + respBody.toString());
            }
            return respBody;
        } catch (Exception e) {
            throw new CspHttpRequestException(e, CspHttpRespCode.REQUEST_WRONG, e.getMessage());
        } finally {
            if (call != null) {
                call.cancel();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }
}

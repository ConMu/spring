package com.conmu.utils;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by yine on 2017/4/5.
 */
public class GsonUtils {

    private static final ThreadLocal<Gson> GSON = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    private static final JsonParser GSON_PARSER = new JsonParser();

    public static Gson getGson() {
        return GSON.get();
    }

    public static JsonParser getGsonParser() {
        return GSON_PARSER;
    }

    public static JsonObject parseJsonObject(String src) {
        if (StringUtils.isBlank(src)) {
            return new JsonObject();
        }

        return getGsonParser().parse(src).getAsJsonObject();
    }

    public static boolean isJsonObjectValueStringBlank(JsonObject jo, String key) {
        if (jo.has(key)) {
            String value = jo.get(key).getAsString();
            if (StringUtils.isNotBlank(value)) {
                return false;
            }
        }

        return true;
    }

    public static JsonObject copyJsonObject(JsonObject src) {
        JsonObject ret = new JsonObject();
        Set<Map.Entry<String, JsonElement>> entries = src.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            ret.add(entry.getKey(), entry.getValue());
        }
        return ret;
    }

    public static void copyJsonObject(JsonObject src, JsonObject ret) {
        Set<Map.Entry<String, JsonElement>> entries = src.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            ret.add(entry.getKey(), entry.getValue());
        }
    }

    public static void cutJsonObject(JsonObject src, JsonObject ret) {
        Set<Map.Entry<String, JsonElement>> entries = src.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            ret.remove(entry.getKey());
        }
    }

    public static JsonObject mergeJsonObject(JsonObject... jos) {
        JsonObject ret = new JsonObject();
        for (JsonObject jo : jos) {
            Set<Map.Entry<String, JsonElement>> entries = jo.entrySet();
            for (Map.Entry<String, JsonElement> entry : entries) {
                ret.add(entry.getKey(), entry.getValue());
            }
        }
        return ret;
    }

    public static <T> List<T> getList(JsonObject jo, String key, Class<T> clazz) {
        JsonElement je = jo.get(key);
        if (je != null && je.isJsonArray()) {
            JsonArray ja = je.getAsJsonArray();
            List<T> list = new ArrayList<>();
            Iterator<JsonElement> var6 = ja.iterator();

            while(var6.hasNext()) {
                JsonElement jsonElement = var6.next();
                list.add(fromJson(jsonElement, clazz));
            }

            return list;
        } else {
            return null;
        }
    }

    public static <T> T fromJson(JsonElement json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static String toJsonString(Object obj) {
        return obj == null ? "": getGson().toJson(obj);
    }

    public static String getString(JsonObject jo, String key) {
        return getString(jo, key, null);
    }
    public static String getString(JsonObject jo, String key, String dv) {
        try {
            return jo.has(key) && !jo.get(key).isJsonNull() ? jo.get(key).getAsString().trim() : dv;
        } catch (IllegalStateException var6) {
            try {
                JsonElement ele = jo.get(key);
                return ele != null ? ele.toString() : dv;
            } catch (Exception var5) {
                return dv;
            }
        }
    }
}

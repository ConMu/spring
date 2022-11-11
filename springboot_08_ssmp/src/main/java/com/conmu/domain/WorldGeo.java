package com.conmu.domain;

/**
 * @author mucongcong
 * @date 2022/11/01 20:26
 * @since
 **/
public class WorldGeo {
//    {
//        "code": 244,
//            "tw": "安哥拉",
//            "en": "Angola",
//            "locale": "AO",
//            "zh": "安哥拉",
//            "lat": -9.3005898,
//            "lng": 14.9134098,
//            "emoji": "🇦🇴"
//    }

    private int code;

    private String tw;

    private String en;

    private String locale;

    private String zh;

    private double lat;

    private double lng;

    private String emoji;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}

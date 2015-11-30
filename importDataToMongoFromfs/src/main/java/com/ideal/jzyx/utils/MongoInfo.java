package com.ideal.jzyx.utils;

/**
 * Created by Administrator on 2015/11/27.
 */
public class MongoInfo {
    private String city;
    private String tag;
    private String ads;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "MongoInfo{" +
                "city='" + city + '\'' +
                ", tag='" + tag + '\'' +
                ", ads='" + ads + '\'' +
                '}';
    }
}

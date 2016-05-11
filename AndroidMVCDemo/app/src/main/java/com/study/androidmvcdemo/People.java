package com.study.androidmvcdemo;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hsy on 2016/5/11.
 */
public class People {
    public String mName;
    public String mAddress;
    public String mMobile;
    public int mAge;

    public static ArrayList<People> parse(JSONObject jsonObject) {
        ArrayList<People> result = null;
        if (jsonObject != null) {
            // do parse the json
        }
        return result;
    }

    @Override
    public String toString() {
        return "People{" +
                "mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mMobile='" + mMobile + '\'' +
                ", mAge=" + mAge +
                '}';
    }
}

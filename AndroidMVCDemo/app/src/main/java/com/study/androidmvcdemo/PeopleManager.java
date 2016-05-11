package com.study.androidmvcdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by hsy on 2016/5/11.
 */
public class PeopleManager {
    public static final String ACTION_LOAD_START = "com.study.action.load.start";
    public static final String ACTION_LOAD_SUCCESS = "com.study.action.load.success";
    public static final String ACTION_LOAD_FAIL = "com.study.action.load.fail";

    private AsyncHttpClient mNetClient;
    private Context mContext;
    private ArrayList<People> mPeople;


    public PeopleManager(Context context) {
        mContext = context;
        mNetClient = new AsyncHttpClient();
    }

    public void startSync() {
        // do load people from net
        // when load success, parse the json and notify the result
        // when load fail, notify load fail
        mNetClient.get(
                "http://xxx",
                new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        notifyLoadStart();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        mPeople = People.parse(response);
                        if (mPeople == null || mPeople.size() == 0) {
                            notifyLoadFail();
                        } else {
                            notifyLoadSuccess();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        notifyLoadFail();
                    }
                });
    }

    public ArrayList<People> getPeople() {
        return mPeople;
    }

    private void notifyLoadStart() {
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(
                new Intent(ACTION_LOAD_START)
        );
    }

    private void notifyLoadSuccess() {
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(
                new Intent(ACTION_LOAD_SUCCESS)
        );
    }

    private void notifyLoadFail() {
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(
                new Intent(ACTION_LOAD_FAIL)
        );
    }
}

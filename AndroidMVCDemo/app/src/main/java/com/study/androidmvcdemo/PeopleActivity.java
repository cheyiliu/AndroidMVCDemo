package com.study.androidmvcdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity {
    private PeopleManager mPeopleManager = new PeopleManager(this);
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String acton = intent.getAction();
                if (acton.equals(PeopleManager.ACTION_LOAD_START)) {
                    showLoading();
                } else if (acton.equals(PeopleManager.ACTION_LOAD_SUCCESS)) {
                    showPeopleList();
                } else if (acton.equals(PeopleManager.ACTION_LOAD_FAIL)) {
                    showError();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerReceiver();
        mPeopleManager.startSync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PeopleManager.ACTION_LOAD_START);
        intentFilter.addAction(PeopleManager.ACTION_LOAD_SUCCESS);
        intentFilter.addAction(PeopleManager.ACTION_LOAD_FAIL);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }


    private void initView() {
        // do the view initialization
    }

    private void showLoading() {
        // show loading ui
    }

    private void showPeopleList() {
        ArrayList<People> peoples = mPeopleManager.getPeople();
        // show the peoples
    }

    private void showError() {
        // show error ui
    }
}

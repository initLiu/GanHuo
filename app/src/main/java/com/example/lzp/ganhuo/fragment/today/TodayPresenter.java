package com.example.lzp.ganhuo.fragment.today;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lzp.ganhuo.net.MyVolley;
import com.example.lzp.ganhuo.util.JsonParser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by SKJP on 2017/3/9.
 */

public class TodayPresenter implements TodayContract.Presenter {
    private static final String TODAY_URL = "http://gank.io/api/day/";
    private static final String REQUEST_TAG = "today";
    private TodayContract.View mTodayView;

    public TodayPresenter(TodayContract.View todayView) {
        mTodayView = todayView;
    }

    @Override
    public void requestTodayData(int year, int month, int day) {
        mTodayView.showLoadingIndicator(true);
        String url = TODAY_URL + year + "/" + month + "/" + day;

        MyVolley.request(url, REQUEST_TAG, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                TodayPresenter.this.processTodayData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    @Override
    public void cancleRequest() {
        MyVolley.cancleRequest(REQUEST_TAG);
    }

    private void processTodayData(String s) {
        mTodayView.showLoadingIndicator(false);
        Today today = JsonParser.parse2Today(s);
        mTodayView.showTodayData(today);
    }

    @Override
    public void start() {

    }
}
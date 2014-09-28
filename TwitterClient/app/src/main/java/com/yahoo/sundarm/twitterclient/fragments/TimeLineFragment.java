package com.yahoo.sundarm.twitterclient.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.sundarm.twitterclient.TwitterClientApp;
import com.yahoo.sundarm.twitterclient.TwitterRestClient;
import com.yahoo.sundarm.twitterclient.adapter.EndlessScrollListener;
import com.yahoo.sundarm.twitterclient.models.Tweet;

import org.json.JSONArray;

/**
 * Created by sundarm on 9/28/14.
 */
public class TimeLineFragment extends  TweetsListFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        populateTimeLine();
        lvTimeLine.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                populateTimeLine();
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });
        return view;
    }

    public void populateTimeLine()
    {
        TwitterRestClient client = TwitterClientApp.getRestClient();
        client.getTimeLine(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                tweetsList.addAll(Tweet.fromJSONArray(jsonArray));
                Log.d("debug", jsonArray.toString());
                Log.d("debug", "Number of tweets is ------ " + jsonArray.length());
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", "failed to retrieve tweets");
                Log.d("debug", throwable.toString());
            }
        });
    }

}

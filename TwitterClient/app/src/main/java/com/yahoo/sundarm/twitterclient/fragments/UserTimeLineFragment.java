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
import com.yahoo.sundarm.twitterclient.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sundarm on 9/28/14.
 */
public class UserTimeLineFragment extends TweetsListFragment {
    Long max_id = 1L;
    User user = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);


        TwitterClientApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
            public void onSuccess(JSONObject jsonObject) {
                user = User.fromJSON(jsonObject);

            }

        });
        populateMentionsTimeLine();
        lvTimeLine.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                populateMentionsTimeLine();
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });
        return view;
    }

    public void populateMentionsTimeLine() {
        TwitterRestClient client = TwitterClientApp.getRestClient();
        client.getUserTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                tweetsList.addAll(Tweet.fromJSONArray(jsonArray));
                if (tweetsList.size() > 0) {
                    max_id = tweetsList.get(0).getId();
                }
                Log.d("debug", jsonArray.toString());
                Log.d("debug", "Number of tweets is ------ " + jsonArray.length());
                Log.d("debug ", " The max is " + max_id.toString());
                adapter.notifyDataSetChanged();


            }


            public void handleFailureMessage(Throwable e, String responseBody) {
                Log.d("debug", responseBody);

            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", "failed to retrieve tweets");
                Log.d("debug", s);
            }

            @Override
            public void onFailure(Throwable throwable, JSONArray jsonArray) {
                super.onFailure(throwable, jsonArray);
                Log.d("debug", "failed to retrieve tweets");
                Log.d("debug", throwable.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d("debug", "failed to retrieve tweets");
                Log.d("debug", throwable.toString());
                super.onFailure(throwable);
            }
        }, max_id, "sundarm");
    }
}

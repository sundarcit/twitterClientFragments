package com.yahoo.sundarm.twitterclient.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.sundarm.twitterclient.TwitterClientApp;
import com.yahoo.sundarm.twitterclient.TwitterRestClient;
import com.yahoo.sundarm.twitterclient.adapter.EndlessScrollListener;
import com.yahoo.sundarm.twitterclient.models.Tweet;

import org.json.JSONArray;


public class MentionsTimeLineFragment extends TweetsListFragment {

    Long max_id = 1L;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

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

    public void populateMentionsTimeLine()
    {
        TwitterRestClient client = TwitterClientApp.getRestClient();
        client.getMentionsTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                tweetsList.addAll(Tweet.fromJSONArray(jsonArray));
                max_id = tweetsList.get(0).getId();
                Log.d("debug", jsonArray.toString());
                Log.d("debug", "Number of tweets is ------ " + jsonArray.length());
                Log.d ("debug ", " The max is " + max_id.toString());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", "failed to retrieve tweets");
                Log.d("debug", throwable.toString());
            }
        }, max_id);
    }


}

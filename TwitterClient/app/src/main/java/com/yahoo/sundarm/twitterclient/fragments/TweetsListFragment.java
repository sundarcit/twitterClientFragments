package com.yahoo.sundarm.twitterclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yahoo.sundarm.twitterclient.R;
import com.yahoo.sundarm.twitterclient.TwitterArrayAdapter;
import com.yahoo.sundarm.twitterclient.models.Tweet;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sundarm on 9/28/14.
 */
public class TweetsListFragment extends Fragment {

    public ArrayList<Tweet> tweetsList;
    public ArrayAdapter<Tweet> adapter ;
    public ListView lvTimeLine ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragments_tweets_list, container, false);
        lvTimeLine = (ListView) view.findViewById(R.id.lvTimeline);
        lvTimeLine.setAdapter(adapter);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweetsList = new ArrayList<Tweet>();
        adapter = new TwitterArrayAdapter(getActivity(), tweetsList );
    }

    public void addAll (ArrayList<Tweet> tweets){
        adapter.addAll(tweets);
    }
}

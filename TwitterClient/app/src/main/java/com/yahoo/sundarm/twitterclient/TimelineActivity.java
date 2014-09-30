package com.yahoo.sundarm.twitterclient;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yahoo.sundarm.twitterclient.Listener.FragmentTabListener;
import com.yahoo.sundarm.twitterclient.fragments.HomeTimeLineFragment;
import com.yahoo.sundarm.twitterclient.fragments.MentionsTimeLineFragment;
import com.yahoo.sundarm.twitterclient.models.Tweet;


public class TimelineActivity extends FragmentActivity {

    HomeTimeLineFragment timeLineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        setupTabs();
      //  HomeTimeLineFragment timeLineFragment1;
       // timeLineFragment1 = getFragmentManager().findFragmentById(R.id.flContainer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void composeTweet( MenuItem mi) {
        Toast.makeText(this, "Compose a new Tweet", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(TimelineActivity.this, ComposeActivity.class);
        startActivityForResult(intent, 200);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == 200) {
           Tweet tweet = (Tweet)data.getSerializableExtra("tweet");
            getSupportFragmentManager().executePendingTransactions();
            HomeTimeLineFragment fragment = (HomeTimeLineFragment) getSupportFragmentManager().findFragmentByTag("Home");
            fragment.addTweet(tweet);
//            tweetsList.add(0, tweet);
//            adapter.notifyDataSetChanged();

        }
    }


    private void setupTabs() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Home")
                .setIcon(R.drawable.ic_home)
                .setTag("HomeTimelineFragment")
                .setTabListener(
                        new FragmentTabListener<HomeTimeLineFragment>(R.id.flContainer, this, "Home",
                                HomeTimeLineFragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Mentions")
                .setIcon(R.drawable.ic_mention)
                .setTag("MentionsTimeLineFragment")
                .setTabListener(
                        new FragmentTabListener<MentionsTimeLineFragment>(R.id.flContainer, this, "Mentions",
                                MentionsTimeLineFragment.class));

        actionBar.addTab(tab2);
    }

    public void showProfile(MenuItem mi)
    {

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}


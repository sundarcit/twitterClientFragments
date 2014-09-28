package com.yahoo.sundarm.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yahoo.sundarm.twitterclient.fragments.TimeLineFragment;


public class TimelineActivity extends FragmentActivity {

    TimeLineFragment timeLineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        //TimeLineFragment timeLineFragment1 = getFragmentManager().findFragmentById(R.id.);
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
//           Tweet tweet = (Tweet)data.getSerializableExtra("tweet");
//            tweetsList.add(0, tweet);
//            adapter.notifyDataSetChanged();

        }
    }
}

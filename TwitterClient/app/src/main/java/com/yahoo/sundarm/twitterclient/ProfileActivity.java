package com.yahoo.sundarm.twitterclient;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yahoo.sundarm.twitterclient.models.Tweet;
import com.yahoo.sundarm.twitterclient.models.User;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class ProfileActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getMyInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
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


    public void getMyInfo()
    {

        TwitterClientApp.getRestClient().getMyInfo(new JsonHttpResponseHandler(){
        public void onSuccess(JSONObject jsonObject) {
           User user = User.fromJSON(jsonObject);
           getActionBar().setTitle(user.getScreenName());
            populateProfileView(user);
    }

            public void populateProfileView (User user)
            {
                ImageView iv = (ImageView)findViewById(R.id.ivProfileView);
                TextView userView = (TextView)findViewById(R.id.tvName);
                TextView tagView = (TextView)findViewById(R.id.tvTagLine);
                TextView following = (TextView)findViewById(R.id.tvFollowing);
                TextView follwers = (TextView)findViewById(R.id.tvFollowers);

                userView.setText(user.getScreenName());
                tagView.setText(user.getTagLine());
                following.setText(user.getFollowingCount().toString() + " followers ");
                follwers.setText(user.getFollowersCount().toString() + " following ");


                ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), iv);


            }
        @Override
        public void onFailure(Throwable throwable, String s) {
        Log.d("debug", "failed to retrieve tweets");
        Log.d("debug", throwable.toString());
    }
    });
    }
}

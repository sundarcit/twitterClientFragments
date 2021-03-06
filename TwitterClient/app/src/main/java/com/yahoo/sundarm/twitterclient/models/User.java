package com.yahoo.sundarm.twitterclient.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sundarm on 9/20/14.
 */
public class User implements Serializable{
    String name;
    Long id;

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public Long getId() {
        return id;
    }

    String screenName;
    String profileImageUrl;

    String tagLine;

    public Integer getFollowersCount() {
        return followersCount;
    }

    public String getTagLine() {
        return tagLine;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    Integer followersCount;
    Integer followingCount;

    public static User fromJSON(JSONObject jsonObject)
    {
        User user = new User();
        try {
            user.id =  jsonObject.getLong("id");
            user.name =  jsonObject.getString("name");
            user.screenName = "@" + jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.followersCount = jsonObject.getInt("followers_count");
            user.tagLine = jsonObject.getString("description");
            user.followingCount = jsonObject.getInt("friends_count");


        }
        catch (JSONException e)
        {
            Log.e("error", e.getMessage());
        }
        return  user;

    }

}

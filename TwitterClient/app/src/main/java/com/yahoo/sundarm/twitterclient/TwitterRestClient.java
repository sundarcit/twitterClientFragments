package com.yahoo.sundarm.twitterclient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yahoo.sundarm.twitterclient.models.Tweet;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterRestClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "uq3hnWZLd5nAIBkCIxahqgYTp";       // Change this
	public static final String REST_CONSUMER_SECRET = "cOldDAs1SbFg1qvVydrmJgbFDfRQ8NnecyQEIT12oa4au8gzKD"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cprest"; // Change this (here and in manifest)

	public TwitterRestClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}


    public void getTimeLine(AsyncHttpResponseHandler handler, Long max_id)
    {

        String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("since_id", "10");
        params.put("count", "5");
        if (max_id != 1L) {
            params.put("max_id", max_id.toString());
        }

        client.get(apiUrl, params, handler);

    }


    public void getMentionsTimeLine(AsyncHttpResponseHandler handler, Long max_id)
    {

        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        RequestParams params = new RequestParams();

        params.put("since_id", max_id.toString());
        params.put("count", "5");
        client.get(apiUrl, params, handler);

    }


    public void postTweet(AsyncHttpResponseHandler handler, String tweetText)
    {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", tweetText);
        client.post(apiUrl, params, handler);

    }


    public void getMyInfo(AsyncHttpResponseHandler handler){
        String apiUrl = getApiUrl("account/verify_credentials.json");
        client.get(apiUrl, null, handler);
    }

    public void getUserTimeLine(AsyncHttpResponseHandler handler, Long max_id, String userId) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = new RequestParams();
        params.put("since_id", "10");
        params.put("count", "5");
        params.put("screen_name", userId);
        if (max_id != 1L) {
//            params.put("max_id", max_id.toString());
            params.put("since_id", max_id.toString());
        }
        client.get(apiUrl, params, handler);

    }
}
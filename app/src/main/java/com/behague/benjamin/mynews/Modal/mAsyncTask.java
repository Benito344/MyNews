package com.behague.benjamin.mynews.Modal;

import android.os.AsyncTask;

import com.behague.benjamin.mynews.Controllers.Activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Benjamin BEHAGUE on 08/01/2018.
 */

public class mAsyncTask extends AsyncTask<String, Void, JSONObject> {

    private final String SECTION = "section";
    private final String TITLE = "title";
    private final String ABSTRACT = "abstract";
    private final String ARTICLE_URL = "url";
    private final String PUBLISHED_DATE = "published_date";
    private final String IMAGE_URL = "url";
    private String mStape;
    JSONObject jsonData;
    public static ArrayList <Article> articlesList_TOP = new ArrayList<> ();
    public static ArrayList <Article> articlesList_MOST = new ArrayList<> ();

    private MainActivity activity;

    public mAsyncTask(MainActivity activity){
        this.activity = activity;
    }


    @Override
    protected JSONObject doInBackground (String... params){
        InputStream inputStream;
        for(int j = 0 ; j < 2 ; j++) {
            try {

                URL url = new URL(params[j]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                inputStream = connection.getInputStream();
                int mStatut = connection.getResponseCode();
                if (mStatut < 400) {
                    String result = InputStreamToString(inputStream);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArrayResults = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArrayResults.length(); i++) {
                        JSONObject obj = jsonArrayResults.getJSONObject(i);
                        //JSONArray jsonArrayMultimedia = obj.getJSONArray("multimedia");
                        Article article = new Article();
                        article.setTitle(obj.getString(TITLE));
                        article.setAbstact(obj.getString(ABSTRACT));
                        article.setSection(obj.getString(SECTION));
                        article.setArticleUrl(obj.getString(ARTICLE_URL));
                        article.setPublishedDate(obj.getString(PUBLISHED_DATE));
                        if (params[j + 2] == "TOP") {
                            articlesList_TOP.add(article);
                        } else {
                            articlesList_MOST.add(article);
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonData;

    }


    @Override
    protected void onPostExecute(JSONObject mJSONobject){

        this.activity.getArticleList(articlesList_TOP, articlesList_MOST);
    }

    public static String InputStreamToString (InputStream in,int bufSize){
        final StringBuilder out = new StringBuilder();
        final byte[] buffer = new byte[bufSize];
        try{
            for(int ctr ; ( ctr = in.read(buffer)) !=-1;){
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return out.toString();
    }

    public static String InputStreamToString (InputStream in){
        return InputStreamToString(in, 1024);
    }
}

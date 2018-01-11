package com.behague.benjamin.mynews.Modal;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
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

    private JSONObject jsonData;

    private ArrayList <Article> articlesList = new ArrayList<> ();

    private Activity activity;

    public mAsyncTask(Activity activity){
        this.activity = activity;
    }


    @Override
    protected JSONObject doInBackground (String... params){
        InputStream inputStream;

        try{

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();
            inputStream = connection.getInputStream();
            String result = InputStreamToString(inputStream);
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArrayResults = new JSONArray(jsonObject.getString("results"));
            for(int i = 0; i< jsonArrayResults.length();i++){
                JSONObject obj = new JSONObject(jsonArrayResults.getString(i));
                Article article = new Article();
                article.setTitle(obj.getString(TITLE));
                article.setAbstact(obj.getString(ABSTRACT));
                article.setSection(obj.getString(SECTION));
                article.setArticleUrl(obj.getString(ARTICLE_URL));
                article.setPublishedDate(obj.getString(PUBLISHED_DATE));
                //article.setImageUrl(obj.getString("multimedia"));
                articlesList.add(article);
            }
            toFile(articlesList);

        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return jsonData;
    }

    public void toFile(ArrayList<Article> list){

        File file = new File(activity.getExternalFilesDir(null), "articlesList");
        try (FileOutputStream fos = activity.openFileOutput("Top.ser", Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(list);
        } catch (IOException e){
                 e.printStackTrace();
        }
        /*try {
            OutputStream output = new FileOutputStream(file);
            byte data[] = new byte[4096];
            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
        }catch (java.io.IOException e){
            e.printStackTrace();
        }*/
    }

  /*  @Override
    protected void onPostExecute(JSONObject mJSONobject){
        try{
            JSONArray articles = mJSONobject.getJSONArray("results");
            Article mArticles = new Article();
            JSONObject mobject = articles.getJSONObject(0);
            mArticles.setTitle(mobject.getString(TITLE));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }*/

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

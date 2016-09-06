package com.example.android.photos.Networking;

import android.os.AsyncTask;

import com.example.android.photos.Model.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Shimaa on 9/1/2016.
 */
public class DownloadTask extends AsyncTask<String, Void, List<Photo>> {

    @Override
    protected List<Photo> doInBackground(String... params) {

        List<Photo> list = new ArrayList<>();
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(params[0]).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                JSONObject object = new JSONObject(jsonString);
                JSONObject innerPhotoObject = object.getJSONObject("photos");
                JSONArray photoArray = innerPhotoObject.getJSONArray("photo");
                for(int i=0 ; i< photoArray.length() ; i++){
                    JSONObject photo = photoArray.getJSONObject(i);
                    list.add(new Photo(photo));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;

    }

    private String readResponse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        StringBuilder builder = new StringBuilder();
        while(true) {
            if(line == null) break;
            builder.append(line);
            line = reader.readLine();
        }

        //those three lines to remove the non correct characters from the Json String
        int start = builder.toString().indexOf("(") + 1;
        int end = builder.toString().length() - 1;
        String jSONString = builder.toString().substring( start, end);

        return jSONString;
    }



}

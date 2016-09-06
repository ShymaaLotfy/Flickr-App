package com.example.android.photos.Model;

import android.provider.ContactsContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Shimaa on 9/3/2016.
 */
public class Photo implements Serializable {

    public String imageUrl ;
    public String userId;

    public Photo(JSONObject object) throws JSONException {

        userId = object.getString("owner");
        String FARMID = object.getString("farm");
        String SERVERID = object.getString("server");
        String SECRET = object.getString("secret");
        String ID = object.getString("id");

        StringBuilder sb = new StringBuilder();

        sb.append("http://farm");
        sb.append(FARMID);
        sb.append(".staticflickr.com/");
        sb.append(SERVERID);
        sb.append("/");
        sb.append(ID);
        sb.append("_");
        sb.append(SECRET);
        sb.append("_m");
        sb.append(".jpg");

        imageUrl = sb.toString();
    }
}

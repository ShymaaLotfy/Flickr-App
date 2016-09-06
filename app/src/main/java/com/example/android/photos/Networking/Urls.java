package com.example.android.photos.Networking;

/**
 * Created by Shimaa on 9/3/2016.
 */
public class Urls {

    public final static String BASE_URL = "https://api.flickr.com/services/rest/";
    public final static String API_KEY = "&api_key=" + "481b22347ba25d8cb909f1452de90a0a" ;
    public final static String FORMAT =  "&format=json";
    public final static String BASE_SEARCH_URL = BASE_URL + "?method=flickr.photos.search&text=" ;
    public final static String BASE_USER_PHOTOS = BASE_URL + "?method=flickr.people.getPhotos" + API_KEY + "&user_id=";


}

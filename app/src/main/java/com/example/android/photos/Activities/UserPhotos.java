package com.example.android.photos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.photos.Adapters.ImageAdapter;
import com.example.android.photos.Model.Photo;
import com.example.android.photos.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shimaa on 9/3/2016.
 */
public class UserPhotos extends AppCompatActivity {

    private List<Photo> photos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_photos);

        //Get the clicked Movie
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        photos = (List<Photo>) bundle.getSerializable("list");

        GridView gridview = (GridView) findViewById(R.id.gridview);
        final ImageAdapter adapter = new ImageAdapter(getApplicationContext(),photos);
        gridview.setAdapter(adapter);


    }
}

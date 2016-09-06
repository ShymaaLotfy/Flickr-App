package com.example.android.photos.Activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;
;
import com.example.android.photos.Model.Photo;
import com.example.android.photos.Networking.DownloadTask;
import com.example.android.photos.Networking.Urls;
import com.example.android.photos.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Shimaa on 9/3/2016.
 */
public class DisplayPhotoActivity extends AppCompatActivity {

    private Photo openedPhoto;
    private ImageView imageV;
    private static int imageCounter = 1 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_photo);

        //Get the clicked Movie
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        openedPhoto = (Photo) bundle.getSerializable("openedPhoto");

        imageV = (ImageView) findViewById(R.id.opendPhoto);
        Picasso.with(getApplicationContext())
                .load(openedPhoto.imageUrl)
                .into(imageV);
    }

    public void openUserProfile(View v) {
        //String profileUrl = "http://www.flickr.com/people/" + openedPhoto.userId + "/" ;
        DownloadTask task = new DownloadTask() {
            @Override
            protected void onPostExecute(List<Photo> photos) {
                super.onPostExecute(photos);

                Intent intent = new Intent(getApplicationContext(), UserPhotos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) photos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };

        task.execute(Urls.BASE_USER_PHOTOS + openedPhoto.userId + Urls.FORMAT);
    }

    public void savePhoto(View v) throws FileNotFoundException {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+ "flickr"); //Creates app specific folder
        path.mkdirs();
        File imageFile = new File(path, "flickr" + imageCounter +".png"); // Imagename.png
        imageCounter ++;
        FileOutputStream out = new FileOutputStream(imageFile);
        try{
            imageV.setDrawingCacheEnabled(true);
            imageV.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
            out.flush();
            out.close();

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(getApplicationContext(),new String[] { imageFile.getAbsolutePath() }, null,new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                    Log.i("ExternalStorage", "Scanned " + path + ":");
                    Log.i("ExternalStorage", "-> uri=" + uri);
                }
            });
        } catch(Exception e) {

        }finally {
              Toast.makeText(this, " photo is saved " , Toast.LENGTH_SHORT).show();
        }
    }



}
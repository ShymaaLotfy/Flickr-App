package com.example.android.photos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.photos.Adapters.ImageAdapter;
import com.example.android.photos.Model.Photo;
import com.example.android.photos.Networking.DownloadTask;
import com.example.android.photos.Networking.Urls;
import com.example.android.photos.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shimaa on 9/6/2016.
 */
public class StartSearching extends AppCompatActivity implements SearchView.OnQueryTextListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_searching);
    }

    public  void logOut(View v){
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logOut();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);

    }


    //search methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        DownloadTask task = new DownloadTask(){
            @Override
            protected void onPostExecute(List<Photo> photos) {

                GridView gridview = (GridView) findViewById(R.id.gridview);
                final ImageAdapter adapter = new ImageAdapter(getApplicationContext(),photos);
                gridview.setAdapter(adapter);

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Intent intent = new Intent(parent.getContext(), DisplayPhotoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("openedPhoto", (Serializable) adapter.getItem(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        };

        task.execute(Urls.BASE_SEARCH_URL + query + Urls.API_KEY + Urls.FORMAT);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

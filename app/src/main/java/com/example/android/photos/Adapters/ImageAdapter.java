package com.example.android.photos.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.photos.Model.Photo;
import com.example.android.photos.Networking.DownloadTask;
import com.example.android.photos.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shimaa on 9/3/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Photo> list ;

    public ImageAdapter(Context c, List<Photo> photos) {
        mContext = c;
        list = photos;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext)
                .load(list.get(position).imageUrl)
                .into(imageView);

        return imageView;
    }


}
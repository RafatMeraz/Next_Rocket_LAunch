package com.example.dell.rocketlauncher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private ArrayList<ImageClass> imageList;
    private LayoutInflater inflater;
    private Context context;

    public ImageAdapter(ArrayList<ImageClass> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_image_view, parent, false);
        TextView textView = convertView.findViewById(R.id.imageNameTV);
        ImageView imageView = convertView.findViewById(R.id.ImageViewInActivity);
        textView.setText(imageList.get(position).getImageName());
        Glide.with(context).load(imageList.get(position).getImgURL()).into(imageView);

        return convertView;
    }
}


package com.example.dell.rocketlauncher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();

    private static String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=DEMO_KEY";
    private ArrayList<ImageClass> ImageList;

    private ProgressDialog pDialog;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();
    }

    private void initialization() {
        listView = findViewById(R.id.galleryListView);

        ImageList = new ArrayList<>();
        ImageList.add(new ImageClass("", R.drawable.imgone));
        ImageList.add(new ImageClass("", R.drawable.imgtwo));
        ImageList.add(new ImageClass("", R.drawable.imgthree));
        ImageList.add(new ImageClass("", R.drawable.imgfour));
        ImageList.add(new ImageClass("", R.drawable.imgfive));
        ImageList.add(new ImageClass("", R.drawable.imgsix));
        ImageList.add(new ImageClass("", R.drawable.imgseven));
        ImageList.add(new ImageClass("", R.drawable.imgeight));
        ImageList.add(new ImageClass("", R.drawable.imgnine));
        ImageList.add(new ImageClass("", R.drawable.imgten));
        ImageList.add(new ImageClass("", R.drawable.imgeleven));
        ImageList.add(new ImageClass("", R.drawable.imgtwelve));

        ImageAdapter adapter = new ImageAdapter(ImageList, this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}


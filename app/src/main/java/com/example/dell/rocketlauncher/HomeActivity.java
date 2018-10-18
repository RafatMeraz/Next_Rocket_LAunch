package com.example.dell.rocketlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView launchCardView, newsCardView, missionCardView, galleryCardView, liveCardView, educationCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //For initialize all the views
        initialization();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void initialization() {
        launchCardView = findViewById(R.id.launchCardView);
        newsCardView = findViewById(R.id.newsCardView);
        missionCardView = findViewById(R.id.missionCardView);
        galleryCardView = findViewById(R.id.galleryCardView);
        liveCardView = findViewById(R.id.liveCardView);
        educationCardView = findViewById(R.id.educationCardView);

        launchCardView.setOnClickListener(this);
        newsCardView.setOnClickListener(this);
        missionCardView.setOnClickListener(this);
        galleryCardView.setOnClickListener(this);
        liveCardView.setOnClickListener(this);
        educationCardView.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.launchCardView :
                intent = new Intent(HomeActivity.this, LaunchListActivity.class);
                startActivity(intent);
                break;
            case R.id.newsCardView:
                intent = new Intent(HomeActivity.this, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.missionCardView:
                intent = new Intent(HomeActivity.this, MissionActivity.class);
                startActivity(intent);
                break;
            case R.id.galleryCardView:

                break;
            case R.id.liveCardView:
                intent = new Intent(HomeActivity.this, WebViewActivity.class);
                Toast.makeText(this, "Loading Live! Please wait....", Toast.LENGTH_LONG).show();
                intent.putExtra("link", "https://www.youtube.com/watch?v=wwMDvPCGeE0");
                startActivity(intent);
                break;
            case R.id.educationCardView:

                break;
        }
    }
}

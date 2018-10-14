package com.example.dell.rocketlauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class LaunchListActivity extends AppCompatActivity {

    private RocketLaunchAdapter mAdapter;
    private RecyclerView launchRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_list);

        initialization();
    }

    private void initialization() {
        launchRecyclerView = findViewById(R.id.launchRecyclerView);

        //List of data which you get from api 
        List<LaunchRocket> list = null; 
        
        mAdapter = new RocketLaunchAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        launchRecyclerView.setLayoutManager(mLayoutManager);
        launchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        launchRecyclerView.setAdapter(mAdapter);
    }
}

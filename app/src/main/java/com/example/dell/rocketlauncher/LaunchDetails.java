package com.example.dell.rocketlauncher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class LaunchDetails extends AppCompatActivity {

    private TextView name, windowStrat, windowEnd,liveLink,TbdTime,TbdDate, netTime, countryCode, stationMap,
            location;

    private ImageView launchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_details2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();

    }

    private void initialization(){
        name=findViewById(R.id.LaunchNameTextView);
        windowStrat=findViewById(R.id.launchWindowStartTextView);
        windowEnd=findViewById(R.id.windowEndTextView);
        liveLink=findViewById(R.id.missionWikiURLTextView);
        TbdTime=findViewById(R.id.launchTbdTimeTextView);
        TbdDate=findViewById(R.id.launchTbdDateTextView);
        netTime = findViewById(R.id.launchTimeTextView);
        countryCode = findViewById(R.id.launchCountryCodeTextView);
        stationMap = findViewById(R.id.launchStationMapTextView);
        location = findViewById(R.id.detailsLaunchLocationTextView);
        launchImage =findViewById(R.id.launchDetailsImageView);

        Intent intent = getIntent();
        final Launch2 launch2 = (Launch2) intent.getSerializableExtra("launch");
        name.setText(launch2.getName());
        windowStrat.setText(launch2.getWindowStart());
        windowEnd.setText(launch2.getWindowEnd());
        netTime.setText(launch2.getNet());

        liveLink.setText(Html.fromHtml("<u>"+launch2.getLinks()+"</u> "));
        stationMap.setText(Html.fromHtml("<u>"+launch2.getStationMap()+"</u> "));

        TbdDate.setText(launch2.getTbdDate());
        TbdTime.setText(launch2.getTbdTime());
        countryCode.setText(launch2.getCountryCode());
        location.setText(launch2.getLocation());

        Glide.with(this).load(launch2.getImageURL()).into(launchImage);

        liveLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (launch2.getLinks().equals("Unknown")){
                    Toast.makeText(LaunchDetails.this, "Sorry! There is no URL for Live", Toast.LENGTH_LONG).show();
                }else {
                    Intent webIntent = new Intent(LaunchDetails.this, WebViewActivity.class);
                    webIntent.putExtra("link", launch2.getLinks());
                    Toast.makeText(LaunchDetails.this, "Please Wait! Website is Loading.....", Toast.LENGTH_LONG).show();
                    startActivity(webIntent);
                }
            }
        });

        stationMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = launch2.getStationMap().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

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

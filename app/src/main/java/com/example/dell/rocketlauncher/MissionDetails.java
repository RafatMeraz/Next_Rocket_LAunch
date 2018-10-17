 package com.example.dell.rocketlauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

 public class MissionDetails extends AppCompatActivity {

     private TextView missionNameTV, descriptionTV, typeTV, infoURLTV, wikiURLTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();
    }

     private void initialization() {

         final Intent intent = getIntent();
         final Mission mission = (Mission) intent.getSerializableExtra("mission");

         missionNameTV = findViewById(R.id.missionNameTextView);
         descriptionTV = findViewById(R.id.missionDescriptionTextView);
         typeTV = findViewById(R.id.missionTypeTextView);
         infoURLTV = findViewById(R.id.missionInfoURLTextView);
         wikiURLTV = findViewById(R.id.missionWikiURLTextView);

         missionNameTV.setText(mission.getName());
         descriptionTV.setText(mission.getDescription());
         typeTV.setText(mission.getType());
         infoURLTV.setText(Html.fromHtml("<u>"+ mission.getInfoURL()+"</u> "));
         wikiURLTV.setText(Html.fromHtml("<u>"+mission.getWikiURL()+"</u> "));

         final Intent webIntent = new Intent(MissionDetails.this, WebViewActivity.class);
         infoURLTV.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 webIntent.putExtra("link", mission.getInfoURL());
                 webIntent.putExtra("mName", mission.getName());
                 startActivity(webIntent);
             }
         });
         wikiURLTV.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 webIntent.putExtra("link", mission.getWikiURL());
                 startActivity(webIntent);
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

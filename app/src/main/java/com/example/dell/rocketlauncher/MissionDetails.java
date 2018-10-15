 package com.example.dell.rocketlauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

 public class MissionDetails extends AppCompatActivity {

     private TextView missionNameTV, descriptionTV, typeTV, infoURLTV, wikiURLTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_details);


        initialization();
    }

     private void initialization() {

         HashMap<String, String> mission  = (HashMap<String, String>) getIntent().getParcelableExtra("launch");

         missionNameTV = findViewById(R.id.missionNameTextView);
         descriptionTV = findViewById(R.id.missionDescriptionTextView);
         typeTV = findViewById(R.id.missionTypeTextView);
         infoURLTV = findViewById(R.id.missionInfoURLTextView);
         wikiURLTV = findViewById(R.id.missionWikiURLTextView);

         missionNameTV.setText(mission.get("name"));
         descriptionTV.setText(mission.get("description"));
         typeTV.setText(mission.get("type"));
         infoURLTV.setText(mission.get("infoURL"));
         wikiURLTV.setText(mission.get("wikiURL"));
    }
 }

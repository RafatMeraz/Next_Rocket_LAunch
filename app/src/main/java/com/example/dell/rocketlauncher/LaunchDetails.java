package com.example.dell.rocketlauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchDetails extends AppCompatActivity {

    private TextView name, windowStrat, windowEnd,liveLink,TbdTime,TbdDate, netTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_details2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();

    }

    private void initialization() {
        name=findViewById(R.id.LaunchNameTextView);
        windowStrat=findViewById(R.id.launchWindowStartTextView);
        windowEnd=findViewById(R.id.windowEndTextView);
        liveLink=findViewById(R.id.missionWikiURLTextView);
        TbdTime=findViewById(R.id.launchTbdTimeTextView);
        TbdDate=findViewById(R.id.launchTbdDateTextView);
        netTime = findViewById(R.id.launchTimeTextView);

        Intent intent = getIntent();
        final Launch2 launch2 = (Launch2) intent.getSerializableExtra("launch");
        name.setText(launch2.getName());
        windowStrat.setText(launch2.getWindowStart());
        windowEnd.setText(launch2.getWindowEnd());
        netTime.setText(launch2.getNet());

        liveLink.setText(Html.fromHtml("<u>"+launch2.getLinks()+"</u> "));

        TbdDate.setText(launch2.getTbdDate());
        TbdTime.setText(launch2.getTbdTime());

        liveLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(LaunchDetails.this, WebViewActivity.class);
                webIntent.putExtra("link", launch2.getLinks());
                Toast.makeText(LaunchDetails.this, "Please Wait! Website is Loading.....", Toast.LENGTH_LONG).show();
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

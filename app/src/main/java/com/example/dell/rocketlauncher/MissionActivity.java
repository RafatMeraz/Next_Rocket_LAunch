package com.example.dell.rocketlauncher;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MissionActivity extends AppCompatActivity {

    private static final String TAG = LaunchListActivity.class.getSimpleName();

    private static String url = "https://launchlibrary.net/1.3/mission";
    private ArrayList<HashMap<String, String>> contactList;

    private ProgressDialog pDialog;
    private ListView listView;
    private Mission m ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();
    }

    private void initialization() {
        listView = findViewById(R.id.missionListView);

        contactList = new ArrayList<>();
        new GetContacts().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MissionActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("missions");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String description = c.getString("description");
                        String type = c.getString("type");
                        String infoURL = c.getString("infoURL");
                        String wikiURL = c.getString("wikiURL");

                        m = new Mission(id, name, description, type, infoURL, wikiURL);

                        // tmp hash map for single launch
                        HashMap<String, String> mission = new HashMap<>();

                        // adding each child node to HashMap key => value
                        mission.put("id", id);
                        mission.put("name", name);
                        mission.put("description", description);
                        mission.put("type", type);
                        mission.put("infoURL", infoURL);
                        mission.put("wikiURL", wikiURL);
                        // adding launch to launch list
                        contactList.add(mission);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            android.widget.ListAdapter adapter = new SimpleAdapter(
                    MissionActivity.this, contactList,
                    R.layout.mission_layout, new String[]{"name", "description",
                    "type"}, new int[]{R.id.missionTitleTextView, R.id.missionDescriptionTextView, R.id.missionTypeTextView});

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MissionActivity.this, MissionDetails.class);
                    HashMap<String, String> launch = contactList.get(position);
                    String mid = launch.get("id");
                    String name = launch.get("name");
                    String description = launch.get("description");
                    String type = launch.get("type");
                    String infoURL = launch.get("infoURL");
                    String wikiURL = launch.get("wikiURL");
                    Mission mission = new Mission(mid, name, description, type, infoURL, wikiURL);
                    intent.putExtra("mission", mission);

                    startActivity(intent);
                }
            });
        }

    }


}

package com.example.dell.rocketlauncher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static java.security.AccessController.getContext;

public class LaunchListActivity extends AppCompatActivity {

    private static final String TAG = LaunchListActivity.class.getSimpleName();

    private static String url = "https://launchlibrary.net/1.3/launch";
    private  ArrayList<HashMap<String, String>> launchList;

    private ProgressDialog pDialog;

    private RocketLaunchAdapter mAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();
    }

    private void initialization() {
        listView = findViewById(R.id.launchSheduleListView);
        launchList = new ArrayList<>();

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
            pDialog = new ProgressDialog(LaunchListActivity.this);
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
                    JSONArray contacts = jsonObj.getJSONArray("launches");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String net = c.getString("net");
                        String tbdtime = c.getString("tbdtime");
                        String tbddate = c.getString("tbddate");

                        // tmp hash map for single launch
                        HashMap<String, String> launch = new HashMap<>();

                        // adding each child node to HashMap key => value
                        launch.put("id", id);
                        launch.put("name", name);
                        launch.put("net", net);
                        // adding launch to launch list
                        launchList.add(launch);
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
                    LaunchListActivity.this, launchList,
                    R.layout.launch_shedule_layout, new String[]{"name", "net"}
                    , new int[]{R.id.launchTitleTextView, R.id.launchTimeLocationTextView});

            listView.setAdapter(adapter);
        }

    }
}

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
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NewsActivity extends AppCompatActivity {


    private static final String TAG = MissionActivity.class.getSimpleName();

    private static String url = "http://hubblesite.org/api/v3/news";
    private ArrayList<HashMap<String, String>> newsList;

    private ProgressDialog pDialog;
    private ListView listView;
    Mission m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialization();
    }

    private void initialization() {
        listView = findViewById(R.id.newsListView);

        newsList = new ArrayList<>();
        new GetNews().execute();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(NewsActivity.this);
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
                    JSONArray news = new JSONArray(jsonStr);

                    // Getting JSON Array node

                    // looping through All Contacts
                    for (int i = 0; i < news.length(); i++) {
                        JSONObject c = news.getJSONObject(i);

                        String id = c.getString("news_id");
                        String name = c.getString("name");
                        String url = c.getString("url");

                        //m = new Mission(id, name, description, type, infoURL, wikiURL);

                        // tmp hash map for single launch
                        HashMap<String, String> mission = new HashMap<>();

                        // adding each child node to HashMap key => value
                        mission.put("news_id", id);
                        mission.put("name", name);
                        mission.put("url", url);
                        // adding launch to launch list
                        newsList.add(mission);
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
                    NewsActivity.this, newsList,
                    R.layout.single_news_layout, new String[]{"news_id", "name",
                    "url"}, new int[]{R.id.newsNumberTextView, R.id.newsDescriptionTextView, R.id.newsURLTextView});

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = newsList.get(position).get("url").toString();
                    Intent webIntent = new Intent(NewsActivity.this, WebViewActivity.class);
                    webIntent.putExtra("link", url);
                    Toast.makeText(NewsActivity.this, "Loading Website! Please wait...", Toast.LENGTH_LONG).show();
                    startActivity(webIntent);
                }
            });
        }

    }

}

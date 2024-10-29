package com.example.thirdapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordsAdapter adapter;
    private List<Record> records;
    private static final String BASE_URL = "http://10.0.2.2:3000"; // localhost for Android emulator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        records = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordsAdapter(records);
        recyclerView.setAdapter(adapter);

        // Load initial data
        fetchRecords();

        // Add RecordFormFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new RecordFormFragment())
                .commit();
    }

    public void fetchRecords() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = BASE_URL + "/records/getAll";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        records.clear();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject item = response.getJSONObject(i);
                                records.add(new Record(
                                        item.getInt("id"),
                                        item.getString("name"),
                                        item.getString("description")
                                ));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }
}
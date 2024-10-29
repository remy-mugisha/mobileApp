package com.example.thirdapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RecordFormFragment extends Fragment {
    private EditText nameInput;
    private EditText descriptionInput;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_record_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameInput = view.findViewById(R.id.editTextName);
        descriptionInput = view.findViewById(R.id.editTextDescription);
        submitButton = view.findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRecord();
            }
        });
    }

    private void submitRecord() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = "http://10.0.2.2:3000/records/create";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", nameInput.getText().toString());
            jsonObject.put("description", descriptionInput.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Clear form
                        nameInput.setText("");
                        descriptionInput.setText("");

                        // Refresh records list
                        if (getActivity() instanceof NetworkActivity) {
                            ((NetworkActivity) getActivity()).fetchRecords();
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

        queue.add(jsonObjectRequest);
    }
}
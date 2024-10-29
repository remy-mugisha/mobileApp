package com.example.thirdapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class ShoeListFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoe_list, container, false);

        dbHelper = new DatabaseHelper(getContext());
        listView = view.findViewById(R.id.shoeListView);

        displayShoeList();

        return view;
    }

    private void displayShoeList() {
        List<Shoe> shoeList = dbHelper.getAllShoes();
        ArrayAdapter<Shoe> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, shoeList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
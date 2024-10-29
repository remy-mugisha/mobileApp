package com.example.thirdapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    private ShoeAdapter shoeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        List<Shoe> shoeList = createShoeList();
        shoeAdapter = new ShoeAdapter(shoeList);
        recyclerView.setAdapter(shoeAdapter);

        return view;
    }

    private List<Shoe> createShoeList() {
        List<Shoe> shoes = new ArrayList<>();
        shoes.add(new Shoe("Nike", 10, true, 99.99));
        shoes.add(new Shoe("Adidas", 9, true, 89.99));
        shoes.add(new Shoe("Puma", 11, false, 79.99));
        shoes.add(new Shoe("Reebok", 8, true, 69.99));
        shoes.add(new Shoe("Vans", 10, true, 59.99));
        shoes.add(new Shoe("Converse", 9, true, 54.99));
        shoes.add(new Shoe("New Balance", 11, false, 84.99));
        shoes.add(new Shoe("Asics", 10, true, 94.99));
        shoes.add(new Shoe("Skechers", 8, true, 64.99));
        shoes.add(new Shoe("Under Armour", 9, false, 74.99));
        shoes.add(new Shoe("Fila", 10, true, 49.99));
        // Add more shoes if needed
        return shoes;
    }
}
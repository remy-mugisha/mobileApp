package com.example.thirdapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ShoeFormFragment extends Fragment {

    private EditText brand, size, price;
    private Switch inStockSwitch;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoe_form, container, false);

        brand = view.findViewById(R.id.brand);
        size = view.findViewById(R.id.size);
        price = view.findViewById(R.id.price);
        inStockSwitch = view.findViewById(R.id.inStockSwitch);

        dbHelper = new DatabaseHelper(getContext());

        Button saveButton = view.findViewById(R.id.button2);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveShoe();
            }
        });

        Button homeButton = view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void saveShoe() {
        String brandText = brand.getText().toString();
        int sizeInt = Integer.parseInt(size.getText().toString());
        double priceC = Double.parseDouble(price.getText().toString());
        boolean inStock = inStockSwitch.isChecked();

        Shoe shoe = new Shoe(brandText, sizeInt, inStock, priceC);
        long newRowId = dbHelper.insertShoe(shoe);

        if (newRowId != -1) {
            Toast.makeText(getContext(), "Shoe saved successfully", Toast.LENGTH_SHORT).show();
            clearForm();
        } else {
            Toast.makeText(getContext(), "Error saving shoe", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        brand.setText("");
        size.setText("");
        price.setText("");
        inStockSwitch.setChecked(false);
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
package com.example.thirdapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder> {

    private List<Shoe> shoeList;

    public ShoeAdapter(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_item, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeViewHolder holder, int position) {
        Shoe shoe = shoeList.get(position);
        holder.brandTextView.setText(shoe.getBrand());
        holder.sizeTextView.setText("Size: " + shoe.getSize());
        holder.priceTextView.setText("$" + String.format("%.2f", shoe.getPrice()));
        holder.stockTextView.setText(shoe.isInStock() ? "In Stock" : "Out of Stock");
    }

    @Override
    public int getItemCount() {
        return shoeList.size();
    }

    static class ShoeViewHolder extends RecyclerView.ViewHolder {
        TextView brandTextView, sizeTextView, priceTextView, stockTextView;

        ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            stockTextView = itemView.findViewById(R.id.stockTextView);
        }
    }
}
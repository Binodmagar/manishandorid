package com.binod.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.expensetracker.R;

import java.util.List;

public class TransactionAdpater extends RecyclerView.Adapter<TransactionAdpater.TransactionViewHolder> {

    Context context;

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class  TransactionViewHolder extends RecyclerView.ViewHolder {

        //declaration
        TextView tvCategory, tvAmount;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            //binding
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}

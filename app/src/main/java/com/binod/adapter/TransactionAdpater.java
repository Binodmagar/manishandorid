package com.binod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.expensetracker.R;
import com.binod.model.Expense;

import java.util.List;

public class TransactionAdpater extends RecyclerView.Adapter<TransactionAdpater.TransactionViewHolder> {

    Context context;
    List<Expense> expenseList;

    public TransactionAdpater(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_oneday,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        Expense expense = expenseList.get(position);
        holder.tvDayFood.setText(expense.getName());
        holder.tvDayAmount.setText(expense.getAmount() + "");
        holder.tvDayNote.setText(expense.getDescription());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class  TransactionViewHolder extends RecyclerView.ViewHolder {
        //declaration
        TextView tvDayFood,tvDayAmount,tvDayNote;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            //binding
           tvDayFood = itemView.findViewById(R.id.tvDayFood);
           tvDayAmount = itemView.findViewById(R.id.tvDayAmount);
           tvDayNote = itemView.findViewById(R.id.tvDayNote);
        }
    }
}

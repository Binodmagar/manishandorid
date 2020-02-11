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

public class TodayExpenseHomeAdapter extends RecyclerView.Adapter<TodayExpenseHomeAdapter.ExpenseHomeViewHolder>{

    Context context;
    List<Expense> expenseList;

    public TodayExpenseHomeAdapter(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_todaytransaction,parent,false);
        return new ExpenseHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHomeViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.tvNameH.setText(expense.getName());
//        holder.tvCatH.setText("Category: " + expense.getCategory());
//        holder.tvAccH.setText("Account: " + expense.getAccount());
        holder.tvAmountH.setText("Rs " + expense.getAmount() + "");
        holder.tvDesH.setText(expense.getDescription());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseHomeViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameH, tvCatH, tvAccH, tvAmountH,tvDesH;
        public ExpenseHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameH = itemView.findViewById(R.id.tvNameH);
//            tvAccH = itemView.findViewById(R.id.tvAccH);
//            tvCatH = itemView.findViewById(R.id.tvCatH);
            tvAmountH = itemView.findViewById(R.id.tvAmountH);
            tvDesH = itemView.findViewById(R.id.tvDesH);
        }
    }
}

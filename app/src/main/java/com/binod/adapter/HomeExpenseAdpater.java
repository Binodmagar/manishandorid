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

public class HomeExpenseAdpater extends RecyclerView.Adapter<HomeExpenseAdpater.TransactionViewHolder> {

    Context context;
    List<Expense> expenseList;

    public HomeExpenseAdpater(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_expensehome,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        Expense expense = expenseList.get(position);
        holder.tvNameHEO.setText(expense.getName());
        holder.tvAmountHEO.setText("Rs " + expense.getAmount() + "");
        holder.tvDesHEO.setText(expense.getDescription());
//
//        Income income =  incomeList.get(position);
//        holder.tvAllName.setText("Income Name: " + income.getName());
//        holder.tvAllAmount.setText("Rs: " + income.getAmount() + "");
//        holder.tvAllCat.setText("Category: " + income.getCategory());
//        holder.tvAllAcc.setText("Account: " + income.getAccount());
//        holder.tvAllDesc.setText(income.getDescription());
//        holder.tvDayNote.setText(expense.getDescription());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class  TransactionViewHolder extends RecyclerView.ViewHolder {
        //declaration
        TextView tvNameHEO,tvAmountHEO,tvDesHEO;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            //binding
            tvNameHEO = itemView.findViewById(R.id.tvNameHEO);
            tvAmountHEO = itemView.findViewById(R.id.tvAmountHEO);
            tvDesHEO = itemView.findViewById(R.id.tvDesHEO);
        }
    }
}

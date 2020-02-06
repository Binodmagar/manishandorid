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
import com.binod.model.Income;

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
                .inflate(R.layout.activity_alltransaction,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        Expense expense = expenseList.get(position);
        holder.tvAllName.setText("Expense Name: " + expense.getName());
        holder.tvAllAmount.setText("Rs: " + expense.getAmount() + "");
        holder.tvAllCat.setText("Category: " + expense.getCategory());
        holder.tvAllAcc.setText("Account: " + expense.getAccount());
        holder.tvAllDesc.setText("Description: " + expense.getDescription());
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
        TextView tvAllName,tvAllAmount,tvAllCat,tvAllAcc,tvAllDesc;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            //binding
            tvAllName = itemView.findViewById(R.id.tvAllName);
            tvAllAmount = itemView.findViewById(R.id.tvAllAmount);
            tvAllCat = itemView.findViewById(R.id.tvAllCat);
            tvAllAcc = itemView.findViewById(R.id.tvAllAcc);
            tvAllDesc = itemView.findViewById(R.id.tvAllDes);
        }
    }
}

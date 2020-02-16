package com.binod.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.expensetracker.R;
import com.binod.expensetracker.UpdateExpenseActivity;
import com.binod.model.Expense;

import java.util.List;

public class TransactionExpenseAdapter extends RecyclerView.Adapter<TransactionExpenseAdapter.TransactionExpenseViewHolder> {
    Context context;
    List<Expense> expenseList;

    public TransactionExpenseAdapter(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public TransactionExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_allexpensetransaction,parent,false);
        return new TransactionExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionExpenseViewHolder holder, int position) {
        final Expense expense = expenseList.get(position);
        holder.tvAllNameE.setText(expense.getName());
        holder.tvAllAmountE.setText("Rs " + expense.getAmount() + "");
        holder.tvAllCatE.setText("Category: " + expense.getCategory());
        holder.tvAllAccE.setText("Account: " + expense.getAccount());
        holder.tvAllNoteE.setText(expense.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nameE", expense.getName());
                bundle.putString("amountE", String.valueOf(expense.getAmount()));
                bundle.putString("accountE", expense.getAccount());
                bundle.putString("categoryE", expense.getCategory());
                bundle.putString("noteE", expense.getDescription());

                Intent intent = new Intent(context, UpdateExpenseActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }


    public class TransactionExpenseViewHolder extends RecyclerView.ViewHolder {

        TextView tvAllNameE, tvAllCatE, tvAllAccE, tvAllAmountE, tvAllNoteE;
        public TransactionExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAllNameE = itemView.findViewById(R.id.tvAllNameE);
            tvAllAmountE = itemView.findViewById(R.id.tvAllAmountE);
            tvAllCatE = itemView.findViewById(R.id.tvAllCatE);
            tvAllAccE = itemView.findViewById(R.id.tvAllAccE);
            tvAllNoteE = itemView.findViewById(R.id.tvAllNoteE);
        }
    }

}

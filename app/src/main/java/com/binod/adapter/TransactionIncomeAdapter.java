package com.binod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.expensetracker.R;
import com.binod.model.Income;

import java.util.List;

public class TransactionIncomeAdapter extends RecyclerView.Adapter<TransactionIncomeAdapter.TransactionIncomeViewHolder>{

    Context context;
    List<Income> incomeList;

    public TransactionIncomeAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @NonNull
    @Override
    public TransactionIncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_allincometransaction,parent,false);
        return new TransactionIncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionIncomeViewHolder holder, int position) {
        Income income = incomeList.get(position);
        holder.tvAllNameI.setText(income.getName());
        holder.tvAllAmountI.setText("Rs " + income.getAmount());
        holder.tvAllCatI.setText("Category: " + income.getCategory());
        holder.tvAllAccI.setText("Account: " + income.getAccount());
        holder.tvAllNoteI.setText(income.getDescription());
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class TransactionIncomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvAllNameI, tvAllCatI, tvAllAccI, tvAllAmountI, tvAllNoteI;
        public TransactionIncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            //binding
            tvAllNameI = itemView.findViewById(R.id.tvAllNameI);
            tvAllAmountI = itemView.findViewById(R.id.tvAllAmountI);
            tvAllCatI = itemView.findViewById(R.id.tvAllCatI);
            tvAllAccI = itemView.findViewById(R.id.tvAllAccI);
            tvAllNoteI = itemView.findViewById(R.id.tvAllNoteI);
        }
    }
}

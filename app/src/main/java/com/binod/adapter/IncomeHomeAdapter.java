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

public class IncomeHomeAdapter extends RecyclerView.Adapter<IncomeHomeAdapter.IncomeHomeViewHolder> {

    Context context;
    List<Income> incomeList;

    public IncomeHomeAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @NonNull
    @Override
    public IncomeHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_todaytransaction,parent,false);
        return new IncomeHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeHomeViewHolder holder, int position) {
        Income income = incomeList.get(position);
        holder.tvNameH.setText("Income name: " + income.getName());
        holder.tvAmountH.setText("Rs: " + income.getAmount());
        holder.tvAccH.setText("Account: " + income.getAccount());
        holder.tvCatH.setText("Category: " + income.getCategory());
        holder.tvDesH.setText("Description: " + income.getDescription());
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class IncomeHomeViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameH, tvCatH, tvAccH, tvAmountH,tvDesH;
        public IncomeHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameH = itemView.findViewById(R.id.tvNameH);
            tvAccH = itemView.findViewById(R.id.tvAccH);
            tvCatH = itemView.findViewById(R.id.tvCatH);
            tvAmountH = itemView.findViewById(R.id.tvAmountH);
            tvDesH = itemView.findViewById(R.id.tvDesH);

        }
    }
}

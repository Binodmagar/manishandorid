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

public class TodayIncomeHomeAdapter extends RecyclerView.Adapter<TodayIncomeHomeAdapter.IncomeHomeViewHolder> {

    Context context;
    List<Income> incomeList;

    public TodayIncomeHomeAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @NonNull
    @Override
    public IncomeHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_todaytransactionincome,parent,false);
        return new IncomeHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeHomeViewHolder holder, int position) {
        Income income = incomeList.get(position);
        holder.tvNameHI.setText(income.getName());
        holder.tvAmountHI.setText("Rs " + income.getAmount());
//        holder.tvAccH.setText("Account: " + income.getAccount());
//        holder.tvCatH.setText("Category: " + income.getCategory());
        holder.tvDesHI.setText(income.getDescription());
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class IncomeHomeViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameHI, tvCatH, tvAccH, tvAmountHI,tvDesHI;
        public IncomeHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameHI = itemView.findViewById(R.id.tvNameHI);
//            tvAccH = itemView.findViewById(R.id.tvAccH);
//            tvCatH = itemView.findViewById(R.id.tvCatH);
            tvAmountHI = itemView.findViewById(R.id.tvAmountHI);
            tvDesHI = itemView.findViewById(R.id.tvDesHI);

        }
    }
}

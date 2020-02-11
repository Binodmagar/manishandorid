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

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    Context context;
    List<Income> incomeList;

    public IncomeAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_alltransactionincome,parent,false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        Income income = incomeList.get(position);
        holder.tvDayFoodI.setText("Income Name: " +income.getName());
        holder.tvDayAmountI.setText("Rs: " + income.getAmount() + "");
        holder.tvDayCatI.setText("Category: " + income.getCategory());
        holder.tvDayAccountI.setText("Account: " + income.getAccount());
        holder.tvDayNoteI.setText("Description: " + income.getDescription());

    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayFoodI,tvDayAmountI,tvDayNoteI, tvDayCatI, tvDayAccountI;
        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDayFoodI = itemView.findViewById(R.id.tvDayFoodI);
            tvDayAmountI = itemView.findViewById(R.id.tvDayAmountI);
            tvDayCatI = itemView.findViewById(R.id.tvDayCatI);
            tvDayAccountI = itemView.findViewById(R.id.tvDayAccountI);
            tvDayNoteI = itemView.findViewById(R.id.tvDayNoteI);
        }
    }


}

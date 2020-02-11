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

public class HomeIncomeAdapter extends RecyclerView.Adapter<HomeIncomeAdapter.IncomeViewHolder> {

    Context context;
    List<Income> incomeList;

    public HomeIncomeAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_incomehome,parent,false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        Income income = incomeList.get(position);
        holder.tvNameHIO.setText(income.getName());
        holder.tvAmountHIO.setText("Rs " + income.getAmount() + "");
        holder.tvDesHIO.setText(income.getDescription());
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameHIO,tvAmountHIO,tvDesHIO;
        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameHIO = itemView.findViewById(R.id.tvNameHIO);
            tvAmountHIO = itemView.findViewById(R.id.tvAmountHIO);
            tvDesHIO = itemView.findViewById(R.id.tvDesHIO);
        }
    }


}

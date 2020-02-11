package com.binod.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.binod.adapter.HomeExpenseAdpater;
import com.binod.api.ExpenseAPI;
import com.binod.expensetracker.R;
import com.binod.model.Expense;
import com.binod.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFragment extends Fragment {


    RecyclerView rvExpenseFragment;
    public ExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expense, container, false);

        rvExpenseFragment = view.findViewById(R.id.rvExpenseFragment);

        recycleView();
        return  view;
    }


    public void recycleView(){
        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);
        Call<List<Expense>> listCall = expenseAPI.getByUser(Url.token);
        listCall.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Expense> expenseList = response.body();
                HomeExpenseAdpater homeExpenseAdpater = new HomeExpenseAdpater(getActivity(), expenseList);
                rvExpenseFragment.setAdapter(homeExpenseAdpater);
                rvExpenseFragment.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {

                Toast.makeText(getActivity(), "failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

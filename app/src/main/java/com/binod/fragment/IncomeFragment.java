package com.binod.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.binod.adapter.IncomeAdapter;
import com.binod.api.IncomeAPI;
import com.binod.expensetracker.R;
import com.binod.model.Income;
import com.binod.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {

    RecyclerView rcIncome;

    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_income, container, false);

        rcIncome = view.findViewById(R.id.rcIncome);
        recycleView();
        return  view;
    }

    public void recycleView(){
        IncomeAPI expenseAPI = Url.getInstance().create(IncomeAPI.class);
        Call<List<Income>> listCall = expenseAPI.getByUser(Url.token);
        listCall.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(Call<List<Income>> call, Response<List<Income>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Income> incomes = response.body();
                IncomeAdapter incomeAdapter = new IncomeAdapter(getContext(), incomes);
                rcIncome.setAdapter(incomeAdapter);
                rcIncome.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void onFailure(Call<List<Income>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}

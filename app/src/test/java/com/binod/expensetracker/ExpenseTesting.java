package com.binod.expensetracker;

import android.content.Context;

import com.binod.bll.ExpenseBLL;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExpenseTesting {

    ExpenseBLL expenseBLL;

    @Before
    public void setUp(){
        expenseBLL = new ExpenseBLL("Momo",100, "Food", "cash", "2","2","2020","College breakfast");
    }

    @Test
    public void testExpense(Context context){
       boolean result = expenseBLL.addExpense(context);
       assertEquals(true, result);
    }
}

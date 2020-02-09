package com.binod.expensetracker;

import android.content.Context;

import com.binod.bll.IncomeBLL;
import com.binod.model.Income;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class IncomeTesting {
    IncomeBLL incomeBLL;

    @Before
    public void setUp(){
       incomeBLL = new IncomeBLL("Salary",10000, "Salary","cash","2", "1", "2020","Income of jan month");
    }

    @Test
    public void testIncome(Context context){
        boolean result = incomeBLL.addIncome(context);
       assertEquals(true, result);
    }
}

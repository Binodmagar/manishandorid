package com.binod.expensetracker;

import com.binod.bll.ExpenseBLL;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExpenseTesting {

    ExpenseBLL expenseBLL;

    @Before
    public void setUp(){
        expenseBLL = new ExpenseBLL("Momo",100, "Food", "cash", "2020/01/7","College breakfast");
    }

    @Test
    public void testExpense(){
        boolean result = expenseBLL.addExpense();
        assertEquals(true, result);
    }
}

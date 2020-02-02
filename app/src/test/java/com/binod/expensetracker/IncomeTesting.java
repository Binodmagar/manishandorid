package com.binod.expensetracker;

import com.binod.bll.IncomeBLL;
import com.binod.model.Income;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class IncomeTesting {
    IncomeBLL incomeBLL;

    @Before
    public void setUp(){
        incomeBLL = new IncomeBLL("Salary",10000, "Salary","cash","2020/01/2","Income of jan month");
    }

    @Test
    public void testIncome(){
        boolean result = incomeBLL.addIncome();
        assertEquals(true, result);
    }
}

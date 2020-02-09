package com.binod.expensetracker;

import com.binod.bll.RegisterBLL;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class RegisterTesting {

    RegisterBLL registerBLL;
    @Before
    public void setUp(){
        registerBLL = new RegisterBLL("john", "magar", "9898000123", "john@gmail.com","john12345","imageFile-1580640856878.png");

    }
    @Test
    public void testRegister(){
        boolean result = registerBLL.addUser();
        assertEquals(true, result);
    }
}

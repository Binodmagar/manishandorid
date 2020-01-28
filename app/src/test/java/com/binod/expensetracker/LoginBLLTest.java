package com.binod.expensetracker;

import com.binod.bll.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginBLLTest {

    @Test
    public void testLogin(){
        com.binod.bll.LoginBLL loginBLL = new com.binod.bll.LoginBLL();
        boolean result = loginBLL.checkUser("binod@gmail.com", "admin12345");
        assertEquals(true, result);
    }
}

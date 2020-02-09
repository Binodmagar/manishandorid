package com.binod.expensetracker;

import com.binod.bll.LoginBLL;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginBLLTest {
    com.binod.bll.LoginBLL loginBLL ;
    @Before
    public void setUp(){
        loginBLL = new com.binod.bll.LoginBLL();
    }

    @Test
    public void testLogin(){

        boolean result = loginBLL.checkUser("roman@gmail.com", "roman12345");
        assertEquals(true, result);
    }
}

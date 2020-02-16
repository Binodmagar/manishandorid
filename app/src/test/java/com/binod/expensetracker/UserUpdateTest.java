package com.binod.expensetracker;

import android.content.Context;

import com.binod.bll.UpdateProfile;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserUpdateTest {

    UpdateProfile updateProfile;
    @Before
    public void setUp(){
        updateProfile = new UpdateProfile("john", "magar", "9898000123", "john@gmail.com","john12345","imageFile-1580640856878.png");

    }
    @Test
    public void testRegister(Context context){
        boolean result = updateProfile.updateUser(context);
        assertEquals(true, result);
    }
}

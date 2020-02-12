package com.binod.expensetracker;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;


public class RegisterInstrumentedTest {

    String imgProfile,etFirstName, etLastName, etMobileNumber, etRemail, etRpassword,etConfirmPassword ;


    @Before
    public void setup() {
        imgProfile="imageFile-1581327029088.png";
        etFirstName = "Saroj";
        etLastName = "Pathak";
        etMobileNumber = "0909090900";
        etRemail = "saroj@gmail.com";
        etRpassword="saroj12345";
        etConfirmPassword="saroj12345";

    }
    @Rule
    public ActivityTestRule<RegisterActivity>
    testRule = new ActivityTestRule<>(RegisterActivity.class);


    @Test
    public void registerUser(){
        Espresso.onView(withId(R.id.etFirstName))
                .perform(ViewActions.typeText(String.valueOf(etFirstName)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLastName))
                .perform(ViewActions.typeText(String.valueOf(etLastName)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMobileNumber))
                .perform(ViewActions.typeText(String.valueOf(etMobileNumber)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRemail))
                .perform(ViewActions.typeText(String.valueOf(etRemail)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRpassword))
                .perform(ViewActions.typeText(String.valueOf(etRpassword)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etConfirmPassword))
                .perform(ViewActions.typeText(String.valueOf(etConfirmPassword)), ViewActions.closeSoftKeyboard());
        Espresso.onView(allOf(withText(imgProfile),
                withParent(withId(R.id.imgProfile))));

        Intents.init();
        Espresso.onView(withId(R.id.btnSignUp))
                .perform(ViewActions.click());
        Intents.release();

    }
}


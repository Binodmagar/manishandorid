package com.binod.expensetracker;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterInstrumentedTest {

    @Rule
    public ActivityTestRule<RegisterActivity>
    testRule = new ActivityTestRule<>(RegisterActivity.class);


    @Test
    public void registerUser(){
        onView(withId(R.id.etFirstName)).perform(typeText("magar"), closeSoftKeyboard());
        onView(withId(R.id.etLastName)).perform(typeText("magar"), closeSoftKeyboard());
        onView(withId(R.id.etMobileNumber)).perform(typeText("9090001234"), closeSoftKeyboard());
        onView(withId(R.id.etRemail)).perform(typeText("eragon@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etRpassword)).perform(typeText("eragon12345"), closeSoftKeyboard());
        onView(withId(R.id.etConfirmPassword)).perform(typeText("eragon12345"), closeSoftKeyboard());
        onView(withId(R.id.imgProfile)).perform(typeText("imageFile-1580640856878.png"), closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .check(matches(isDisplayed()));
    }
}

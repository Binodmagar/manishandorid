package com.binod.expensetracker;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity>
            testRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkUser(){
        onView(withId(R.id.etEmail))
                .perform(typeText("admin@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.etPassword))
                .perform(typeText("admin12345"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .perform(click());

    }
}

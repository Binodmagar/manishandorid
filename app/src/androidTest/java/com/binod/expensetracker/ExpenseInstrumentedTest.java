package com.binod.expensetracker;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExpenseInstrumentedTest {

    @Rule
    public ActivityTestRule<AddIncomeActivity>
            testRule = new ActivityTestRule<>(AddIncomeActivity.class);

    @Test
    public void addExpense(){
        onView(withId(R.id.etNameAE)).perform(typeText("Salary"), closeSoftKeyboard());
        onView(withId(R.id.etAmountAE)).perform(typeText("1000"), closeSoftKeyboard());

//        onView(withId(R.id.spIncomeAI)).perform(click());
//        onData(allOf(is(instanceOf(String.class)), is(selectionText))).perform(click());
//        onView(withId(R.id.spIncomeAI)).check(matches(withSpinnerText(containsString("salary"))));
        onView(withId(R.id.spCategoryAE)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spCategoryAE)).check(matches(withSpinnerText(containsString("Salary"))));

        onView(withId(R.id.spAccountAE)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spAccountAE)).check(matches(withSpinnerText(containsString("Cash"))));

        onView(withId(R.id.etNoteAE)).perform(typeText("Salary of the month"), closeSoftKeyboard());
        onView(withId(R.id.tvDateAE)).perform(typeText("1000"), closeSoftKeyboard());

        onView(withId(R.id.btnSaveAE))
                .perform(click());

        onView(withId(R.id.tvToday))
                .check(matches(isDisplayed()));
    }
}

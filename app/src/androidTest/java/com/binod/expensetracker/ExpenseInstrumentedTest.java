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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.stringContainsInOrder;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExpenseInstrumentedTest {
    @Rule
    public ActivityTestRule<AddExpenseActivity>
    testRule = new ActivityTestRule<>(AddExpenseActivity.class);

    @Test
    public void addExpense(){
        onView(withId(R.id.etNameAE))
                .perform(typeText("momo"),closeSoftKeyboard());


        onView(withId(R.id.etAmountAE))
                .perform(typeText(String.valueOf(100)), closeSoftKeyboard());


        onView(withId(R.id.spCategoryAE)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spCategoryAE)).check(matches(withSpinnerText(containsString("Food"))));

        onView(withId(R.id.spAccountAE)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spAccountAE)).check(matches(withSpinnerText(containsString("Cash"))));

        onView(withId(R.id.etDayE))
                .perform(typeText("2"))
                .perform(closeSoftKeyboard());


        onView(withId(R.id.etMonthE))
                .perform(typeText("2"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.etYearE))
                .perform(typeText("2020"))
                .perform(closeSoftKeyboard());


        onView(withId(R.id.etNoteAE))
                .perform(typeText("Today breakfast"))
                .perform(closeSoftKeyboard());


        onView(withId(R.id.btnSaveExpense))
                .perform(click());
    }
}

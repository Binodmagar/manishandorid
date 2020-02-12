package com.binod.expensetracker;


import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

public class ExpenseInstrumentedTest {

    String etNameAE,etAmountAE, etDayE, etMonthE, etYearE, etNoteAE;
    @Before
    public void setup() {
        etNameAE="momo";
        etAmountAE = String.valueOf(1000);
        etDayE = "2";
        etMonthE = "1";
        etYearE = "2020";
        etNoteAE="College lunch";
    }

    @Rule
    public ActivityTestRule<AddExpenseActivity>
    testRule = new ActivityTestRule<>(AddExpenseActivity.class);

    @Test
    public void addExpense(){

        onView(withId(R.id.etNameAE))
                .perform(ViewActions.typeText(String.valueOf(etNameAE)), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etAmountAE))
                .perform(ViewActions.typeText(String.valueOf(etAmountAE)), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.spCategoryAE)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spCategoryAE)).check(matches(withSpinnerText(containsString("Food"))));

        onView(withId(R.id.spAccountAE)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spAccountAE)).check(matches(withSpinnerText(containsString("Cash"))));

        onView(withId(R.id.etDayE))
                .perform(ViewActions.typeText(String.valueOf(etDayE)), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etMonthE))
                .perform(ViewActions.typeText(String.valueOf(etMonthE)), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etYearE))
                .perform(ViewActions.typeText(String.valueOf(etYearE)), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etNoteAE))
                .perform(ViewActions.typeText(String.valueOf(etNoteAE)), ViewActions.closeSoftKeyboard());

        Intents.init();
        onView(withId(R.id.btnSaveAE))
                .perform(click());
        Intents.release();
    }
}

package com.binod.expensetracker;

import androidx.test.espresso.Espresso;
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

public class IncomeInstrumentedTest {

    String etNameAI,etAmountAI, etDayI, etMonthI, etYearI, etNoteAI;
    @Before
    public void setup() {
        etNameAI="Salary";
        etAmountAI = String.valueOf(10000);
        etDayI = "2";
        etMonthI = "1";
        etYearI = "2020";
        etNoteAI="Jan months salary";
    }
    @Rule
    public ActivityTestRule<AddIncomeActivity>
    testRule = new ActivityTestRule<>(AddIncomeActivity.class);

    @Test
    public void addIncome(){
            onView(withId(R.id.etNameAI))
                    .perform(ViewActions.typeText(String.valueOf(etNameAI)), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.etAmountAI))
                    .perform(ViewActions.typeText(String.valueOf(etAmountAI)), ViewActions.closeSoftKeyboard());

            onView(withId(R.id.spIncomeAI)).perform(click());
            onData(anything()).atPosition(0).perform(click());
            onView(withId(R.id.spIncomeAI)).check(matches(withSpinnerText(containsString("Salary"))));

            onView(withId(R.id.spAccountAI)).perform(click());
             onData(anything()).atPosition(0).perform(click());
            onView(withId(R.id.spAccountAI)).check(matches(withSpinnerText(containsString("Cash"))));

            onView(withId(R.id.etDayI))
                    .perform(ViewActions.typeText(String.valueOf(etDayI)), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.etMonthI))
                    .perform(ViewActions.typeText(String.valueOf(etMonthI)), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.etYearI))
                    .perform(ViewActions.typeText(String.valueOf(etYearI)), ViewActions.closeSoftKeyboard());

            onView(withId(R.id.etNoteAI))
                    .perform(ViewActions.typeText(String.valueOf(etNoteAI)), ViewActions.closeSoftKeyboard());

            Intents.init();
            onView(withId(R.id.btnSaveAI))
                    .perform(click());
            Intents.release();
    }
}

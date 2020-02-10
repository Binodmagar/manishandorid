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
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IncomeInstrumentedTest {

    @Rule
    public ActivityTestRule<AddIncomeActivity>
    testRule = new ActivityTestRule<>(AddIncomeActivity.class);

    @Test
    public void addIncome(){
        onView(withId(R.id.etNameAI)).perform(typeText("Salary"), closeSoftKeyboard());
        onView(withId(R.id.etAmountAI)).perform(typeText("1000"), closeSoftKeyboard());

        onView(withId(R.id.spIncomeAI)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spIncomeAI)).check(matches(withSpinnerText(containsString("Salary"))));

        onView(withId(R.id.spAccountAI)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spAccountAI)).check(matches(withSpinnerText(containsString("Cash"))));


        onView(withId(R.id.etDayI)).perform(typeText("30"), closeSoftKeyboard());
        onView(withId(R.id.etMonthI)).perform(typeText("9"), closeSoftKeyboard());
        onView(withId(R.id.etYearI)).perform(typeText("2020"), closeSoftKeyboard());
        onView(withId(R.id.etNoteAI)).perform(typeText("Salary of the month"), closeSoftKeyboard());

        onView(withId(R.id.btnSaveIncome))
                .perform(click());


    }



}

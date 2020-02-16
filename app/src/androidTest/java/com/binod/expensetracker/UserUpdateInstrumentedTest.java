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

public class UserUpdateInstrumentedTest {

    String imgProfileUP,etFirstNameUP, etLastNameUP, etMobileNumberUP, etEmailUP, etPasswordUP,btnUpdate ;

    @Rule
    public ActivityTestRule<UpdateProfileActiivty>
            testRule = new ActivityTestRule<>(UpdateProfileActiivty.class);


    @Before
    public void setup() {
        imgProfileUP="imageFile-1581327029088.png";
        etFirstNameUP = "Saroj";
        etLastNameUP = "Pathak";
        etMobileNumberUP = "0909090900";
        etEmailUP = "saroj@gmail.com";
        etPasswordUP="saroj12345";

    }

    @Test
    public void registerUser(){
        Espresso.onView(withId(R.id.etFirstNameUP))
                .perform(ViewActions.typeText(String.valueOf(etFirstNameUP)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLastNameUP))
                .perform(ViewActions.typeText(String.valueOf(etLastNameUP)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMobileNumberUP))
                .perform(ViewActions.typeText(String.valueOf(etMobileNumberUP)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etEmailUP))
                .perform(ViewActions.typeText(String.valueOf(etEmailUP)), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPasswordUP))
                .perform(ViewActions.typeText(String.valueOf(etPasswordUP)), ViewActions.closeSoftKeyboard());
        Espresso.onView(allOf(withText(imgProfileUP),
                withParent(withId(R.id.imgProfileUP))));

        Intents.init();
        Espresso.onView(withId(R.id.btnUpdate))
                .perform(ViewActions.click());
        Intents.release();

    }


}

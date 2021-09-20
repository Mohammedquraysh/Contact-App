package com.example.firebase

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    @get: Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun check_if_data_isValid() {
        onView(withId(R.id.firstName)).perform(typeText("Mohammed"), closeSoftKeyboard())
        onView(withId(R.id.lastName)).perform(typeText("Quraysh"), closeSoftKeyboard())
        onView(withId(R.id.number)).perform(typeText("08130288099"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
    }


    @Test
    fun check_if_data_isInvalid() {
        onView(withId(R.id.firstName)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.lastName)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.number)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
    }


}
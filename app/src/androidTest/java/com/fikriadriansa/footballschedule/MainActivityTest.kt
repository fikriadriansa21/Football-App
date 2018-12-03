package com.fikriadriansa.footballschedule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.fikriadriansa.footballschedule.R.id.*
import com.fikriadriansa.footballschedule.activity.MatchDetailActivity
import com.fikriadriansa.footballschedule.main.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testFavoriteEvent(){
        Thread.sleep(2000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(rvEvent)).check(matches(isDisplayed()))
        onView(withText("Arsenal")).check(matches(isDisplayed()))
        onView(withText("Arsenal")).perform(click())
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())

        Thread.sleep(1000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
        Thread.sleep(1000)
        onView(withId(rvFav)).check(matches(isDisplayed()))
    }

}
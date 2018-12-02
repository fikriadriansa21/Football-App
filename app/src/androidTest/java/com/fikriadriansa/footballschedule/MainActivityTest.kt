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
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MatchDetailActivity::class.java)

    @Test
    fun testListEvent(){
        onView(withId(rvEvent)).check(matches(isDisplayed()))
        onView(withId(rvEvent)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rvEvent)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testFavoriteEvent(){
        onView(withId(rvEvent)).check(matches(isDisplayed()))
        onView(withId(rvEvent)).perform(click())
        onView(withText("Newcastle vs West Ham")).check(matches(isDisplayed()))
        onView(withText("Newcastle vs West Ham")).perform(click())
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(isDisplayed()))
        pressBack()

        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())



    }

}
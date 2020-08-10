package com.outdoors.breakingbadchars

import android.content.Context
import android.widget.SearchView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*

import com.outdoors.breakingbadchars.main.MainActivity
import com.outdoors.breakingbadchars.util.CharacterListAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test



@HiltAndroidTest
class ApplicationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun runApp()
    {
        ActivityScenario.launch(MainActivity::class.java)

        sleep(3000)
        onView(withId(R.id.chars_list_view))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.chars_list_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterListAdapter.ViewHolder>(0,
                ViewActions.click()
            ))
        sleep(2000)
        onView(withId(R.id.charStatus)).check(matches(ViewMatchers.withText("Presumed dead")))
        sleep(1000)

        pressBack()
        sleep(1000)
    }

    @Test
    fun testSeasonFilter()
    {
        ActivityScenario.launch(MainActivity::class.java)

        sleep(3000)
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())
        onView(withId(R.id.filter)).perform(click())
        sleep(1000)
        onView(withText("Season 1")).perform(click())

        sleep(1000)
        onView(withId(R.id.chars_list_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterListAdapter.ViewHolder>(6,
                    ViewActions.click()
                ))

        sleep(2000)
        onView(withId(R.id.charName)).check(matches(ViewMatchers.withSubstring("Hector Salamanca")))
        sleep(1000)
    }

    @Test
    fun testSearchFilter()
    {
        ActivityScenario.launch(MainActivity::class.java)
        sleep(3000)
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())

        onView(withId(R.id.searchBt)).perform(click())
        sleep(1000)
        onView(isAssignableFrom(SearchView::class.java)).perform(typeTextIntoFocusedView("gus"))
        sleep(1000)
        onView(withId(R.id.chars_list_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterListAdapter.ViewHolder>(0,
                    ViewActions.click()
                ))
        sleep(2000)
        onView(withId(R.id.charName)).check(matches(ViewMatchers.withSubstring("Gustavo")))
        sleep(1000)

        Espresso.pressBack()
        sleep(2000)

        onView(withId(R.id.searchBt)).perform(click())

        sleep(1000)
        onView(isAssignableFrom(SearchView::class.java)).perform(typeTextIntoFocusedView("aaaaa"))
        sleep(1000)
        onView(withId(R.id.noResultsView)).check(matches(isDisplayed()))
        sleep(1000)
    }


    private fun sleep(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}


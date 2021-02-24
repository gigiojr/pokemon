package com.pokemon.ui.component.searchbarcomponent

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.pokemon.R
import org.junit.Rule
import org.junit.Test

class SearchBarComponentTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(SearchBarComponentTestActivity::class.java)

    @Test
    fun startComponent() {
        onView(withId(R.id.searchField)).check(matches(isDisplayed()))
        onView(withId(R.id.btSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun showProgressBar() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.componentView.showLoad()
        }

        onView(withId(R.id.searchField)).check(matches(isDisplayed()))
        onView(withId(R.id.btSearch)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun hideProgressBar() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.componentView.showLoad()
            activity.componentView.hideLoad()
        }

        onView(withId(R.id.searchField)).check(matches(isDisplayed()))
        onView(withId(R.id.btSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun insertTextOnField() {
        onView(withId(R.id.searchField)).perform(ViewActions.typeText("Test"))
        onView(withId(R.id.btSearch)).perform(ViewActions.click())
        onView(withId(R.id.searchField)).check(matches(withText("Test")))
        onView(withId(R.id.btSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun clearField() {
        onView(withId(R.id.searchField)).perform(ViewActions.typeText("Test"))
        onView(withId(R.id.btSearch)).perform(ViewActions.click())

        activityScenarioRule.scenario.onActivity { activity ->
            activity.componentView.clearField()
        }

        onView(withId(R.id.searchField)).check(matches(withText("")))
        onView(withId(R.id.btSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }
}
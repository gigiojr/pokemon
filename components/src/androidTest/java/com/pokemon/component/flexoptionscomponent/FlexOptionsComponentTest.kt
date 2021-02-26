package com.pokemon.component.flexoptionscomponent

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FlexOptionsComponentTest {

    @get:Rule var activityScenarioRule = ActivityScenarioRule(FlexOptionsComponentTestActivity::class.java)

    @Test
    fun shouldShowItem() {
        val dataSet = listOf("Item")

        activityScenarioRule.scenario.onActivity { activity ->
            activity.componentView.items = dataSet
        }

        onView(withText("Item")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShow5Items() {
        val dataSet = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        activityScenarioRule.scenario.onActivity { activity ->
            activity.componentView.items = dataSet
        }

        onView(withText("Botão 1")).check(matches(isDisplayed()))
        onView(withText("Botão 2")).check(matches(isDisplayed()))
        onView(withText("Botão 3")).check(matches(isDisplayed()))
        onView(withText("Botão 4")).check(matches(isDisplayed()))
        onView(withText("Botão 5")).check(matches(isDisplayed()))
    }
}
package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
        @Rule
        public ActivityScenarioRule<MainActivity> scenarioRule= new ActivityScenarioRule<MainActivity>(MainActivity.class);

        @Test
        public void testAddCity() {
                // Click the add button
                onView(withId(R.id.button_add)).perform(click());

                // Type Edmonton
                onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));

                // Press confirm
                onView(withId(R.id.button_confirm)).perform(click());

                onView(withText("Edmonton")).check(matches(isDisplayed()));
        }

        @Test
        public void testClearCity() {
                onView(withId(R.id.button_add)).perform(click());
                onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());

                onView(withId(R.id.button_add)).perform(click());
                onView(withId(R.id.editText_name)).perform(typeText("Vancouver"));
                onView(withId(R.id.button_confirm)).perform(click());

                onView(withId(R.id.button_clear)).perform(click());
                onView(withText("Edmonton")).check(doesNotExist());
                onView(withText("Vancouver")).check(doesNotExist());
        }

        @Test
        public void testListView() {
                onView(withId(R.id.button_add)).perform(click());
                onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());
        }

        // Check that MainActivity is switched to ShowActivity
        @Test
        public void testActivitySwitch() {
                Intents.init();

                onView(withId(R.id.button_add)).perform(click());
                onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());

                onView(withText("Edmonton")).perform(click());
                // Verify ShowActivity is started
                intended(hasComponent(ShowActivity.class.getName()));

                Intents.release();
        }

        // Check whether the city name is consistent
        @Test
        public void testCityNameConsistency() {
                Intents.init();

                onView(withId(R.id.button_add)).perform(click());
                onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());

                onView(withText("Edmonton")).perform(click());
                intended(hasComponent(ShowActivity.class.getName()));           // Ensure it is the "Edmonton" test displayed in ShowActivity
                onView(withText("Edmonton")).check(matches(isDisplayed()));

                Intents.release();
        }

        // Test if the "Back" button returns to MainActivity
        @Test
        public void testBackButton() {
                Intents.init();

                onView(withId(R.id.button_add)).perform(click());
                onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());

                onView(withText("Edmonton")).perform(click());
                intended(hasComponent(ShowActivity.class.getName()));
                onView(withId(R.id.button_back)).perform(click());
                // Verify MainActivity is started
                intended(hasComponent(MainActivity.class.getName()));

                Intents.release();
        }
}

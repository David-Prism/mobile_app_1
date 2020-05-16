package com.example.helloworld;

import static org.junit.Assert.*;

import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import java.util.Date;
import org.junit.runner.RunWith;

import static androidx.test.espresso.contrib.PickerActions.setDate;

@RunWith(AndroidJUnit4.class)
public class ThankYouTest {

    @Rule
    public ActivityTestRule<ThankYou> activityTestRule
            = new ActivityTestRule<ThankYou>(ThankYou.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(Constants.KEY_NAME, "David");
            testIntent.putExtra(Constants.KEY_EMAIL, "david@gmail.com");
            testIntent.putExtra(Constants.KEY_USERNAME, "David1");
            testIntent.putExtra(Constants.KEY_DATE, "2020-04-17");
            onView(withId(R.id.text_view_4)).perform(setDate(1985, 6, 30));
            return testIntent;
        }
    };

    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.text_view_7))
                .check(matches(withText("David1")));
    }
}

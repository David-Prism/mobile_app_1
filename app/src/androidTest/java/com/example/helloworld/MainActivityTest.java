package com.example.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;

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
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    private static final String TAG = MainActivityTest.class.getSimpleName();

    @Test
    public void hasTextOnScreen() {
        onView(withId(R.id.text_view_1)).check(matches(withText(R.string.name)));
    }




//    @Test
//    public void retainsStateAfterRotate() {
//        // Change state of the button
//        onView(withId(R.id.text_view_4)).perform(setDate(2017, 6, 30));
//
//        mainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        mainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//        // Ensure change is still there
//        onView(withId(R.id.text_view_7)).check(matches(withText("2017/6/30")));
//    }
}

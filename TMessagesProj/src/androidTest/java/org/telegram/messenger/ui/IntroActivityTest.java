package org.telegram.messenger.ui;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.IntroActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;
import static org.telegram.messenger.ui.matchers.DrawableMatcher.withDrawable;
import static org.telegram.messenger.ui.matchers.TextMatcherDropHtml.withText;

/**
 * Created by user on 28.03.2016.
 */
@RunWith(AndroidJUnit4.class)
@MediumTest
public class IntroActivityTest {
    final int pagesCount = 7;

    @Rule
    public ActivityTestRule<IntroActivity> mActivityRule = new ActivityTestRule<>(
            IntroActivity.class);

    @Test
    public void has_views(){
        onView(withId(R.id.intro_view_pager))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.header_text), isDisplayed()))
                .check(matches(isDisplayed()));
        onView(withId(R.id.icon_image1))
                .check(matches(isDisplayed()));
        onView(withId(R.id.icon_image2))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.bottom_pages))
                .check(matches(isDisplayed()));
    }

    @Test
    public void initial_state(){
        onView(allOf(withId(R.id.icon_image1), isDisplayed()))
                .check(matches(withDrawable(LocaleController.isRTL ? R.drawable.intro7 : R.drawable.intro1)));
        onView(allOf(withId(R.id.header_text), isDisplayed()))
                .check(matches(withText(LocaleController.isRTL ? R.string.Page7Title : R.string.Page1Title)));
        onView(allOf(withId(R.id.message_text), isDisplayed()))
                .check(matches(withText(LocaleController.isRTL ? R.string.Page7Message : R.string.Page1Message)));
    }

    @Test
    public void swipe_all_pages(){
        for (int i = 0; i < pagesCount - 1; i++) {
            onView(withId(R.id.intro_view_pager))
                    .perform(LocaleController.isRTL ? swipeRight() : swipeLeft());
        }
    }

    @Test
    public void swipe_left_intro(){
        onView(withId(R.id.intro_view_pager))
                .perform(swipeLeft());
        onView(allOf(withId(R.id.header_text), isDisplayed()))
                .check(matches(withText(R.string.Page2Title)));
    }
}

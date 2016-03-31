/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2016.
 */

package org.telegram.ui;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Adapters.IntroAdapter;
import org.telegram.ui.Components.IntroInfoUnit;
import org.telegram.ui.Components.IntroPagerPresenter;
import org.telegram.ui.Components.IntroView;

public class IntroActivity extends Activity implements IntroView {

    private IntroPagerPresenter modelPresenter;
    private ViewPager viewPager;
    private ImageView topImage1;
    private ImageView topImage2;
    private ViewGroup bottomPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        modelPresenter = new IntroPagerPresenter(this);
        setTheme(R.style.Theme_TMessages);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (AndroidUtilities.isTablet()) {
            setContentView(R.layout.intro_layout_tablet);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.intro_layout);
        }

        viewPager = (ViewPager) findViewById(R.id.intro_view_pager);
        TextView startMessagingButton = (TextView) findViewById(R.id.start_messaging_button);
        startMessagingButton.setText(LocaleController.getString("StartMessaging", R.string.StartMessaging).toUpperCase());
        if (Build.VERSION.SDK_INT >= 21) {
            StateListAnimator animator = new StateListAnimator();
            animator.addState(new int[]{android.R.attr.state_pressed}, ObjectAnimator.ofFloat(startMessagingButton, "translationZ", AndroidUtilities.dp(2), AndroidUtilities.dp(4)).setDuration(200));
            animator.addState(new int[]{}, ObjectAnimator.ofFloat(startMessagingButton, "translationZ", AndroidUtilities.dp(4), AndroidUtilities.dp(2)).setDuration(200));
            startMessagingButton.setStateListAnimator(animator);
        }
        topImage1 = (ImageView) findViewById(R.id.icon_image1);
        topImage2 = (ImageView) findViewById(R.id.icon_image2);
        bottomPages = (ViewGroup) findViewById(R.id.bottom_pages);
        viewPager.setAdapter(new IntroAdapter(this));
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(modelPresenter);
        startMessagingButton.setOnClickListener(modelPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        modelPresenter.onResume();
        AndroidUtilities.checkForCrashes(this);
        AndroidUtilities.checkForUpdates(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AndroidUtilities.unregisterUpdates();
    }

    @Override
    public void setUnit(IntroInfoUnit unit) {
        final ImageView fadeoutImage;
        final ImageView fadeinImage;
        if (topImage1.getVisibility() == View.VISIBLE) {
            fadeoutImage = topImage1;
            fadeinImage = topImage2;

        } else {
            fadeoutImage = topImage2;
            fadeinImage = topImage1;
        }
        fadeinImage.bringToFront();
        fadeinImage.setImageResource(unit.getIconId());
        fadeinImage.clearAnimation();
        fadeoutImage.clearAnimation();

        Animation outAnimation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.icon_anim_fade_out);
        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeoutImage.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Animation inAnimation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.icon_anim_fade_in);
        inAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fadeinImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeoutImage.startAnimation(outAnimation);
        fadeinImage.startAnimation(inAnimation);
    }

    @Override
    public void setPos(int pos) {
        int count = bottomPages.getChildCount();
        for (int a = 0; a < count; a++) {
            View child = bottomPages.getChildAt(a);
            if (a == pos) {
                child.setBackgroundColor(0xff2ca5e0);
            } else {
                child.setBackgroundColor(0xffbbbbbb);
            }
        }
    }

    @Override
    public int getPagerActivePos() {
        return viewPager.getCurrentItem();
    }

    @Override
    public void start() {
        Intent intent2 = new Intent(IntroActivity.this, LaunchActivity.class);
        intent2.putExtra("fromIntro", true);
        startActivity(intent2);
        finish();
    }
}

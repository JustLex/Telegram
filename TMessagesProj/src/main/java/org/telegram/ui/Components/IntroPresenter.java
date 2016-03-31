package org.telegram.ui.Components;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by user on 31.03.2016.
 */
public interface IntroPresenter extends View.OnClickListener, ViewPager.OnPageChangeListener {
    void onResume();
}

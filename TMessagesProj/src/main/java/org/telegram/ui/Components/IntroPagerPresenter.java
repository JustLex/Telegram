package org.telegram.ui.Components;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by user on 31.03.2016.
 */
public class IntroPagerPresenter implements IntroPresenter  {
    private IntroInfoUnit activeInfo;
    private int activePos;
    private IntroInfo allInfo;
    private IntroView view;
    private boolean startPressed;

    public IntroPagerPresenter(IntroView view) {
        allInfo = new IntroInfoHardcode();
        this.view = view;
        activeInfo = allInfo.getFirst();
        activePos = allInfo.getFirstPos();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_SETTLING) {
            int pos = view.getPagerActivePos();
            if (pos != activePos)
            {
                activeInfo = allInfo.getForPos(pos);
                activePos = pos;
                view.setUnit(activeInfo);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (startPressed) {
            return;
        }
        startPressed = true;
        view.start();
    }

    @Override
    public void onResume() {
        view.setUnit(activeInfo);
        view.setPos(activePos);
    }
}

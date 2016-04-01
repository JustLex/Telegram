package org.telegram.ui.Components;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/**
 * Created by user on 31.03.2016.
 */
public class IntroInfoHardcode implements IntroInfo{
    private IntroInfoUnit[] introUnits = {
        new IntroInfoUnit(R.drawable.intro1, R.string.Page1Title, R.string.Page1Message),
        new IntroInfoUnit(R.drawable.intro2, R.string.Page2Title, R.string.Page2Message),
        new IntroInfoUnit(R.drawable.intro2, R.string.Page3Title, R.string.Page3Message),
        new IntroInfoUnit(R.drawable.intro4, R.string.Page4Title, R.string.Page4Message),
        new IntroInfoUnit(R.drawable.intro5, R.string.Page5Title, R.string.Page5Message),
        new IntroInfoUnit(R.drawable.intro6, R.string.Page6Title, R.string.Page6Message),
        new IntroInfoUnit(R.drawable.intro7, R.string.Page7Title, R.string.Page7Message)
    };
    private boolean isRTL = LocaleController.isRTL;

    @Override
    public IntroInfoUnit getFirst() {
        if (!isRTL)
            return introUnits[0];
        else
            return introUnits[introUnits.length - 1];
    }

    @Override
    public IntroInfoUnit getLast() {
        if (!isRTL)
            return introUnits[introUnits.length - 1];
        else
            return introUnits[0];
    }

    @Override
    public int getFirstPos() {
        return isRTL ? (introUnits.length - 1) : 0;
    }

    @Override
    public int getLastPos() {
        return isRTL ? 0 : introUnits.length - 1;
    }

    @Override
    public IntroInfoUnit getForPos(int pos) {
        if (pos >= introUnits.length)
            pos = introUnits.length - 1;
        if (pos < 0)
            pos = 0;
        return introUnits[pos];

    }

    @Override
    public int getSize() {
        return introUnits.length;
    }


}

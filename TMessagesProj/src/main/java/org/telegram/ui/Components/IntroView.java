package org.telegram.ui.Components;

/**
 * Created by user on 31.03.2016.
 */
public interface IntroView {
    void setUnit(IntroInfoUnit unit);
    void setPos(int pos);
    int getPagerActivePos();
    void start();
}

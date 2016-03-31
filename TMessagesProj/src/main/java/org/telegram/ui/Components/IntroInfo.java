package org.telegram.ui.Components;

/**
 * Created by user on 31.03.2016.
 */
public interface IntroInfo {
    IntroInfoUnit getFirst();
    IntroInfoUnit getLast();
    int getFirstPos();
    int getLastPos();
    IntroInfoUnit getForPos(int pos);
    int getSize();
}

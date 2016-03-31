package org.telegram.ui.Components;

/**
 * Created by user on 31.03.2016.
 */
public class IntroInfoUnit {
    private int iconId;
    private int headerId;
    private int textId;

    public IntroInfoUnit(int iconId, int headerId, int textId) {
        this.iconId = iconId;
        this.headerId = headerId;
        this.textId = textId;
    }

    public int getIconId() {
        return iconId;
    }

    public int getHeaderId() {
        return headerId;
    }

    public int getTextId() {
        return textId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntroInfoUnit that = (IntroInfoUnit) o;

        if (iconId != that.iconId) return false;
        if (headerId != that.headerId) return false;
        return textId == that.textId;

    }

    @Override
    public int hashCode() {
        int result = iconId;
        result = 31 * result + headerId;
        result = 31 * result + textId;
        return result;
    }
}

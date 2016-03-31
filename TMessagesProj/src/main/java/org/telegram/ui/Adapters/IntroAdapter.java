package org.telegram.ui.Adapters;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.IntroInfo;
import org.telegram.ui.Components.IntroInfoHardcode;
import org.telegram.ui.Components.IntroInfoUnit;
import org.telegram.ui.Components.IntroView;

/**
 * Created by user on 31.03.2016.
 */
public class IntroAdapter extends PagerAdapter {
    IntroInfo info;
    IntroView view;

    public IntroAdapter(IntroView view) {
        this.info = new IntroInfoHardcode();
        this.view = view;
    }

    @Override
    public int getCount() {
        return info.getSize();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.intro_view_layout, null);
        TextView headerText = (TextView) view.findViewById(R.id.header_text);
        TextView messageText = (TextView) view.findViewById(R.id.message_text);
        container.addView(view, 0);
        IntroInfoUnit unit = info.getForPos(position);
        headerText.setText(view.getResources().getString(unit.getHeaderId()));
        messageText.setText(AndroidUtilities.replaceTags(view.getResources().getString(unit.getTextId())));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        view.setPos(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }
}

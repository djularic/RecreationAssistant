package com.jularic.dominik.recreationassistant.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.jularic.dominik.recreationassistant.extras.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dominik on 6.1.2017..
 */

public class MyEventsRecyclerView extends RecyclerView {

    private List<View> mNonEmptyViews = Collections.emptyList();
    private List<View> mEmptyViews = Collections.emptyList();

    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }
    };

    private void toggleViews() {
        if (getAdapter() != null && !mEmptyViews.isEmpty() && !mNonEmptyViews.isEmpty()) {
            if (getAdapter().getItemCount() == 0) {

                //show all the empty views
                Util.showViews(mEmptyViews);

                //hide the RecyclerView
                setVisibility(View.GONE);

                //hide all the views which are meant to be hidden
                Util.hideViews(mNonEmptyViews);
            } else {

                //hide all the empty views
                Util.showViews(mNonEmptyViews);

                //show the RecyclerView
                setVisibility(View.VISIBLE);

                //hide all the views which are meant to be hidden
                Util.hideViews(mEmptyViews);
            }
        }
    }

    public MyEventsRecyclerView(Context context) {
        super(context);
    }

    public MyEventsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEventsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public void hideIfEmpty(View... views) {
        mNonEmptyViews = Arrays.asList(views);
    }

    public void showIfEmpty(View... emptyViews) {
        mEmptyViews = Arrays.asList(emptyViews);
    }
};

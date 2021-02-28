package com.abhijith.videoplaybackonrv.listeners;

import android.view.View;

/**
 * A convenience class used for the encapsulation and handling of the {@link OnItemLongClickListener}.
 *
 * @param <T> the item type
 * @author arthur3486
 */
public class ItemLongClickListener<T> implements View.OnLongClickListener {

    private int mPosition;
    private T mItem;

    private OnItemLongClickListener<T> mOnItemLongClickListener;

    public ItemLongClickListener(
            T item,
            int position,
            OnItemLongClickListener<T> onItemLongClickListener
    ) {
        mItem = item;
        mPosition = position;
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        return ((mOnItemLongClickListener != null) && mOnItemLongClickListener.onItemLongClicked(v, mItem, mPosition));
    }
}

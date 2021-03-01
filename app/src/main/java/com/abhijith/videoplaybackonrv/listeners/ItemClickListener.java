package com.abhijith.videoplaybackonrv.listeners;

import android.view.View;

/**
 * A convenience class used for the encapsulation and handling of the {@link OnItemClickListener}.
 *
 * @param <T> the item type
 */
public class ItemClickListener<T> implements View.OnClickListener {


    private int mPosition;
    private T mItem;

    private OnItemClickListener<T> mOnItemClickListener;




    public ItemClickListener(T item,
                             int position,
                             OnItemClickListener<T> onItemClickListener) {
        mItem = item;
        mPosition = position;
        mOnItemClickListener = onItemClickListener;
    }




    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null) {
            mOnItemClickListener.onItemClicked(v, mItem, mPosition);
        }
    }




}

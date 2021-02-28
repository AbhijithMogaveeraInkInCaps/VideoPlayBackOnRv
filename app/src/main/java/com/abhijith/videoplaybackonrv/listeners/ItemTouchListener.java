package com.abhijith.videoplaybackonrv.listeners;

import android.view.MotionEvent;
import android.view.View;

/**
 * A convenience class used for the encapsulation and handling of the {@link OnItemTouchListener}
 *
 * @param <T> the item type
 * @author arthur3486
 */
public class ItemTouchListener<T> implements View.OnTouchListener {


    private int mPosition;
    private T mItem;

    private OnItemTouchListener<T> mOnItemTouchListener;




    public ItemTouchListener(T item,
                             int position,
                             OnItemTouchListener<T> onItemTouchListener) {
        mItem = item;
        mPosition = position;
        mOnItemTouchListener = onItemTouchListener;
    }




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return ((mOnItemTouchListener != null) && mOnItemTouchListener.onItemTouch(view, motionEvent, mItem, mPosition));
    }




}

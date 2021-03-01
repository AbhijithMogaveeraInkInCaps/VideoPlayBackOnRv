package com.abhijith.videoplaybackonrv.listeners;

import android.view.MotionEvent;
import android.view.View;

/**
 * A contract-interface for the observation of the dataset item touch events.
 *
 * @param <T> the item type
 */
public interface OnItemTouchListener<T> {

    /**
     * Called when the dataset item is touched.
     *
     * @param view the origin of the click event
     * @param motionEvent the generated motion event
     * @param item the associated item
     * @param position the associated position info
     * @return <strong>true</strong> to continue the consumption of the touch events, <strong>false</strong> otherwise.
     */
    boolean onItemTouch(View view, MotionEvent motionEvent, T item, int position);

}

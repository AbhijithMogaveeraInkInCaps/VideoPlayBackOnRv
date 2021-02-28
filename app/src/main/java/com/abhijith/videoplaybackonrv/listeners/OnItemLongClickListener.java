package com.abhijith.videoplaybackonrv.listeners;

import android.view.View;

/**
 * A contract-interface for the observation of the dataset item long click events.
 *
 * @param <T> the item type
 * @author arthur3486
 */
public interface OnItemLongClickListener<T> {

    /**
     * Called when the dataset item is long clicked.
     *
     * @param view the origin of the long click event
     * @param item the associated item
     * @param position the associated position info
     * @return <strong>true</strong> if the long click has been confirmed, <strong>false</strong> otherwise.
     */
    boolean onItemLongClicked(View view, T item, int position);

}

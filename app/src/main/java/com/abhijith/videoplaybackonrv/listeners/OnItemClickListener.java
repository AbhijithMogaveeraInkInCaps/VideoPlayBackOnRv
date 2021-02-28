package com.abhijith.videoplaybackonrv.listeners;

import android.view.View;

/**
 * A contract-interface for the observation of the dataset item click events.
 *
 * @param <T> the item type
 * @author arthur3486
 */
public interface OnItemClickListener<T> {

    /**
     * Called when the dataset item is clicked
     *
     * @param view the origin of the click event
     * @param item the associated item
     * @param position the associated position info
     */
    void onItemClicked(View view, T item, int position);

}

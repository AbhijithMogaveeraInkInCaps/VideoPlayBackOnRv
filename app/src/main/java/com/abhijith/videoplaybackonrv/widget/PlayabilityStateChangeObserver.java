package com.abhijith.videoplaybackonrv.widget;

/**
 * A playability state change observer used by the {@link Playable} items held by the {@link PlayableItemsContainer}.
 */
public interface PlayabilityStateChangeObserver {

    /**
     * Gets called when the item playability state changes.
     *
     * @param isPlayable whether the item is playable or not
     */
    void onPlayabilityStateChanged(boolean isPlayable);

}

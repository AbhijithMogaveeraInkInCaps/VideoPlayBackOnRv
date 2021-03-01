package com.abhijith.videoplaybackonrv.model.markers;

import androidx.annotation.NonNull;

/**
 * A marker interface that's to implemented by the Items that are considered captionable.
 *
 */
public interface Captionable {

    /**
     * Retrieves the captions from the corresponding item.
     *
     * @return the retrieved caption
     */
    @NonNull
    String getCaption();

}
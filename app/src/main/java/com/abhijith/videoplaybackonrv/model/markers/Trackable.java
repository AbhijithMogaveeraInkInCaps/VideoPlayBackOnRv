package com.abhijith.videoplaybackonrv.model.markers;

import androidx.annotation.NonNull;

/**
 * A marker class that's to be implemented by the Items that need to be made "unique" within the Trackable Adapters.
 *
 * @param <KT> key type (e.g. {@link Integer}, {@link String}, etc.)
 */
public interface Trackable<KT> {

    /**
     * Retrieves the track key associated with this {@link Trackable}.
     *
     * @return the track key
     */
    @NonNull
    KT getTrackKey();

}
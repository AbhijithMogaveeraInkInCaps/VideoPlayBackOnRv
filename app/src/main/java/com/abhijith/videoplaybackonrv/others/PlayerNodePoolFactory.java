package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;

/**
 * Defines a base contract for the concrete {@link com.abhijith.videoplaybackonrv.others.PlayerNodePoolFactory} implementations.
 */
public interface PlayerNodePoolFactory {

    /**
     * Creates a new instance of the {@link PlayerNodePool}.
     *
     * @return the created {@link PlayerNodePool} instance
     */
    @NonNull
    PlayerNodePool create();

}
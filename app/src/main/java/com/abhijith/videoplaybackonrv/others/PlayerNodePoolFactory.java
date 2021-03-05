package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;

/**
 * Defines a base contract for the concrete {@link com.abhijith.videoplaybackonrv.others.PlayerNodePoolFactory} implementations.
 */
public interface PlayerNodePoolFactory {

    /**
     * Creates a new instance of the {@link com.abhijith.videoplaybackonrv.others.PlayerNodePool}.
     *
     * @return the created {@link com.abhijith.videoplaybackonrv.others.PlayerNodePool} instance
     */
    @NonNull
    com.abhijith.videoplaybackonrv.others.PlayerNodePool create();

}
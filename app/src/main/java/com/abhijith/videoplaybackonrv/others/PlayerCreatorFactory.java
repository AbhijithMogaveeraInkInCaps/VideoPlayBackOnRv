package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.player.creators.PlayerCreator;

/**
 * Defines a base contract for the concrete {@link PlayerCreatorFactory} implementations.
 */
public interface PlayerCreatorFactory {

    /**
     * Creates a new instance of the {@link PlayerCreator} based on the specified parameters.
     *
     * @param playerProvider the player provider
     * @param config the desired player configuration
     * @return the created {@link PlayerCreator} instance
     */
    @NonNull
    PlayerCreator create(@NonNull PlayerProvider playerProvider, @NonNull Config config);



}
package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.player.creators.DefaultPlayerCreator;
import com.abhijith.videoplaybackonrv.player.creators.PlayerCreator;
import com.abhijith.videoplaybackonrv.util.misc.Preconditions;

/**
 * A default implementation of the {@link PlayerCreatorFactory}.
 */
public final class DefaultPlayerCreatorFactory implements PlayerCreatorFactory {


    @NonNull
    @Override
    public final PlayerCreator create(@NonNull com.abhijith.videoplaybackonrv.others.PlayerProvider playerProvider, @NonNull com.abhijith.videoplaybackonrv.others.Config config) {
        Preconditions.checkNonNull(playerProvider);
        Preconditions.checkNonNull(config);

        return new DefaultPlayerCreator(playerProvider, config);
    }

}
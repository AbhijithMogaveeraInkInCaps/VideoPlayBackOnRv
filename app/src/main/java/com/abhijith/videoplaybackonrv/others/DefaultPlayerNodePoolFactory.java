package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;

/**
 * A default implementation of the {@link PlayerNodePoolFactory}.
 */
public final class DefaultPlayerNodePoolFactory implements PlayerNodePoolFactory {


    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors();


    @NonNull
    @Override
    public final PlayerNodePool create() {
        return new ArviPlayerNodePool(MAX_POOL_SIZE);
    }


}
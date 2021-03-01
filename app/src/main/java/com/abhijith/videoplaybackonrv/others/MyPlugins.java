package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;

/**
 * Utility class used for the injection of certain {@link PlayerProvider} components.
 */
public final class MyPlugins {

    private static volatile PlayerCreatorFactory playerCreatorFactory = new DefaultPlayerCreatorFactory();
    private static volatile PlayerNodePoolFactory playerNodePoolFactory = new DefaultPlayerNodePoolFactory();

    private static volatile boolean isLockedDown = false;


    /**
     * "Locks down" the plugin injection.
     * All the plugin injections performed after the lock-down will fail with an {@link IllegalStateException}.
     */
    static void lockDown() {
        isLockedDown = true;
    }


    /**
     * Retrieves the current lock-down state.
     *
     * @return whether the plugin injection is locked down or not
     */
    public static boolean isLockedDown() {
        return isLockedDown;
    }


    /**
     * Injects the {@link PlayerCreatorFactory} to be used by the {@link PlayerProvider}.
     *
     * @param factory the new player creator factory
     */
    public static void setPalayerCreatorFactory(@NonNull PlayerCreatorFactory factory) {
        checkLockDownState();

        playerCreatorFactory = factory;
    }


    /**
     * Retrieves the currently injected {@link PlayerCreatorFactory}.
     *
     * @return the current {@link PlayerCreatorFactory}
     */
    @NonNull
    public static PlayerCreatorFactory getPlayerCreatorFactory() {
        return playerCreatorFactory;
    }


    /**
     * Injects the {@link PlayerNodePoolFactory} to be used by the {@link PlayerProvider}.
     *
     * @param factory the new player node pool factory
     */
    public static void setPlayerNodePoolFactory(@NonNull PlayerNodePoolFactory factory) {
        checkLockDownState();

        playerNodePoolFactory = factory;
    }


    /**
     * Retrieves the currently injected {@link PlayerNodePoolFactory}.
     *
     * @return the current {@link PlayerNodePoolFactory}
     */
    @NonNull
    public static PlayerNodePoolFactory getPlayerNodePoolFactory() {
        return playerNodePoolFactory;
    }


    private static void checkLockDownState() {
        if(isLockedDown) {
            throw new IllegalStateException("The Plugins can not be changed anymore.");
        }
    }


    private MyPlugins() {
        throw new IllegalStateException("Not instantiatable!");
    }


}
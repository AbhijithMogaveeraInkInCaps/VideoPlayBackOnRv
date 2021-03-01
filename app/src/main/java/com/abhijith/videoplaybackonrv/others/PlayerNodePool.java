package com.abhijith.videoplaybackonrv.others;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abhijith.videoplaybackonrv.player.Player;

/**
 * A base contract for the concrete implementations of the {@link com.abhijith.videoplaybackonrv.others.PlayerNodePool}.
 */
interface PlayerNodePool {

    /**
     * Adds the specified {@link com.abhijith.videoplaybackonrv.others.PlayerNode} to the current player node pool.
     *
     * @param player the player node to be added to the pool
     */
    void add(@NonNull com.abhijith.videoplaybackonrv.others.PlayerNode player);

    /**
     * Creates the {@link com.abhijith.videoplaybackonrv.others.PlayerNode} from the specified key and {@link Player},
     * and adds it to the current player node pool.
     *
     * @param key the key to map the player to
     * @param player the player to be added to the pool
     */
    void add(@NonNull String key, @NonNull Player player);

    /** 
     * Removes the specified {@link com.abhijith.videoplaybackonrv.others.PlayerNode} from the current player node pool.
     *
     * @param playerNode the node to be removed from the pool
     * @return the removed player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode remove(@NonNull com.abhijith.videoplaybackonrv.others.PlayerNode playerNode);

    /**
     * Removes the {@link com.abhijith.videoplaybackonrv.others.PlayerNode} for the specified key, if there's any.
     *
     * @param key the key to remove the player node for
     * @return the removed Player Node, or <strong>null</strong> if there was no Player Node for the specified key
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode remove(@NonNull String key);

    /**
     * Unregisters (makes available) the {@link com.abhijith.videoplaybackonrv.others.PlayerNode} mapped to the specified key.
     */
    void unregister(@NonNull String key);

    /**
     * Registers (acquires) the available {@link com.abhijith.videoplaybackonrv.others.PlayerNode} for the specified key.
     *
     * @param key the key to register the player node for
     * @return the registered player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode acquire(@NonNull String key);

    /**
     * Registers (acquires) the available free {@link com.abhijith.videoplaybackonrv.others.PlayerNode} for the specified key, if there's any.
     *
     * @param key the key to register the player node for
     * @return the registered player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode acquireFree(@NonNull String key);

    /**
     * Registers (acquires) the oldest available {@link com.abhijith.videoplaybackonrv.others.PlayerNode} for the specified key.
     * If no available {@link com.abhijith.videoplaybackonrv.others.PlayerNode} was found, unregisters the oldest one and re-registers it (now for the new key).
     *
     * @param key the key to register the player node for
     * @return the registered player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode acquireOldest(@NonNull String key);

    /**
     * Releases the specified {@link com.abhijith.videoplaybackonrv.others.PlayerNode}.
     *
     * @param playerNode the player node to be released
     */
    void release(@NonNull com.abhijith.videoplaybackonrv.others.PlayerNode playerNode);

    /**
     * Releases the {@link com.abhijith.videoplaybackonrv.others.PlayerNode} that corresponds to the specified key.
     *
     * @param key the key for which the {@link com.abhijith.videoplaybackonrv.others.PlayerNode} to be released
     */
    void release(@NonNull String key);

    /**
     * Releases all the {@link com.abhijith.videoplaybackonrv.others.PlayerNode}s present within this pool.
     */
    void release();

    /**
     * Retrieves the {@link com.abhijith.videoplaybackonrv.others.PlayerNode} for the specified key.
     *
     * @param key the key to retrieve the player node for
     * @return the retrieved player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode get(@NonNull String key);

    /**
     * Retrieves the free {@link com.abhijith.videoplaybackonrv.others.PlayerNode} present in the current pool.
     *
     * @return the retrieved free player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode getFree();

    /**
     * Retrieves the oldest {@link com.abhijith.videoplaybackonrv.others.PlayerNode} in the current pool.
     *
     * @return the retrieved oldest player node
     */
    @Nullable
    com.abhijith.videoplaybackonrv.others.PlayerNode getOldest();

    /**
     * Retrieves the number of the {@link com.abhijith.videoplaybackonrv.others.PlayerNode}s held by the current pool.
     *
     * @return the number of the player nodes held by the current pool
     */
    int getPlayerCount();

    /**
     * Determines whether the current pool is full (whether the number of the {@link com.abhijith.videoplaybackonrv.others.PlayerNode}s held
     * by the current pool reached the player node limit imposed by the current pool).
     *
     * @return <strong>true</strong> if it's full, <strong>false</strong> otherwise
     */
    boolean isFull();

    /**
     * Determines whether the current pool contains a {@link com.abhijith.videoplaybackonrv.others.PlayerNode} that corresponds to the specified key.
     *
     * @param key the key to check the player node presence for
     * @return <strong>true</strong> if the pool contains the corresponding player node, <strong>false</strong> otherwise
     */
    boolean contains(@NonNull String key);

}

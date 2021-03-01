package com.abhijith.videoplaybackonrv.util.cache;

import androidx.annotation.RestrictTo;

/**
 * The {@link Cache} factory.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public enum CacheType {


    /**
     * An in-memory (RAM) implementation of {@link Cache}.
     */
    IN_MEMORY {
        @Override
        <K, V> Cache<K, V> create(boolean concurrent) {
            final Cache<K, V> cache = new InMemoryCache<>();
            return (concurrent ? new ConcurrentCache<>(cache) : cache);
        }
    };


    /**
     * Creates a new instance of the {@link Cache}.
     *
     * @param concurrent whether to create a synchronized (thread-safe) version of {@link Cache}, or not
     * @param <K> cache key type
     * @param <V> cache value type
     * @return the created {@link Cache}
     */
    abstract <K, V> Cache<K, V> create(boolean concurrent);


}

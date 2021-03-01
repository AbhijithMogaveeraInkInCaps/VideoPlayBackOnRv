package com.abhijith.videoplaybackonrv.util.cache;

import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.util.misc.Preconditions;

/**
 * A synchronized (thread-safe) implementation of the {@link Cache}.
 * Used as a {@link Cache} wrapper, to provide the thread safety for any
 * {@link Cache} implementation.
 *
 * @param <K> the cache key type
 * @param <V> the cache value type
 */
final class ConcurrentCache<K, V> implements Cache<K, V> {


    private final Object mLock;

    private final Cache<K, V> mCache;




    ConcurrentCache(@NonNull Cache<K, V> cache) {
        mLock = new Object();
        mCache = Preconditions.checkNonNull(cache);
    }




    @Override
    public final V put(K key, V value) {
        synchronized(mLock) {
            return mCache.put(key, value);
        }
    }




    @Override
    public final V get(K key) {
        synchronized(mLock) {
            return mCache.get(key);
        }
    }




    @Override
    public final V get(K key, V defaultValue) {
        synchronized(mLock) {
            return mCache.get(key, defaultValue);
        }
    }




    @Override
    public final <RV> RV getAs(K key) {
        synchronized(mLock) {
            return mCache.getAs(key);
        }
    }




    @Override
    public final <RV> RV getAs(K key, RV defaultValue) {
        synchronized(mLock) {
            return mCache.getAs(key, defaultValue);
        }
    }




    @Override
    public final V remove(K key) {
        synchronized(mLock) {
            return mCache.remove(key);
        }
    }




    @Override
    public final V remove(K key, V defaultValue) {
        synchronized(mLock) {
            return mCache.remove(key, defaultValue);
        }
    }




    @Override
    public final <RV> RV removeAs(K key) {
        synchronized(mLock) {
            return mCache.removeAs(key);
        }
    }




    @Override
    public final <RV> RV removeAs(K key, RV defaultValue) {
        synchronized(mLock) {
            return mCache.removeAs(key, defaultValue);
        }
    }




    @Override
    public final boolean contains(K key) {
        synchronized(mLock) {
            return mCache.contains(key);
        }
    }




    @Override
    public final boolean clear() {
        synchronized(mLock) {
            return mCache.clear();
        }
    }




}

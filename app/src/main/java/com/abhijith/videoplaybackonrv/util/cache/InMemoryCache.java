package com.abhijith.videoplaybackonrv.util.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the in-memory (RAM) {@link Cache}.
 *
 * @param <K> the cache key type
 * @param <V> the cache value type
 */
final class InMemoryCache<K, V> implements Cache<K, V> {


    private final Map<K, V> mCacheMap;




    InMemoryCache() {
        mCacheMap = new HashMap<>();
    }




    @Override
    public final V put(K key, V value) {
        return mCacheMap.put(key, value);
    }




    @Override
    public final V get(K key) {
        return get(key, null);
    }




    @Override
    public final V get(K key, V defaultValue) {
        return (contains(key) ? mCacheMap.get(key) : defaultValue);
    }




    @Override
    public final <RV> RV getAs(K key) {
        return getAs(key, null);
    }




    @SuppressWarnings("unchecked")
    @Override
    public final <RV> RV getAs(K key, RV defaultValue) {
        return (RV) (contains(key) ? mCacheMap.get(key) : defaultValue);
    }




    @Override
    public final V remove(K key) {
        return remove(key, null);
    }




    @Override
    public final V remove(K key, V defaultValue) {
        return (contains(key) ? mCacheMap.remove(key) : defaultValue);
    }




    @Override
    public final <RV> RV removeAs(K key) {
        return removeAs(key, null);
    }




    @SuppressWarnings("unchecked")
    @Override
    public final <RV> RV removeAs(K key, RV defaultValue) {
        return (RV) (contains(key) ? mCacheMap.remove(key) : defaultValue);
    }




    @Override
    public final boolean contains(K key) {
        return (mCacheMap.get(key) != null);
    }




    @Override
    public final boolean clear() {
        mCacheMap.clear();
        return true;
    }




}

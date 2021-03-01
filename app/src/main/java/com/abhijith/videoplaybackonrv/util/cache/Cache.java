package com.abhijith.videoplaybackonrv.util.cache;

/**
 * A base contract to be implemented by the concrete {@link Cache} implementations.
 *
 * @param <K> cache entry key type
 * @param <V> cache entry type
 */
public interface Cache<K, V> {

    /**
     * Puts the value {@link V} into the cache and associates the key {@link K} with it.
     *
     * @param key the key to map the value to
     * @param value the value to be put into cache
     * @return the put value
     */
    V put(K key, V value);

    /**
     * Retrieves the value {@link V} associated with the specified key {@link K}.
     *
     * @param key the key to retrieve the value for
     * @return the corresponding value if it's present, or <strong>null</strong> otherwise
     */
    V get(K key);

    /**
     * Retrieves the value {@link V} associated with the specified key {@link K}.
     * In cases when the value {@link V} is not present within the cache, the specified default
     * value is used instead.
     *
     * @param key the key to retrieve the value for
     * @param defaultValue the default value
     * @return the corresponding value if it's present, or the default one otherwise
     */
    V get(K key, V defaultValue);

    /**
     * Retrieves the value that corresponds to the specified key {@link K} and automatically
     * casts it to the desired value type, based on the type inference.
     *
     * @param key the key to retrieve the value for
     * @param <RV> the type to cast the retrieved value to
     * @return the corresponding value if it's present, or <strong>null</strong> otherwise
     */
    <RV> RV getAs(K key);

    /**
     * Retrieves the value that corresponds to the specified key {@link K} and automatically
     * casts it to the desired value type, based on the type inference.
     * In cases when the value is not present within the cache, the specified default
     * value is used instead.
     *
     * @param key the key to retrieve the value for
     * @param defaultValue the default value
     * @param <RV> the type to cast the retrieved value to
     * @return the corresponding value if it's present, or the default one otherwise
     */
    <RV> RV getAs(K key, RV defaultValue);

    /**
     * Removes the value {@link V} that corresponds to the specified key {@link K} from the cache.
     *
     * @param key the key to remove the value for
     * @return the corresponding removed value if it's present, or <strong>null</strong> otherwise
     */
    V remove(K key);

    /**
     * Removes the value {@link V} that corresponds to the specified key {@link K} from the cache.
     * In cases when the value {@link V} is not present within the cache, the specified default
     * value is used instead.
     *
     * @param key the key to remove the value for
     * @param defaultValue the default value
     * @return the corresponding removed value if it's present, or the default one otherwise
     */
    V remove(K key, V defaultValue);

    /**
     * Removes the value that corresponds to the specified key {@link K} from the cache, and automatically
     * casts it [the removed value] to the desired value type, based on the type inference.
     *
     * @param key the key to remove the value for
     * @param <RV> the type to cast the removed value to
     * @return the corresponding removed value if it's present, or <strong>null</strong> otherwise
     */
    <RV> RV removeAs(K key);

    /**
     * Removes the value that corresponds to the specified key {@link K} from the cache, and automatically
     * casts it [the removed value] to the desired value type, based on the type inference.
     *
     * @param key the key to remove the value for
     * @param defaultValue the default value
     * @param <RV> the type to cast the removed value to
     * @return the corresponding removed value if it's present, or the default one otherwise
     */
    <RV> RV removeAs(K key, RV defaultValue);

    /**
     * Checks whether the current {@link Cache} instance contains the value {@link V}
     * that corresponds to the specified key {@link K}.
     *
     * @param key the key to check the value presence for
     * @return <strong>true</strong> if the corresponding value is present, <strong>false</strong> otherwise
     */
    boolean contains(K key);

    /**
     * Removes all the entries from the current {@link Cache} instance.
     *
     * @return <strong>true</strong> if the clearing completed successfully, <strong>false</strong> otherwise
     */
    boolean clear();

}

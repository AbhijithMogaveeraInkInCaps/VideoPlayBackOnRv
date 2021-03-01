package com.abhijith.videoplaybackonrv.util.cache;

import androidx.annotation.RestrictTo;

import com.abhijith.videoplaybackonrv.model.PlaybackInfo;

/**
 * An implementation of {@link Cache} used for the management of the {@link com.abhijith.videoplaybackonrv.player.Player}'s {@link PlaybackInfo}.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class PlaybackInfoCache implements Cache<String, PlaybackInfo> {


    private static volatile PlaybackInfoCache sInstance;

    private final Cache<String, PlaybackInfo> mCache;




    /**
     * Lazily creates an instance of the {@link PlaybackInfoCache} (if necessary).
     *
     * @return the instance of the {@link PlaybackInfoCache}
     */
    public static PlaybackInfoCache getInstance() {
        if(sInstance == null) {
            synchronized(PlaybackInfoCache.class) {
                if(sInstance == null) {
                    sInstance = new PlaybackInfoCache();
                }
            }
        }

        return sInstance;
    }




    private PlaybackInfoCache() {
        mCache = CacheType.IN_MEMORY.create(true);
    }




    @Override
    public final PlaybackInfo put(String key, PlaybackInfo value) {
        return mCache.put(key, value);
    }




    @Override
    public final PlaybackInfo get(String key) {
        return mCache.get(key);
    }




    @Override
    public final PlaybackInfo get(String key, PlaybackInfo defaultValue) {
        return mCache.get(key, defaultValue);
    }




    @Override
    public final <RV> RV getAs(String key) {
        return mCache.getAs(key);
    }




    @Override
    public final <RV> RV getAs(String key, RV defaultValue) {
        return mCache.getAs(key, defaultValue);
    }




    @Override
    public final PlaybackInfo remove(String key) {
        return mCache.remove(key);
    }




    @Override
    public final PlaybackInfo remove(String key, PlaybackInfo defaultValue) {
        return mCache.remove(key, defaultValue);
    }




    @Override
    public final <RV> RV removeAs(String key) {
        return mCache.removeAs(key);
    }




    @Override
    public final <RV> RV removeAs(String key, RV defaultValue) {
        return mCache.removeAs(key, defaultValue);
    }




    @Override
    public final boolean contains(String key) {
        return mCache.contains(key);
    }




    @Override
    public final boolean clear() {
        return mCache.clear();
    }




}

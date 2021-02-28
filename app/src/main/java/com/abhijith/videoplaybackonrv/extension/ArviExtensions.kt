package com.abhijith.videoplaybackonrv.extension

import android.content.Context
import com.abhijith.videoplaybackonrv.others.PlayerProvider
import com.abhijith.videoplaybackonrv.others.PlayerProviderImpl
import com.abhijith.videoplaybackonrv.util.misc.ExoPlayerUtils
import com.google.android.exoplayer2.upstream.cache.Cache


/**
 * Obtains the global [PlayerProvider] instance.
 */
val Context.playerProvider : PlayerProvider
    get() = PlayerProviderImpl.getInstance(this)


/**
 * Creates/obtains the Exo Player [Cache] of the default size [ExoPlayerUtils.DEFAULT_CACHE_SIZE].
 *
 * @return the created/obtains [Cache]
 */
fun Context.defaultExoCache() : Cache {
    return ExoPlayerUtils.getCache(this)
}


/**
 * Creates/obtains the Exo Player [Cache] of the specified size (in bytes).
 *
 * @return the created/obtains [Cache]
 */
fun Context.exoCache(size : Long) : Cache {
    return ExoPlayerUtils.getCache(this, size)
}
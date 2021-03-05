package com.abhijith.videoplaybackonrv.others

import android.content.Context
import android.net.Uri
import com.abhijith.videoplaybackonrv.player.Player
import com.google.android.exoplayer2.source.MediaSource

/**
 * Defines a base contract for the concrete [PlayerProvider] implementations.
 */
interface PlayerProvider {
    /**
     * Creates the a non-looping [MediaSource]. (See [.createMediaSource])
     *
     * @param uri the uri to create the media source for
     * @return the created media source
     */
    fun createMediaSource(uri: Uri): MediaSource

    /**
     * Creates the [MediaSource] for the specified [Uri].
     * The created [MediaSource] can later be used with the [Player],
     * as a data source.
     * Uses the default [Player] [Config].
     *
     * @param uri the uri to create the media source for
     * @param isLooping whether to loop the video
     * @return the created media source
     */
    fun createMediaSource(uri: Uri, isLooping: Boolean): MediaSource

    /**
     * Creates the a non-looping [MediaSource]. (See [.createMediaSource])
     *
     * @param config the player configuration
     * @param uri the uri to created the media source for
     * @return the created media source
     */
    fun createMediaSource(config: Config, uri: Uri): MediaSource

    /**
     * Creates the [MediaSource] for the specified [Uri] and Player[Config]
     * The created [MediaSource] can later be used with the [Player],
     * as a data source.
     *
     * @param config the player configuration
     * @param uri the uri to created the media source for
     * @param isLooping whether to loop the video
     * @return the created media source
     */
    fun createMediaSource(config: Config, uri: Uri, isLooping: Boolean): MediaSource

    /**
     * Retrieves the library name.
     *
     * @return the library name
     */
    val libraryName: String

    /**
     * Retrieves the application [Context].
     *
     * @return the application context
     */
    val context: Context

    /**
     * Retrieves an existing [Player] instance for the specified key, if there's any.
     * Uses the default [Config].
     *
     * @param key the key to retrieve the player for
     * @return the retrieved Player, or **null** if no Player was found.
     */
    fun getPlayer(key: String): Player?

    /**
     * Retrieves an existing [Player] instance for the specified key and Player [Config], if there's any.
     *
     * @param config the player configuration
     * @param key the key to retrieve the player for
     * @return the retrieved Player, or **null** if no Player was found.
     */
    fun getPlayer(config: Config, key: String): Player?

    /**
     * Retrieves an existing or create a brand-new [Player] instance for the specified key.
     * Users the default player [Config].
     *
     * @param key the key to retrieve the player for
     * @return the retrieved or created Player
     */
    fun getOrInitPlayer(key: String): Player

    /**
     * Retrieves an existing or create a brand-new [Player] instance for the specified key and Player [Config].
     *
     * @param config the player configuration
     * @param key the key to retrieve the player for
     * @return the retrieved or created Player
     */
    fun getOrInitPlayer(config: Config, key: String): Player

    /**
     * Checks if there's a [Player] available for the specified key.
     * Uses the default Player [Config].
     *
     * @param key the key to check the player presence for
     * @return **true** if the player is available, **false** otherwise
     */
    fun hasPlayer(key: String): Boolean

    /**
     * Checks if there's a [Player] available for the specified key and Player [Config].
     *
     * @param config the player configuration
     * @param key the key to check the player presence for
     * @return **true** if the player is available, **false** otherwise
     */
    fun hasPlayer(config: Config, key: String): Boolean

    /**
     * Unregisters the [Player], thus making it available within the Player Pool as a "free" Player.
     * Uses the default Player [Config].
     *
     * @param key the key to unregister the Player for
     */
    fun unregister(key: String)

    /**
     * Unregisters the [Player], thus making it available within the Player Pool as a "free" Player.
     *
     * @param config the player configuration
     * @param key the key to unregister the Player for
     */
    fun unregister(config: Config, key: String)

    /**
     * Releases the [Player] for the specified key.
     * Uses the default Player [Config].
     *
     * @param key the key to release the Player for
     */
    fun release(key: String)

    /**
     * Releases all the [Player]s that match the specified [Config].
     *
     * @param config the player configuration
     */
    fun release(config: Config)

    /**
     * Releases the [Player] for the specified key and [Config].
     *
     * @param config the player configuration
     * @param key the key to release the Player for
     */
    fun release(config: Config, key: String)

    /**
     * Releases all the currently available (initialized) [Player]s.
     */
    fun release()
}
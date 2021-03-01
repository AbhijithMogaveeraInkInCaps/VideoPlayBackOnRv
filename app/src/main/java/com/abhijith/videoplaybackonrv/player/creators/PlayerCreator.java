package com.abhijith.videoplaybackonrv.player.creators;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.player.Player;
import com.google.android.exoplayer2.source.MediaSource;

/**
 * A base contract to be implemented by the concrete implementations
 * of the {@link PlayerCreator}s.
 * Should be able to create the {@link Player}s and related {@link MediaSource}s.
 */
public interface PlayerCreator {

    /**
     * Creates a brand-new {@link Player} instance.
     *
     * @return the created {@link Player} instance
     */
    @NonNull
    Player createPlayer();

    /**
     * Creates a {@link MediaSource} for the specified {@link Uri}.
     *
     * @param uri the media uri
     * @return the created {@link MediaSource}
     */
    @NonNull
    MediaSource createMediaSource(@NonNull Uri uri);

    /**
     * Creates a {@link MediaSource} for the specified {@link Uri} and extension.
     *
     * @param uri the media uri
     * @param extension the media extension
     * @return the created {@link MediaSource}
     */
    @NonNull
    MediaSource createMediaSource(@NonNull Uri uri, @NonNull String extension);

}

package com.abhijith.videoplaybackonrv.player.util;

import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.player.Player;
import com.abhijith.videoplaybackonrv.util.misc.Preconditions;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

import java.util.HashSet;
import java.util.Set;

/**
 * A manager for the {@link Player.EventListener}s. Used to observe the {@link EventListener} events
 * and propagate them to all the subscribed {@link Player.EventListener}s.
 */
public final class PlayerEventListenerRegistry implements EventListener {

    private final Set<Player.EventListener> mEventListeners;

    public PlayerEventListenerRegistry() {
        mEventListeners = new HashSet<>();
    }

    public final void addListener(@NonNull Player.EventListener eventListener) {
        Preconditions.nonNull(eventListener);
        mEventListeners.add(eventListener);
    }

    public final void removeListener(@NonNull Player.EventListener eventListener) {
        Preconditions.nonNull(eventListener);

        mEventListeners.remove(eventListener);
    }

    public final void removeAllListeners() {
        mEventListeners.clear();
    }




    @Override
    public final void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
        // do nothing.
    }

    @Override
    public final void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        for(Player.EventListener eventListener : mEventListeners) {
            eventListener.onTracksChanged(trackGroups, trackSelections);
        }
    }

    @Override
    public final void onLoadingChanged(boolean isLoading) {
        for(Player.EventListener eventListener : mEventListeners) {
            eventListener.onLoadingChanged(isLoading);
        }
    }

    @Override
    public final void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        for(Player.EventListener eventListener : mEventListeners) {
            eventListener.onPlayerStateChanged(playbackState);
        }
    }

    @Override
    public final void onRepeatModeChanged(int repeatMode) {
        // do nothing.
    }

    @Override
    public final void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
        // do nothing.
    }

    @Override
    public final void onPlayerError(ExoPlaybackException error) {
        for(Player.EventListener eventListener : mEventListeners) {
            eventListener.onPlayerError(error);
        }
    }

    @Override
    public final void onPositionDiscontinuity(int reason) {
        // do nothing.
    }

    @Override
    public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        // do nothing.
    }

    @Override
    public final void onSeekProcessed() {
        // do nothing.
    }
}

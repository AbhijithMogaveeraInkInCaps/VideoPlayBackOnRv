package com.abhijith.videoplaybackonrv.player.util;

import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.util.misc.Preconditions;
import com.google.android.exoplayer2.SimpleExoPlayer;

/**
 * A concrete implementation of the {@link VolumeController} designed to manage the
 * volume-related settings of the {@link SimpleExoPlayer}.
 */
public final class DefaultVolumeController implements VolumeController {

    private final SimpleExoPlayer player;

    private float oldVolume;

    public DefaultVolumeController(@NonNull SimpleExoPlayer player) {
        this.player = Preconditions.checkNonNull(player);
        this.oldVolume = getVolume();
    }

    @Override
    public final void mute() {
        if(!isMuted()) {
            this.oldVolume = getVolume();

            setVolume(0f);
        }
    }

    @Override
    public final void unmute() {
        if(isMuted()) {
            setVolume(this.oldVolume);
        }
    }

    @Override
    public final void setVolume(float audioVolume) {
        this.player.setVolume(audioVolume);
    }

    @Override
    public final float getVolume() {
        return this.player.getVolume();
    }

    @Override
    public final void setMuted(boolean isMuted) {
        if(isMuted) {
            mute();
        } else {
            unmute();
        }
    }

    @Override
    public final boolean isMuted() {
        return (getVolume() <= 0f);
    }

}

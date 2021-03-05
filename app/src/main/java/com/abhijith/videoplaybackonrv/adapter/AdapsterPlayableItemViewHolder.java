package com.abhijith.videoplaybackonrv.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import com.abhijith.videoplaybackonrv.model.BaseItem;
import com.abhijith.videoplaybackonrv.others.Config;
import com.abhijith.videoplaybackonrv.others.PlayerProviderImpl;
import com.abhijith.videoplaybackonrv.model.PlaybackInfo;
import com.abhijith.videoplaybackonrv.model.VolumeInfo;
import com.abhijith.videoplaybackonrv.player.Player;
import com.abhijith.videoplaybackonrv.util.cache.PlaybackInfoCache;
import com.abhijith.videoplaybackonrv.util.misc.ExoPlayerUtils;
import com.abhijith.videoplaybackonrv.widget.Playable;
import com.abhijith.videoplaybackonrv.widget.PlaybackState;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
//hiii
/**
 * <br>
 *      A {@link Playable} implementation based on the {@link BaseItem.ViewHolder} designed to
 *      enable the Video Autoplayability in the {@link androidx.recyclerview.widget.RecyclerView}-based containers.
 * <br>
 * <br>
 *      <strong>IMPORTANT</strong>:
 *      <br>
 *      <br>
 *           To enable the proper handling of the Video Playback associated with each corresponding Item {@link BaseItem.ViewHolder}
 *           you should use a {@link androidx.recyclerview.widget.RecyclerView}-based container that supports the aforementioned handling.
 *      <br>
 *      <br>
 *           Use the {@link com.abhijith.videoplaybackonrv.widget.PlayableItemsRecyclerView} which has all the necessary handling and related features implemented for you,
 *           or create your own {@link androidx.recyclerview.widget.RecyclerView}-based container with your own implementations of the necessary handling functionality.
 *      <br>
 * <br>
 */
public abstract class AdapsterPlayableItemViewHolder<Data> extends BaseItem.ViewHolder<Data> implements Playable,
        Player.AttachmentStateDelegate, Player.EventListener {


    public static final String TAG = "AdapsterPlayableItemViewHolder";

    private static final float DEFAULT_TRIGGER_OFFSET = 0.5f;


    public final ViewGroup mParentViewGroup;
    public final PlayerView mPlayerView;




    public AdapsterPlayableItemViewHolder(ViewGroup parentViewGroup, View itemView) {
        super(itemView);

        mParentViewGroup = parentViewGroup;
        mPlayerView = itemView.findViewById(com.abhijith.videoplaybackonrv.R.id.player_view);
    }




    @Override
    public final void start() {
        if(!isTrulyPlayable()) {
            return;
        }

        if(startPlayer()) {
            onStateChanged((getPlaybackState() == Player.PlaybackState.READY) ? PlaybackState.READY : PlaybackState.STARTED);
        }
    }




    @Override
    public final void restart() {
        if(!isTrulyPlayable()) {
            return;
        }

        restartPlayer();
        onStateChanged(PlaybackState.RESTARTED);
    }




    @Override
    public final void pause() {
        if(!isTrulyPlayable()) {
            return;
        }

        pausePlayer();
        onStateChanged(PlaybackState.PAUSED);
    }




    @Override
    public final void stop() {
        if(!isTrulyPlayable()) {
            return;
        }

        stopPlayer();
        onStateChanged(PlaybackState.STOPPED);
    }




    @Override
    public final void release() {
        if(!isTrulyPlayable()) {
            return;
        }

        releasePlayer();
        onStateChanged(PlaybackState.STOPPED);
    }




    private boolean startPlayer() {
        // creating/updating the PlaybackInfo for this particular Playable
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final VolumeInfo volumeInfo = playbackInfo.getVolumeInfo();

        setPlaybackInfo(playbackInfo);

        // determining whether the current Playable should play this time
        final boolean shouldPlay = (isLooping() || !playbackInfo.isEnded() || canStartPlaying());

        // preparing the Player
        final Player player = getOrInitPlayer();
        player.init();
        player.attach(mPlayerView);
        player.getVolumeController().setVolume(volumeInfo.getVolume());
        player.getVolumeController().setMuted(volumeInfo.isMuted());
        player.setMediaSource(createMediaSource());
        player.setAttachmentStateDelegate(this);
        player.addEventListener(this);

        // performing the playing related operations (if necessary)
        if(shouldPlay) {
            player.seek(playbackInfo.getPlaybackPosition());
            player.prepare(false);
            player.play();
        }

        return shouldPlay;
    }




    private void restartPlayer() {
        // updating the PlaybackInfo
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.setPlaybackPosition(0);

        final VolumeInfo volumeInfo = playbackInfo.getVolumeInfo();

        setPlaybackInfo(playbackInfo);

        // preparing the Player
        final Player player = getOrInitPlayer();
        player.init();
        player.attach(mPlayerView);
        player.getVolumeController().setVolume(volumeInfo.getVolume());
        player.getVolumeController().setMuted(volumeInfo.isMuted());
        player.setMediaSource(createMediaSource());
        player.setAttachmentStateDelegate(this);
        player.removeEventListener(this);
        player.addEventListener(this);
        player.seek(playbackInfo.getPlaybackPosition());
        player.prepare(false);
        player.play();
    }




    private void pausePlayer() {
        final Player player = getPlayer();
        final PlaybackInfo playbackInfo = getPlaybackInfo();

        if(player != null) {
            player.pause();
            player.removeEventListener(this);

            playbackInfo.setPlaybackPosition(player.getPlaybackPosition());
            setPlaybackInfo(playbackInfo);
        }
    }




    private void stopPlayer() {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        if(player != null) {
            player.pause();
            player.detach(mPlayerView);
            player.stop(true);
            player.setAttachmentStateDelegate(null);
            player.removeEventListener(this);

            playbackInfo.setPlaybackPosition(0L);
            setPlaybackInfo(playbackInfo);
        }
    }




    private void releasePlayer() {
        final Player player = getPlayer();
        unregisterPlayer();
        removePlaybackInfo();

        if(player != null) {
            player.pause();
            player.stop(true);
            player.detach(mPlayerView);
            player.setAttachmentStateDelegate(null);
            player.removeEventListener(this);
        }
    }




    @Override
    public final void seekTo(long positionInMillis) {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        if(player != null) {
            player.seek(positionInMillis);
            playbackInfo.setPlaybackPosition(positionInMillis);
            setPlaybackInfo(playbackInfo);
        }
    }




    @Override
    public final long getPlaybackPosition() {
        final Player player = getPlayer();
        return ((player != null) ? player.getPlaybackPosition() : 0);
    }




    @Override
    public long getDuration() {
        final Player player = getPlayer();
        return ((player != null) ? player.getDuration() : 0);
    }




    @Override
    public final View getPlayerView() {
        return mPlayerView;
    }




    @Override
    public final ViewParent getParent() {
        return (ViewParent) itemView;
    }




    private Player getPlayer() {
        return PlayerProviderImpl.getInstance(itemView.getContext()).getPlayer(getConfig(), getKey());
    }




    private Player getOrInitPlayer() {
        return PlayerProviderImpl.getInstance(itemView.getContext()).getOrInitPlayer(getConfig(), getKey());
    }




    private void unregisterPlayer() {
        PlayerProviderImpl.getInstance(itemView.getContext()).unregister(getConfig(), getKey());
    }




    private MediaSource createMediaSource() {
        return PlayerProviderImpl.getInstance(itemView.getContext()).createMediaSource(
            getConfig(),
            Uri.parse(getUrl()),
            isLooping()
        );
    }




    private void setPlaybackInfo(PlaybackInfo playbackInfo) {
        PlaybackInfoCache.getInstance().put(getKey(), playbackInfo);
    }




    @Override
    public final PlaybackInfo getPlaybackInfo() {
        return PlaybackInfoCache.getInstance().get(getKey(), new PlaybackInfo());
    }




    private void removePlaybackInfo() {
        PlaybackInfoCache.getInstance().remove(getKey());
    }




    private int getPlaybackState() {
        final Player player = getPlayer();
        return ((player != null) ? player.getPlaybackState() : Player.PlaybackState.IDLE);
    }




    @NonNull
    @Override
    public Config getConfig() {
        return PlayerProviderImpl.DEFAULT_CONFIG;
    }




    @NonNull
    @Override
    public String getTag() {
        return "";
    }




    @NonNull
    @Override
    public final String getKey() {
        return (getUrl() + getTag());
    }




    /**
     * <br>
     *      Used to determine the current {@link com.abhijith.videoplaybackonrv.adapter.AdapsterPlayableItemViewHolder}'s Item {@link View} area visibility ratio that's
     *      sufficient enough to start/stop the video playback.
     * <br>
     * <br>
     *     This method is used internally by the {@link #wantsToPlay()} method to properly determine whether it's
     *     the time to start or stop the video playback.
     * <br>
     * <br>
     *     You can override this method and specify your own area visibility ratio to be used for the handling of the aforementioned events,
     *     just keep in mind the fact that the ratio should be between <strong>0.0</strong> and <strong>1.0</strong>, where
     *     <strong>0.0</strong> and <strong>1.0</strong> are 0% and 100% Item {@link View} visibility correspondingly.
     *
     * @return a value between <strong>0.0</strong> and <strong>1.0</strong>.
     */
    @FloatRange(from = 0.0, to = 1.0)
    protected float getTriggerOffset() {
        return DEFAULT_TRIGGER_OFFSET;
    }




    /**
     * <br>
     *      Sets the audio volume to be used during the playback of the video associated with this {@link com.abhijith.videoplaybackonrv.adapter.AdapsterPlayableItemViewHolder}.
     * <br>
     *      If the playback is the active state, then the volume will be adjusted directly on the corresponding {@link Player} instance.
     *
     * @param audioVolume the exact audio volume (a value between <strong>0.0</strong> and <strong>1.0</strong>).
     */
    protected final void setVolume(@FloatRange(from = 0.0, to = 1.0) float audioVolume) {
        // creating/updating the corresponding Playback Info
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.getVolumeInfo().setVolume(audioVolume);

        setPlaybackInfo(playbackInfo);

        // updating the Player-related state (if necessary)
        final Player player = getPlayer();

        if(player != null) {
            player.getVolumeController().setVolume(audioVolume);
        }
    }




    /**
     * Retrieves the audio volume that's associated with the current instance of the {@link com.abhijith.videoplaybackonrv.adapter.AdapsterPlayableItemViewHolder}.
     *
     * @return an audio volume ratio (a value between <strong>0.0</strong> and <strong>1.0</strong>).
     */
    @FloatRange(from = 0.0, to = 1.0)
    protected final float getVolume() {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        return ((player != null) ? player.getVolumeController().getVolume() : playbackInfo.getVolumeInfo().getVolume());
    }




    /**
     * <br>
     *      Sets the audio muted state to be used during the playback of the video associated with this {@link com.abhijith.videoplaybackonrv.adapter.AdapsterPlayableItemViewHolder}.
     * <br>
     *      If the playback is the active state, then the audio muted state will be adjusted directly on the corresponding {@link Player} instance.
     *
     * @param isMuted the exact audio muted state.
     */
    protected final void setMuted(boolean isMuted) {
        // creating/updating the corresponding Playback Info
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.getVolumeInfo().setMuted(isMuted);

        setPlaybackInfo(playbackInfo);

        // updating the Player-related state (if necessary)
        final Player player = getPlayer();

        if(player != null) {
            player.getVolumeController().setMuted(isMuted);
        }
    }




    /**
     * Retrieves the muted state of the audio that's associated with the current instance of the {@link com.abhijith.videoplaybackonrv.adapter.AdapsterPlayableItemViewHolder}.
     *
     * @return the muted state of the audio.
     */
    protected final boolean isMuted() {
        final PlaybackInfo playbackInfo = getPlaybackInfo();
        final Player player = getPlayer();

        return ((player != null) ? player.getVolumeController().isMuted() : playbackInfo.getVolumeInfo().isMuted());
    }




    @Override
    public final boolean isPlaying() {
        final Player player = getPlayer();
        return ((player != null) && player.isPlaying());
    }




    @Override
    public final boolean isTrulyPlayable() {
        return (mPlayerView != null);
    }




    @Override
    public boolean isLooping() {
        return false;
    }




    private boolean isEnded() {
        final Player player = getPlayer();
        return ((player != null) && (player.getPlaybackState() == Player.PlaybackState.ENDED));
    }




    @Override
    public final boolean isAttached(@NonNull Player player) {
        return (isTrulyPlayable() && player.isAttached(mPlayerView));
    }




    @Override
    public final boolean wantsToPlay() {
        return (ExoPlayerUtils.getVisibleAreaOffset(this) >= getTriggerOffset());
    }




    /**
     * <br>
     *      Used to determine whether the playback of a non-looping video can be started.
     *      (Used as a last means of confirmation of the initiation of the playback)
     * <br>
     *      It is to be overridden and used only in cases when you need a specific
     *      control over when the video playback starts (or can be started).
     * <br>
     *      By default, it's always <strong>true</strong>.
     *
     * @return <strong>true</strong> to allow the initiation of the video playback, <strong>false</strong> otherwise.
     */
    protected boolean canStartPlaying() {
        return true;
    }




    /**
     * Gets called when the Playback {@link PlaybackState} changes.
     *
     * @param playbackState the new Playback {@link PlaybackState}.
     */
    protected void onStateChanged(@NonNull PlaybackState playbackState) {
        // to be overridden.
    }




    @Override
    public final void onAttach(@NonNull Player player) {
        if(mPlayerView != null) {
            player.attach(mPlayerView);
        }
    }




    @Override
    public final void onDetach(@NonNull Player player) {
        if(mPlayerView != null) {
            player.detach(mPlayerView);
        }
    }




    @Override
    public void onPlayabilityStateChanged(boolean isPlayable) {
        // to be overridden if necessary.
    }




    @Override
    public final void onPlayerStateChanged(int playbackState) {
        switch(playbackState) {

            case Player.PlaybackState.IDLE:
                onPlaybackIdle();
                break;

            case Player.PlaybackState.BUFFERING:
                onPlaybackBuffering();
                break;

            case Player.PlaybackState.READY:
                onPlaybackReady();
                break;

            case Player.PlaybackState.ENDED:
                onPlaybackEnded();
                break;

        }
    }




    private void onPlaybackIdle() {
        getPlaybackInfo().setEnded(isEnded());

        onStateChanged(PlaybackState.STOPPED);
    }




    private void onPlaybackBuffering() {
        getPlaybackInfo().setEnded(isEnded());

        onStateChanged(PlaybackState.BUFFERING);
    }




    private void onPlaybackReady() {
        getPlaybackInfo().setEnded(isEnded());

        onStateChanged(PlaybackState.READY);
    }




    private void onPlaybackEnded() {
        onStateChanged(PlaybackState.STOPPED);

        final PlaybackInfo playbackInfo = getPlaybackInfo();
        playbackInfo.setPlaybackPosition(0);
        playbackInfo.setEnded(isEnded());
        setPlaybackInfo(playbackInfo);
    }




    @Override
    public final void onLoadingChanged(boolean isLoading) {
        // do nothing.
    }




    @Override
    public final void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        // do nothing.
    }




    @Override
    public final void onPlayerError(ExoPlaybackException error) {
//        Log.e(TAG, ("onPlayerError: " + error.getLocalizedMessage()));

        onStateChanged(PlaybackState.ERROR);

        //TODO <--- onPlayback ended?!
    }




}

package com.abhijith.videoplaybackonrv.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abhijith.videoplaybackonrv.util.misc.Preconditions;

/**
 * A model object used to represent the Video Playback-related Info.
 * (This info is, in most cases, used for proper handling of the {@link androidx.recyclerview.widget.RecyclerView}-specific Video Item Playbacks)
 */
public final class PlaybackInfo implements Parcelable {


    private long playbackPosition;
    private long duration;

    private com.abhijith.videoplaybackonrv.model.VolumeInfo volumeInfo;

    private boolean isEnded;




    public PlaybackInfo() {
        this.playbackPosition = 0L;
        this.duration = 0L;
        this.volumeInfo = new com.abhijith.videoplaybackonrv.model.VolumeInfo();
        this.isEnded = false;
    }

    public PlaybackInfo(@NonNull com.abhijith.videoplaybackonrv.model.PlaybackInfo info) {
        Preconditions.nonNull(info);

        this.playbackPosition = info.playbackPosition;
        this.duration = info.duration;
        this.volumeInfo = new com.abhijith.videoplaybackonrv.model.VolumeInfo(info.volumeInfo);
        this.isEnded = info.isEnded;
    }




    private PlaybackInfo(Parcel in) {
        this.playbackPosition = in.readLong();
        this.duration = in.readLong();
        this.volumeInfo = in.readParcelable(com.abhijith.videoplaybackonrv.model.VolumeInfo.class.getClassLoader());
        this.isEnded = (in.readByte() != 0);
    }




    /**
     * Sets the Video Playback Position (in milliseconds).
     *
     * @param playbackPosition video playback position in milliseconds
     * @return the same instance of the {@link com.abhijith.videoplaybackonrv.model.PlaybackInfo} for chaining purposes.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.PlaybackInfo setPlaybackPosition(long playbackPosition) {
        this.playbackPosition = playbackPosition;
        return this;
    }




    /**
     * Retrieves the Video Playback Position (in milliseconds).
     *
     * @return the playback position in milliseconds.
     */
    public final long getPlaybackPosition() {
        return this.playbackPosition;
    }




    /**
     * Sets the Video Duration (in milliseconds).
     *
     * @param duration video duration in milliseconds
     * @return the same instance of the {@link com.abhijith.videoplaybackonrv.model.PlaybackInfo} for chaining purposes.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.PlaybackInfo setDuration(long duration) {
        this.duration = duration;
        return this;
    }




    /**
     * Retrieves the Video Duration (in milliseconds).
     *
     * @return the video duration in milliseconds.
     */
    public final long getDuration() {
        return this.duration;
    }




    /**
     * Sets the Video Volume Info.
     *
     * @param volumeInfo video volume info
     * @return the same instance of the {@link com.abhijith.videoplaybackonrv.model.PlaybackInfo} for chaining purposes.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.PlaybackInfo setVolumeInfo(@NonNull com.abhijith.videoplaybackonrv.model.VolumeInfo volumeInfo) {
        this.volumeInfo = Preconditions.checkNonNull(volumeInfo);
        return this;
    }




    /**
     * Retrieves the Video Volume Info.
     *
     * @return the video volume info.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.VolumeInfo getVolumeInfo() {
        return this.volumeInfo;
    }




    /**
     * Sets the Video Playback Ended State.
     *
     * @param isEnded whether the video playback ended or not
     * @return the same instance of the {@link com.abhijith.videoplaybackonrv.model.PlaybackInfo} for chaining purposes.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.PlaybackInfo setEnded(boolean isEnded) {
        this.isEnded = isEnded;
        return this;
    }




    /**
     * Retrieves the Video Playback Ended State.
     *
     * @return <strong>true</strong> if the video playback ended, <strong>false</strong> otherwise.
     */
    public final boolean isEnded() {
        return this.isEnded;
    }




    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 17;
        result = ((result * prime) + Long.valueOf(this.playbackPosition).hashCode());
        result = ((result * prime) + Long.valueOf(this.duration).hashCode());
        result = ((result * prime) + this.volumeInfo.hashCode());
        result = ((result * prime) + (this.isEnded ? 1 : 0));

        return result;
    }




    @Override
    public final boolean equals(@Nullable Object obj) {
        return ((obj instanceof com.abhijith.videoplaybackonrv.model.PlaybackInfo) && (obj.hashCode() == hashCode()));
    }




    @Override
    public final int describeContents() {
        return 0;
    }




    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.playbackPosition);
        dest.writeLong(this.duration);
        dest.writeParcelable(this.volumeInfo, flags);
        dest.writeByte(this.isEnded ? ((byte) 1) : ((byte) 0));
    }




    public static final Creator<PlaybackInfo> CREATOR = new ClassLoaderCreator<PlaybackInfo>() {

        @Override
        public com.abhijith.videoplaybackonrv.model.PlaybackInfo createFromParcel(Parcel source, ClassLoader loader) {
            return new com.abhijith.videoplaybackonrv.model.PlaybackInfo(source);
        }

        @Override
        public com.abhijith.videoplaybackonrv.model.PlaybackInfo createFromParcel(Parcel source) {
            return new com.abhijith.videoplaybackonrv.model.PlaybackInfo(source);
        }

        @Override
        public com.abhijith.videoplaybackonrv.model.PlaybackInfo[] newArray(int size) {
            return new com.abhijith.videoplaybackonrv.model.PlaybackInfo[size];
        }

    };




}

package com.abhijith.videoplaybackonrv.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.math.MathUtils;

import com.abhijith.videoplaybackonrv.util.misc.Preconditions;

/**
 * A model object used to represent the Video-related Volume Info.
 * (This info is, in most cases, used for proper handling of the {@link androidx.recyclerview.widget.RecyclerView}-specific Video Item Volume management)
 */
public final class VolumeInfo implements Parcelable {


    private float audioVolume;

    private boolean isMuted;




    public VolumeInfo() {
        this(1f, false);
    }




    public VolumeInfo(float audioVolume, boolean isMuted) {
        this.audioVolume = audioVolume;
        this.isMuted = isMuted;
    }




    public VolumeInfo(@NonNull com.abhijith.videoplaybackonrv.model.VolumeInfo info) {
        Preconditions.nonNull(info);

        this.audioVolume = info.audioVolume;
        this.isMuted = info.isMuted;
    }




    private VolumeInfo(Parcel in) {
        this.audioVolume = in.readFloat();
        this.isMuted = (in.readByte() != 0);
    }




    /**
     * Sets the Audio Volume.
     *
     * @param audioVolume the audio volume ratio (a value between 0.0 and 1.0)
     * @return the same instance of the {@link com.abhijith.videoplaybackonrv.model.VolumeInfo} for chaining purposes.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.VolumeInfo setVolume(@FloatRange(from = 0.0, to = 1.0) float audioVolume) {
        this.audioVolume = MathUtils.clamp(audioVolume, 0f, 1f);
        return this;
    }




    /**
     * Retrieves the Audio Volume Ratio.
     *
     * @return the audio volume ratio (a value between 0.0 and 1.0).
     */
    @FloatRange(from = 0.0, to = 1.0)
    public final float getVolume() {
        return this.audioVolume;
    }




    /**
     * Sets the Audio "Muted" state.
     *
     * @param isMuted whether the Audio is muted or not
     * @return the same instance of the {@link com.abhijith.videoplaybackonrv.model.VolumeInfo} for chaining purposes.
     */
    @NonNull
    public final com.abhijith.videoplaybackonrv.model.VolumeInfo setMuted(boolean isMuted) {
        this.isMuted = isMuted;
        return this;
    }




    /**
     * Retrieves the muted state of the Audio.
     *
     * @return <strong>true</strong> if the audio is muted, <strong>false</strong> otherwise.
     */
    public final boolean isMuted() {
        return this.isMuted;
    }




    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 17;
        result = ((result * prime) + Float.floatToIntBits(this.audioVolume));
        result = ((result * prime) + (this.isMuted ? 1 : 0));

        return result;
    }




    @Override
    public final boolean equals(@Nullable Object obj) {
        return ((obj instanceof com.abhijith.videoplaybackonrv.model.VolumeInfo) && (obj.hashCode() == hashCode()));
    }




    @Override
    public final int describeContents() {
        return 0;
    }




    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.audioVolume);
        dest.writeByte(this.isMuted ? ((byte) 1) : ((byte) 0));
    }




    public static final Creator<VolumeInfo> CREATOR = new ClassLoaderCreator<VolumeInfo>() {

        @Override
        public com.abhijith.videoplaybackonrv.model.VolumeInfo createFromParcel(Parcel source, ClassLoader loader) {
            return new com.abhijith.videoplaybackonrv.model.VolumeInfo(source);
        }

        @Override
        public com.abhijith.videoplaybackonrv.model.VolumeInfo createFromParcel(Parcel source) {
            return new com.abhijith.videoplaybackonrv.model.VolumeInfo(source);
        }

        @Override
        public com.abhijith.videoplaybackonrv.model.VolumeInfo[] newArray(int size) {
            return new com.abhijith.videoplaybackonrv.model.VolumeInfo[size];
        }

    };




}

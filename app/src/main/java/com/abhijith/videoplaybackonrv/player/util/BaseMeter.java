package com.abhijith.videoplaybackonrv.player.util;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;

/**
 * A utility used for bandwidth metering, as well as observation of the data transfer-related events.
 */
public final class BaseMeter<
        T extends BandwidthMeter,
        S extends TransferListener>
        implements BandwidthMeter, TransferListener {

    @NonNull
    final T bandwidthMeter;
    @NonNull
    final S transferListener;

    @SuppressWarnings("WeakerAccess")
    public BaseMeter(@NonNull T bandwidthMeter, @NonNull S transferListener) {
        this.bandwidthMeter = bandwidthMeter;
        this.transferListener = transferListener;
    }

    @Override
    public final void addEventListener(Handler eventHandler, EventListener eventListener) {
        this.bandwidthMeter.addEventListener(eventHandler, eventListener);
    }

    @Override
    public final void removeEventListener(EventListener eventListener) {
        this.bandwidthMeter.removeEventListener(eventListener);
    }

    @Nullable
    @Override
    public final TransferListener getTransferListener() {
        return this.bandwidthMeter.getTransferListener();
    }

    @Override
    public final long getBitrateEstimate() {
        return this.bandwidthMeter.getBitrateEstimate();
    }

    @Override
    public final void onTransferInitializing(
            DataSource dataSource,
            DataSpec dataSpec,
            boolean isNetwork
    ) {
        this.transferListener.onTransferInitializing(dataSource, dataSpec, isNetwork);
    }

    @Override
    public final void onTransferStart(
            DataSource dataSource,
            DataSpec dataSpec,
            boolean isNetwork
    ) {
        this.transferListener.onTransferStart(
                dataSource,
                dataSpec,
                isNetwork
        );
    }

    @Override
    public final void onBytesTransferred(
            DataSource dataSource,
            DataSpec dataSpec,
            boolean isNetwork,
            int bytesTransferred
    ) {
        this.transferListener.onBytesTransferred(
                dataSource,
                dataSpec,
                isNetwork,
                bytesTransferred
        );
    }

    @Override
    public final void onTransferEnd(
            DataSource dataSource,
            DataSpec dataSpec,
            boolean isNetwork
    ) {
        this.transferListener.onTransferEnd(
                dataSource,
                dataSpec,
                isNetwork
        );
    }
}

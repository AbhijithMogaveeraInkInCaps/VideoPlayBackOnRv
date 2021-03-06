 package com.abhijith.videoplaybackonrv.player.util;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abhijith.videoplaybackonrv.util.misc.CollectionUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An implementation of the {@link DefaultRenderersFactory} that takes into account the Digital Rights Management.
 */
public class MultiDrmRendererFactory extends DefaultRenderersFactory {


    private final List<DrmSessionManager<FrameworkMediaCrypto>> drmSessionManagers;




    public MultiDrmRendererFactory(@NonNull Context context,
                                   @Nullable DrmSessionManager[] managers,
                                   @ExtensionRendererMode int extensionRendererMode) {
        super(context);

        setExtensionRendererMode(extensionRendererMode);

        if((managers != null) && (managers.length > 1)) {
            this.drmSessionManagers = CollectionUtils.toListUnchecked(managers);
        } else {
            this.drmSessionManagers = new ArrayList<>();
        }
    }




    @Override
    protected void buildVideoRenderers(Context context,
                                       int extensionRendererMode,
                                       MediaCodecSelector mediaCodecSelector,
                                       @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager,
                                       boolean playClearSamplesWithoutKeys,
                                       boolean enableDecoderFallback,
                                       Handler eventHandler,
                                       VideoRendererEventListener eventListener,
                                       long allowedVideoJoiningTimeMs,
                                       ArrayList<Renderer> out) {
        final ArrayList<Renderer> localOut = new ArrayList<>();

        super.buildVideoRenderers(
            context,
            extensionRendererMode,
            mediaCodecSelector,
            drmSessionManager,
            playClearSamplesWithoutKeys,
            enableDecoderFallback,
            eventHandler,
            eventListener,
            allowedVideoJoiningTimeMs,
            localOut
        );

        final Set<Renderer> outSet = new HashSet<>(localOut);

        if(!this.drmSessionManagers.isEmpty()) {
            localOut.clear();

            for(DrmSessionManager<FrameworkMediaCrypto> manager : this.drmSessionManagers) {
                super.buildVideoRenderers(
                    context,
                    EXTENSION_RENDERER_MODE_OFF,
                    mediaCodecSelector,
                    manager,
                    playClearSamplesWithoutKeys,
                    enableDecoderFallback,
                    eventHandler,
                    eventListener,
                    allowedVideoJoiningTimeMs,
                    localOut
                );
            }

            outSet.addAll(localOut);
        }

        out.addAll(outSet);
    }




    @Override
    protected void buildAudioRenderers(Context context,
                                       int extensionRendererMode,
                                       MediaCodecSelector mediaCodecSelector,
                                       @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager,
                                       boolean playClearSamplesWithoutKeys,
                                       boolean enableDecoderFallback,
                                       AudioProcessor[] audioProcessors,
                                       Handler eventHandler,
                                       AudioRendererEventListener eventListener,
                                       ArrayList<Renderer> out) {
        final ArrayList<Renderer> localOut = new ArrayList<>();

        super.buildAudioRenderers(
            context,
            extensionRendererMode,
            mediaCodecSelector,
            drmSessionManager,
            playClearSamplesWithoutKeys,
            enableDecoderFallback,
            audioProcessors,
            eventHandler,
            eventListener,
            localOut
        );

        final Set<Renderer> outSet = new HashSet<>(localOut);

        if(!this.drmSessionManagers.isEmpty()) {
            localOut.clear();

            for(DrmSessionManager<FrameworkMediaCrypto> manager : this.drmSessionManagers) {
                super.buildAudioRenderers(
                    context,
                    EXTENSION_RENDERER_MODE_OFF,
                    mediaCodecSelector,
                    manager,
                    playClearSamplesWithoutKeys,
                    enableDecoderFallback,
                    audioProcessors,
                    eventHandler,
                    eventListener,
                    localOut
                );
            }

            outSet.addAll(localOut);
        }

        out.addAll(outSet);
    }




}
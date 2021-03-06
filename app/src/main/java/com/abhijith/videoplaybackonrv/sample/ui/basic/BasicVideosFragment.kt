package com.abhijith.videoplaybackonrv.sample.ui.basic

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.sample.ui.base.BaseFragment
import com.abhijith.videoplaybackonrv.widget.PlayableItemsContainer
import com.abhijith.videoplaybackonrv.sample.util.markers.CanManagePlayback
import com.abhijith.videoplaybackonrv.sample.util.markers.HasTitle

class BasicVideosFragment : BaseFragment(), CanManagePlayback, HasTitle {


    private var autoplayMode = PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME


    companion object {

        const val TAG = "BasicVideosFragment"
        private const val EXTRA_AUTOPLAY_MODE = "autoplay_mode"

        @JvmStatic fun newInstance(autoplayMode : PlayableItemsContainer.AutoplayMode) : BasicVideosFragment {
            return BasicVideosFragment().apply {
                arguments = newBundleInstance(autoplayMode)
            }
        }

        @JvmStatic fun newBundleInstance(autoplayMode : PlayableItemsContainer.AutoplayMode) : Bundle {
            return bundleOf(EXTRA_AUTOPLAY_MODE to autoplayMode)
        }

    }


    override fun fetchExtras(extras : Bundle) {
        super.fetchExtras(extras)

        autoplayMode = ((extras.getSerializable(EXTRA_AUTOPLAY_MODE) as PlayableItemsContainer.AutoplayMode?) ?: autoplayMode)
    }


    override fun init(savedInstanceState : Bundle?) {
       /* with(recyclerView) {
            setPlaybackTriggeringStates(
                PlayableItemsContainer.PlaybackTriggeringState.IDLING,
                PlayableItemsContainer.PlaybackTriggeringState.DRAGGING
            )

            autoplayMode = this@BasicVideosFragment.autoplayMode
            layoutManager = PreCacheLinearLayoutManager(context!!).also {
                it.isItemPrefetchEnabled = true
                it.initialPrefetchItemCount = 4
            }
            adapter = BasicVideoItemsRecyclerViewAdapter(
                context = context!!,
                items = VideoProvider.getVideos(count = 100, mute = true).toMutableList(),
                arviConfig = Config.Builder()
                    .cache(ExoPlayerUtils.getCache(context!!))
                    .build()
            )
        }*/
    }


    override fun startPlayback() {
        /*recyclerView?.startPlayback()*/
    }


    override fun stopPlayback() {
        /*recyclerView?.stopPlayback()*/
    }


    override fun onResume() {
        super.onResume()

        if(userVisibleHint) {
            /*recyclerView.onResume()*/
        }
    }


    override fun onPause() {
        super.onPause()

        /*recyclerView.onPause()*/
    }


    override fun onBecameVisibleToUser() {
        super.onBecameVisibleToUser()

        if(isViewCreated) {
            /*recyclerView.onResume()*/
        }
    }


    override fun onBecameInvisibleToUser() {
        super.onBecameInvisibleToUser()

        if(isViewCreated) {
           /* recyclerView.onPause()*/
        }
    }


    override fun onRecycle() {
        super.onRecycle()
/*
        recyclerView?.onDestroy()*/
    }


    override fun getLayoutId() : Int {
        return R.layout.fragment_videos
    }


    override fun getTitle(context : Context) : CharSequence {
        return when(autoplayMode) {
            PlayableItemsContainer.AutoplayMode.MULTIPLE_SIMULTANEOUSLY -> context.getString(R.string.title_videos_fragment_playback_multiple_simultaneously)
            else -> context.getString(R.string.title_videos_fragment_playback_one_at_a_time)
        }
    }


}
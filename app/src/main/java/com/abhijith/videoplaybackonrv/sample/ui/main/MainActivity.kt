package com.abhijith.videoplaybackonrv.sample.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.helpers.PreCacheLinearLayoutManager
import com.abhijith.videoplaybackonrv.others.Config
import com.abhijith.videoplaybackonrv.sample.adapters.basic.BasicVideoItemsRecyclerViewAdapter
import com.abhijith.videoplaybackonrv.sample.ui.base.BaseActivity
import com.abhijith.videoplaybackonrv.sample.util.providers.VideoProvider
import com.abhijith.videoplaybackonrv.util.misc.ExoPlayerUtils
import com.abhijith.videoplaybackonrv.widget.PlayableItemsContainer
import com.abhijith.videoplaybackonrv.widget.PlayableItemsRecyclerView

class MainActivity : BaseActivity() {

    override fun init(savedInstanceState : Bundle?) {
        findViewById<RecyclerView>(R.id.rv)?.apply {
           /* setPlaybackTriggeringStates(
                PlayableItemsContainer.PlaybackTriggeringState.IDLING,
                PlayableItemsContainer.PlaybackTriggeringState.DRAGGING
            )
            autoplayMode = PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME
            layoutManager = PreCacheLinearLayoutManager(
                context!!,
                LinearLayoutManager.VERTICAL,
                false
            ).also {
                it.isItemPrefetchEnabled = true
                it.initialPrefetchItemCount = 4
            }
            adapter = BasicVideoItemsRecyclerViewAdapter(
                context = context!!,
                items = VideoProvider.getVideos(count = 100, mute = true).toMutableList(),
                arviConfig = Config.Builder()
                    .cache(ExoPlayerUtils.getCache(context!!))
                    .build()
            )*/
        adapter = RVAdapter()
        }
    }

    override fun getLayoutId() : Int {
        return R.layout.activity_main
    }
}

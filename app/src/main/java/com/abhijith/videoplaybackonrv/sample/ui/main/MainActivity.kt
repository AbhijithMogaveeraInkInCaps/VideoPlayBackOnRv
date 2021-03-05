package com.abhijith.videoplaybackonrv.sample.ui.main

import android.os.Bundle
import android.widget.ImageView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.sample.adapters.basic.GlobalFlag
import com.abhijith.videoplaybackonrv.sample.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun init(savedInstanceState : Bundle?) {
        val myRV = findViewById<MyRV>(R.id.rv)
        findViewById<ImageView>(R.id.volume).apply{
            setOnClickListener {
                myRV.apply {
                    GlobalFlag.isMute = !GlobalFlag.isMute
                    this.mute(GlobalFlag.isMute)
                }
            }
        }
        myRV?.apply {
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

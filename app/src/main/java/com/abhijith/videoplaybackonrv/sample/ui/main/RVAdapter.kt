package com.abhijith.videoplaybackonrv.sample.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.helpers.PreCacheLinearLayoutManager
import com.abhijith.videoplaybackonrv.others.Config
import com.abhijith.videoplaybackonrv.sample.adapters.basic.BasicVideoItemsRecyclerViewAdapter
import com.abhijith.videoplaybackonrv.sample.ui.main.SelectiveAction.*
import com.abhijith.videoplaybackonrv.sample.util.providers.VideoProvider
import com.abhijith.videoplaybackonrv.util.misc.ExoPlayerUtils
import com.abhijith.videoplaybackonrv.widget.PlayableItemsContainer
import com.abhijith.videoplaybackonrv.widget.PlayableItemsRecyclerView


class RVAdapter : RecyclerView.Adapter<RVAdapter.VH>() {
    class VH(v: View) : RecyclerView.ViewHolder(v), ViewHolderExtension {
        val vp = v.findViewById<PlayableItemsRecyclerView>(R.id.vp2)
        var tv = v.findViewById<TextView>(R.id.ct)
        var pos = -1

        init {
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this.vp)
        }

        override fun action(extensionInfo: ExtensionInfo) {
            when (extensionInfo.action) {
                NONE -> {
                }
                ATTACHED_WIN -> {
                    Log.e("Pos", "play $pos")
                    vp.startPlayback()
                }
                ATTACHED_LOST -> {
                    Log.e("Pos", "pause $pos")
                    vp.pausePlayback()
                }
                ATTACHED_CANDIDATE -> {
                }
                DETACHED -> {
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.my_layout, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.vp?.apply {
            holder.pos = position
            holder.tv.text = position.toString()
            setPlaybackTriggeringStates(
                PlayableItemsContainer.PlaybackTriggeringState.IDLING,
                PlayableItemsContainer.PlaybackTriggeringState.DRAGGING
            )
            autoplayMode = PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME
            layoutManager = PreCacheLinearLayoutManager(
                context!!,
                LinearLayoutManager.HORIZONTAL,
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
            )
        }
    }

    override fun getItemCount(): Int {
        return 200
    }
}

interface ViewHolderExtension {
    fun action(extensionInfo: ExtensionInfo)
}

data class ExtensionInfo(
    var action: SelectiveAction = NONE
)

enum class SelectiveAction {
    NONE,
    ATTACHED_WIN,
    ATTACHED_LOST,
    ATTACHED_CANDIDATE,
    DETACHED
}
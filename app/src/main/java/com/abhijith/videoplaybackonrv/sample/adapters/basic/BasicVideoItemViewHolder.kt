package com.abhijith.videoplaybackonrv.sample.adapters.basic

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.others.Config
import com.abhijith.videoplaybackonrv.widget.PlayableItemViewHolder
import com.abhijith.videoplaybackonrv.widget.PlaybackState
import com.abhijith.videoplaybackonrv.sample.model.Video
import com.abhijith.videoplaybackonrv.sample.util.extensions.getColorCompat
import com.abhijith.videoplaybackonrv.sample.util.extensions.makeGone
import com.abhijith.videoplaybackonrv.sample.util.extensions.makeVisible
import com.abhijith.videoplaybackonrv.sample.util.extensions.setColor

class BasicVideoItemViewHolder(
    parent : ViewGroup,
    itemView : View,
    val arviConfig : Config
) : PlayableItemViewHolder(parent, itemView) {

    val titleTv = itemView.findViewById<TextView>(R.id.titleTv)
    val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
    val errorIconIv = itemView.findViewById<ImageView>(R.id.errorIconIv)
    val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)

    var video : Video? = null

    fun bindData(data : Video?) {
        data?.also {
            handleData(it)
            video = it
        }
    }


    private fun handleData(data : Video) {
        handleInfoViews()
        titleTv.text = data.title
    }


    private fun handleInfoViews() {
        progressBar.makeGone()
        progressBar.setColor(progressBar.context.getColorCompat(R.color.video_item_progress_bar_color))
        errorIconIv.makeGone()
    }


    override fun getUrl() : String {
        return (video?.videoUrl ?: "")
    }


    override fun getConfig() : Config {
        return arviConfig
    }


    override fun isLooping() : Boolean {
        return true
    }


    override fun onStateChanged(playbackState : PlaybackState) {
        super.onStateChanged(playbackState)
        when(playbackState) {
            PlaybackState.BUFFERING -> onBufferingState()
            PlaybackState.READY -> onReadyState()
            PlaybackState.PAUSED -> onPausedState()
            PlaybackState.STOPPED -> onStoppedState()
            PlaybackState.ERROR -> onErrorState()
        }
    }


    private fun onBufferingState() {
        progressBar.makeVisible()
        errorIconIv.makeGone()
    }


    private fun onReadyState() {
        progressBar.makeGone()
        errorIconIv.makeGone()
        ivThumbnail.makeGone()

        video?.isMuted?.let(::setMuted)
    }


    private fun onPausedState() {
        progressBar.makeGone()
        ivThumbnail.makeVisible()
    }


    private fun onStoppedState() {
        progressBar.makeGone()
        ivThumbnail.makeVisible()
    }


    private fun onErrorState() {
        progressBar.makeGone()
        ivThumbnail.makeGone()
        errorIconIv.makeVisible()
    }
}
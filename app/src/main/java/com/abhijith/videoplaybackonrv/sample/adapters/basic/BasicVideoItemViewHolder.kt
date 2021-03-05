package com.abhijith.videoplaybackonrv.sample.adapters.basic

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.others.Config
import com.abhijith.videoplaybackonrv.sample.model.Video
import com.abhijith.videoplaybackonrv.sample.util.extensions.getColorCompat
import com.abhijith.videoplaybackonrv.sample.util.extensions.makeGone
import com.abhijith.videoplaybackonrv.sample.util.extensions.makeVisible
import com.abhijith.videoplaybackonrv.sample.util.extensions.setColor
import com.abhijith.videoplaybackonrv.widget.PlayableItemViewHolder
import com.abhijith.videoplaybackonrv.widget.PlaybackState

class BasicVideoItemViewHolder(
    parent : ViewGroup,
    itemView : View,
    val arviConfig : Config
) : PlayableItemViewHolder(parent, itemView) {

    var lastPlayedPosition = 0L
    val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    val errorIconIv: ImageView = itemView.findViewById(R.id.errorIconIv)
    val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)

    var video : Video? = null

//    fun setMute(boolean: Boolean){
//        video?.isMuted = boolean
//    }

    fun bindData(data : Video?) {
        data?.also {
            video = it
//            video?.isMuted = true
//            seekTo(1)
            handleData(it)
//            isMuted = true
        }
    }


    private fun handleData(data : Video) {
        handleInfoViews()
        titleTv.text = data.title
        lastPlayedPosition = 0L
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

    override fun getlastPlayedPosition(): Long {
        return lastPlayedPosition
    }


    override fun onStateChanged(playbackState : PlaybackState) {
        super.onStateChanged(playbackState)
        when(playbackState) {
            PlaybackState.BUFFERING -> onBufferingState()
            PlaybackState.READY -> onReadyState()
            PlaybackState.PAUSED -> onPausedState()
            PlaybackState.STOPPED -> onStoppedState()
            PlaybackState.ERROR -> onErrorState()
            PlaybackState.STARTED->onStartedState()
            PlaybackState.RESTARTED->onRestarted()
        }
    }

    private fun onRestarted() {
        log("onRestarted")
    }

    private fun onStartedState() {
        log("onStartedState")
        ivThumbnail.makeGone()
//        isMuted = GlobalFlag.isMute
        seekTo(lastPlayedPosition)
    }


    private fun onBufferingState() {
        log("onBufferingState")
        progressBar.makeVisible()
        errorIconIv.makeGone()
    }


    private fun onReadyState() {
        log("onReadyState")
        progressBar.makeGone()
        errorIconIv.makeGone()
        ivThumbnail.makeGone()

//        video?.isMuted?.let(::setMuted)
        GlobalFlag.isMute.let(::setMuted)
    }


    private fun onPausedState() {
        log("onPausedState")
        lastPlayedPosition = currentPosition
        progressBar.makeGone()
    }


    private fun onStoppedState() {
        log("onStoppedState")
        lastPlayedPosition = 0L
        progressBar.makeGone()
        ivThumbnail.makeVisible()
    }


    private fun onErrorState() {
        log("onErrorState")
        progressBar.makeGone()
        ivThumbnail.makeGone()
        errorIconIv.makeVisible()
    }
}

object GlobalFlag{
    var isMute = false
}

fun log(string: String){
    Log.e("RAO",string)
}
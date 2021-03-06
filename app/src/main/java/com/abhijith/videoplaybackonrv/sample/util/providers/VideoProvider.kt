package com.abhijith.videoplaybackonrv.sample.util.providers

import android.net.Uri
import com.abhijith.videoplaybackonrv.sample.model.Image
import com.abhijith.videoplaybackonrv.sample.model.Video
import com.abhijith.videoplaybackonrv.sample.util.misc.randomPositiveInt

object VideoProvider {


    private val VIDEO_URLS = arrayListOf(
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
    )


    @JvmStatic fun getVideos(count : Int, mute : Boolean = true) : List<Any> {
        val items = ArrayList<Any>()

        for(i in 0 until count) {
            val index = (i % VIDEO_URLS.size)
            val url = VIDEO_URLS[index]
            items.add(Video(id = randomPositiveInt(), title = (Uri.parse(url).lastPathSegment ?: ""), videoUrl = url, isMuted = mute))
            items.add(Image(id = randomPositiveInt(),path="https://images.pexels.com/photos/1517162/pexels-photo-1517162.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        }

        return items
    }


}
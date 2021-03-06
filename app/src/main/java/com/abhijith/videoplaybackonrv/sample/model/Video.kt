package com.abhijith.videoplaybackonrv.sample.model

data class Video(
    var id : Int,
    var title : String = "",
    var videoUrl : String = "",
    var isMuted : Boolean = false
)

data class Image(
    val id:Int,
    val path:String
)

package com.abhijith.videoplaybackonrv.sample.model

data class Video(
    var id : Int,
    var title : String = "",
    var videoUrl : String = "",
    var isMuted : Boolean = false
)

data class Image(
    var id : Int,
    var title : String = "",
    var videoUrl : String = "",
)
data class RVAdapterData(val type:Type,){

    companion object{
        enum class Type{
            IMAGE,VIDEO
        }
    }
}
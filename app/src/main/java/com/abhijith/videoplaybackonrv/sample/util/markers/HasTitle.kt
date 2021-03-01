package com.abhijith.videoplaybackonrv.sample.util.markers

import android.content.Context

interface HasTitle {
    fun getTitle(context : Context) : CharSequence
}
package com.abhijith.videoplaybackonrv.sample

import android.app.Application
import com.abhijith.videoplaybackonrv.extension.playerProvider

class ArviApplication : Application() {


    companion object {
        @JvmStatic lateinit var INSTANCE : ArviApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


    override fun onTrimMemory(level : Int) {
        super.onTrimMemory(level)
        if(level >= TRIM_MEMORY_BACKGROUND) {
            playerProvider.release()
        }
    }


}
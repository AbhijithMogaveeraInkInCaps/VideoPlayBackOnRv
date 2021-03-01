package com.abhijith.videoplaybackonrv.sample.ui.videos

import android.content.Context
import com.abhijith.videoplaybackonrv.R

enum class Type {


    BASIC {
        override fun getTitle(context : Context) : CharSequence {
            return context.getString(R.string.title_basic_demo)
        }
    },
    ADAPSTER {
        override fun getTitle(context : Context) : CharSequence {
            return context.getString(R.string.title_adapster_demo)
        }
    };


    abstract fun getTitle(context : Context) : CharSequence


}
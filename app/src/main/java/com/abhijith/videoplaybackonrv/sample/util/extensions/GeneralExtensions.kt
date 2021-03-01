package com.abhijith.videoplaybackonrv.sample.util.extensions

import android.content.Context
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.abhijith.videoplaybackonrv.sample.adapters.base.BaseViewPagerAdapter
import com.abhijith.videoplaybackonrv.sample.util.markers.CanManagePlayback
import com.abhijith.videoplaybackonrv.sample.util.markers.HasTitle


fun BaseViewPagerAdapter.startPlaybackIfNecessary() {
    for(i in 0 until this.count) {
        this.getFragment(i)?.ifCanManagePlayback { startPlayback() }
    }
}


fun BaseViewPagerAdapter.stopPlaybackIfNecessary() {
    for(i in 0 until this.count) {
        this.getFragment(i)?.ifCanManagePlayback { stopPlayback() }
    }
}


fun Any.getTitleOrDefault(context : Context, defaultTitle : CharSequence) : CharSequence {
    return (if(this is HasTitle) this.getTitle(context) else defaultTitle)
}


inline fun PagerAdapter.ifFragmentPagerAdapter(action : FragmentPagerAdapter.() -> Unit) {
    if(this is FragmentPagerAdapter) {
        action(this)
    }
}


inline fun Any.ifCanManagePlayback(action : CanManagePlayback.() -> Unit) {
    if(this is CanManagePlayback) {
        action(this)
    }
}
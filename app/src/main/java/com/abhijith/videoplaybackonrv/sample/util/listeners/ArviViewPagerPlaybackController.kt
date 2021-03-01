package com.abhijith.videoplaybackonrv.sample.util.listeners

import androidx.viewpager.widget.ViewPager
import com.abhijith.videoplaybackonrv.sample.util.extensions.ifCanManagePlayback
import com.abhijith.videoplaybackonrv.sample.util.extensions.ifFragmentPagerAdapter

open class ArviViewPagerPlaybackController(private var viewPager : ViewPager?) : OnPageChangeListenerAdapter() {
    override fun onPageScrollStateChanged(state : Int) {
        if((viewPager != null) && (state == ViewPager.SCROLL_STATE_IDLE)) {
            viewPager!!.adapter?.ifFragmentPagerAdapter {
                getItem(viewPager!!.currentItem).ifCanManagePlayback {
                    startPlayback()
                }
            }
        }
    }
}
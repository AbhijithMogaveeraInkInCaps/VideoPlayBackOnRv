package com.abhijith.videoplaybackonrv.sample.adapters.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.abhijith.videoplaybackonrv.sample.ui.base.BaseFragment
import com.abhijith.videoplaybackonrv.sample.util.markers.CanHandleBackPressEvents

abstract class BaseViewPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager),
    CanHandleBackPressEvents {


    protected val fragmentManager : FragmentManager = fragmentManager
    protected val fragmentList = ArrayList<BaseFragment>()

    var pageTitleLookup : ((position : Int) -> CharSequence?)? = null


    fun addFragment(fragment : BaseFragment) {
        fragmentList.add(fragment)
    }


    fun getFragment(position : Int) : BaseFragment? {
        return (if((position >= 0) && (position < fragmentList.size)) fragmentList[position] else null)
    }


    override fun getItem(position : Int) : Fragment {
        return fragmentList[position]
    }


    override fun getItemId(position : Int) : Long {
        return position.toLong()
    }


    override fun getPageTitle(position : Int) : CharSequence? {
        return (pageTitleLookup?.invoke(position) ?: super.getPageTitle(position))
    }


    override fun getCount() : Int {
        return fragmentList.size
    }


    override fun onBackPressed() : Boolean {
        for(fragment in fragmentList) {
            if(fragment.onBackPressed()) {
                return true
            }
        }

        return false
    }


}
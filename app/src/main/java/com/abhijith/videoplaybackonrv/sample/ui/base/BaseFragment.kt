package com.abhijith.videoplaybackonrv.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.abhijith.videoplaybackonrv.sample.util.markers.CanHandleBackPressEvents

abstract class BaseFragment : Fragment(), CanHandleBackPressEvents {

    lateinit var rootView : View
        private set

    var isViewCreated = false
        private set


    final override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let(::fetchExtras)
        preInit()
    }


    override fun onCreateView(inflater : LayoutInflater,
                              container : ViewGroup?,
                              savedInstanceState : Bundle?) : View? {
        return inflater.inflate(
            getLayoutId(),
            container,
            false
        ).also {
            rootView = it
            isViewCreated = true
        }
    }


    final override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        postInit()
    }


    @CallSuper
    protected open fun fetchExtras(extras : Bundle) {
        //
    }


    protected open fun preInit() {
        //
    }


    protected open fun init(savedInstanceState : Bundle?) {
        //
    }


    protected open fun postInit() {
        //
    }


    final override fun setUserVisibleHint(isVisibleToUser : Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser) {
            onBecameVisibleToUser()
        } else {
            onBecameInvisibleToUser()
        }
    }


    protected open fun onBecameVisibleToUser() {
        //
    }


    protected open fun onBecameInvisibleToUser() {
        //
    }


    @CallSuper
    override fun onBackPressed() : Boolean {
        return false
    }


    final override fun onDestroy() {
        onRecycle()
        super.onDestroy()
    }


    protected open fun onRecycle() {
        //
    }


    @LayoutRes
    protected abstract fun getLayoutId() : Int


}
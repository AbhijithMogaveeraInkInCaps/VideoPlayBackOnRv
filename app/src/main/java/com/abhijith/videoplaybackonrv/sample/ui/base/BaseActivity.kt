package com.abhijith.videoplaybackonrv.sample.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    final override fun onCreate(savedInstanceState : Bundle?) {
        intent?.extras?.let(::fetchExtras)
        preInit()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
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


    final override fun onDestroy() {
        onRecycle()
        super.onDestroy()
    }


    protected open fun onRecycle() {
        //
    }

    @LayoutRes
    abstract fun getLayoutId() : Int
}
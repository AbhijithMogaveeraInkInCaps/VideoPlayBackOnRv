package com.abhijith.videoplaybackonrv.sample.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.sample.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun init(savedInstanceState : Bundle?) {
        findViewById<RecyclerView>(R.id.rv)?.apply {
            adapter = RVAdapter()
        }
    }

    override fun getLayoutId() : Int {
        return R.layout.activity_main
    }
}

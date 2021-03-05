package com.abhijith.videoplaybackonrv.sample.ui.main

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyRV(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    private var isScrollStarted = false
    private val attachedCandidateList = mutableListOf<RVAdapter.VH>()
    override fun onChildAttachedToWindow(child: View) {
        super.onChildAttachedToWindow(child)
        attachedCandidateList.add(getChildViewHolder(child) as RVAdapter.VH)
        if (!isScrollStarted) {
            attachedCandidateList.forEach {
                if (it.adapterPosition == 0) {
                    Log.e("ARRVI", "Attachedwin ${it.adapterPosition}")
                    it.action(ExtensionInfo(SelectiveAction.ATTACHED_WIN))
                } else {
                    Log.e("ARRVI", "Attachedlost ${it.adapterPosition}")
                    it.action(ExtensionInfo(SelectiveAction.DETACHED))
                }
            }
        }
    }

    fun mute(boolean: Boolean) {
        attachedCandidateList.forEach {
            if (boolean)
                it.action(ExtensionInfo(SelectiveAction.MUTE))
            else
                it.action(ExtensionInfo(SelectiveAction.UN_MUTE))
        }
    }

    override fun onChildDetachedFromWindow(child: View) {
        super.onChildDetachedFromWindow(child)
        attachedCandidateList.remove(getChildViewHolder(child) as RVAdapter.VH)
    }

    private var isScrolledDown = false

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        isScrolledDown = dy < 0

    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        isScrollStarted = true
        val linearLayoutManager = layoutManager as LinearLayoutManager
        val visibleItemPosition: Int = if (!isScrolledDown) {
            if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == -1 && state == SCROLL_STATE_IDLE)
                linearLayoutManager.findLastVisibleItemPosition()
            else
                linearLayoutManager.findLastCompletelyVisibleItemPosition()
        } else {
            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == -1 && state == SCROLL_STATE_IDLE)
                linearLayoutManager.findFirstVisibleItemPosition()
            else
                linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        }
        when (state) {
            SCROLL_STATE_IDLE -> {
                attachedCandidateList.forEach {
                    it.action(
                        if (visibleItemPosition == it.pos)
                            ExtensionInfo(SelectiveAction.ATTACHED_WIN)
                        else
                            ExtensionInfo(SelectiveAction.ATTACHED_LOST)
                    )
                }
            }
            SCROLL_STATE_DRAGGING -> {
            }
            SCROLL_STATE_SETTLING -> {
            }
        }
    }
}
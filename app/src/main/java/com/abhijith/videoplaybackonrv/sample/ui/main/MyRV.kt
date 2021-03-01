package com.abhijith.videoplaybackonrv.sample.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyRV(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    val attachedCandidateList = mutableListOf<RVAdapter.VH>()
    override fun onChildAttachedToWindow(child: View) {
        super.onChildAttachedToWindow(child)
        attachedCandidateList.add(getChildViewHolder(child) as RVAdapter.VH)
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
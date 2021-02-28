package com.abhijith.videoplaybackonrv.sample.adapters.adapster

import android.view.View
import android.widget.TextView
import com.abhijith.videoplaybackonrv.model.BaseItem
import com.abhijith.videoplaybackonrv.R

class TextItemViewHolder(
    itemView : View
) : BaseItem.ViewHolder<String>(itemView) {

    val textTv = itemView.findViewById<TextView>(R.id.textTv)

    override fun bindData(data : String?) {
        super.bindData(data)
        data?.let(::handleData)
    }

    private fun handleData(data : String) {
        textTv.text = data
    }
}
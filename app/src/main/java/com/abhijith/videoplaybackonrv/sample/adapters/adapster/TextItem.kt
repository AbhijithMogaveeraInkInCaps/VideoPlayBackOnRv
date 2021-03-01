package com.abhijith.videoplaybackonrv.sample.adapters.adapster

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.adapter.Adapter
import com.abhijith.videoplaybackonrv.markers.ItemResources
import com.abhijith.videoplaybackonrv.model.BaseItem
import com.abhijith.videoplaybackonrv.model.Item
import com.abhijith.videoplaybackonrv.R

class TextItem(itemModel : String) : BaseItem<String, TextItemViewHolder, ItemResources>(itemModel) {


    override fun init(adapter : Adapter<out Item<RecyclerView.ViewHolder, ItemResources>>?,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : TextItemViewHolder {
        return TextItemViewHolder(inflater.inflate(
            layout,
            parent,
            false
        ))
    }


    override fun getLayout() : Int {
        return R.layout.item_text

    }


}

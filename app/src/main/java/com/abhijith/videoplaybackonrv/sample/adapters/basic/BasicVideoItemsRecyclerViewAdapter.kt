package com.abhijith.videoplaybackonrv.sample.adapters.basic

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.others.Config
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.sample.model.Video

class BasicVideoItemsRecyclerViewAdapter(
    context : Context,
    private val items : MutableList<Video>,
    private val arviConfig : Config
) : RecyclerView.Adapter<BasicVideoItemViewHolder>() {


    private val inflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : BasicVideoItemViewHolder {
        return BasicVideoItemViewHolder(
            parent = parent,
            itemView = inflater.inflate(
                R.layout.item_video,
                parent,
                false
            ),
            arviConfig = arviConfig
        )
    }


    override fun onBindViewHolder(holder : BasicVideoItemViewHolder, position : Int) {
        holder.bindData(getItem(position))
    }


    fun getItem(position : Int) : Video? {
        return (if((position >= 0) && (position < itemCount)) items[position] else null)
    }


    override fun getItemCount() : Int {
        return items.size
    }


}
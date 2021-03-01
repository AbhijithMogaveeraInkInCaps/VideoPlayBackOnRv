package com.abhijith.videoplaybackonrv.sample.adapters.basic

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.others.Config
import com.abhijith.videoplaybackonrv.sample.model.Image
import com.abhijith.videoplaybackonrv.sample.model.Video

class BasicVideoItemsRecyclerViewAdapter(
    context: Context,
    private val items: MutableList<Any>,
    private val arviConfig: Config
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return if(items[position] is Image)
            ImageViewType
        else
            VideoViewType
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ImageViewType -> {
                BasicImageItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
                )
            }
            VideoViewType -> {
                BasicVideoItemViewHolder(
                    parent = parent,
                    itemView = inflater.inflate(
                        R.layout.item_video,
                        parent,
                        false
                    ),
                    arviConfig = arviConfig
                )
            }
            else -> throw Exception("Unsupported data type")
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.runWhenVideo {
            it.bindData(getItem(position) as Video)
        }
        holder.runWhenImage {
            it.bindData(getItem(position) as Image)
        }
    }


    fun getItem(position: Int): Any? {
        return (if ((position >= 0) && (position < itemCount)) items[position] else null)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    companion object {
        const val ImageViewType = 0
        const val VideoViewType = 1
    }
}

fun RecyclerView.ViewHolder.runWhenVideo(onTrue: (BasicVideoItemViewHolder) -> Unit) {
    if (this is BasicVideoItemViewHolder) {
        onTrue(this)
    }
}

fun RecyclerView.ViewHolder.runWhenImage(onTrue: (BasicImageItemViewHolder) -> Unit) {
    if (this is BasicImageItemViewHolder) {
        onTrue(this)
    }
}
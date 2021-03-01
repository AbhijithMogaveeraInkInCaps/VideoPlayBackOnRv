package com.abhijith.videoplaybackonrv.sample.adapters.basic

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.R
import com.abhijith.videoplaybackonrv.sample.model.Image
import com.abhijith.videoplaybackonrv.sample.model.Video
import com.bumptech.glide.Glide

class BasicImageItemViewHolder(v: View):RecyclerView.ViewHolder(v) {
    fun bindData(item: Image) {
        Glide.with(imageView).load(item.path).into(imageView)
    }

    val imageView: ImageView = v.findViewById(R.id.image_view)
}


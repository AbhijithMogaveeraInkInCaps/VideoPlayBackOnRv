package com.abhijith.videoplaybackonrv.model.markers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijith.videoplaybackonrv.listeners.OnItemClickListener;

/**
 * To be implemented by the Items that need to be treated as Footer.
 *
 * @param <VH> the view holder type
 */
public interface Footer<VH extends RecyclerView.ViewHolder> {

    /**
     * Sets the click listener responsible for handling the Footer Item click events.
     *
     * @param viewHolder
     * @param onItemClickListener
     */
    void setOnItemClickListener(@NonNull VH viewHolder, @Nullable OnItemClickListener<Footer<VH>> onItemClickListener);

}
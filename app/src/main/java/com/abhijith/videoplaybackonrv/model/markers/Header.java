package com.abhijith.videoplaybackonrv.model.markers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijith.videoplaybackonrv.listeners.OnItemClickListener;

/**
 * To be implemented by the Items that need to be treated as Header.
 *
 * @param <VH> the view holder type
 * @author arthur3486
 */
public interface Header<VH extends RecyclerView.ViewHolder> {

    /**
     * Sets the click listener responsible for handling the Header Item click events.
     *
     * @param viewHolder
     * @param onItemClickListener
     */
    void setOnItemClickListener(@NonNull VH viewHolder, @Nullable OnItemClickListener<Header<VH>> onItemClickListener);

}
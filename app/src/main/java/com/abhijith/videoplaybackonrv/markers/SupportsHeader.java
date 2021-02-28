package com.abhijith.videoplaybackonrv.markers;

import com.abhijith.videoplaybackonrv.listeners.OnItemClickListener;
import com.abhijith.videoplaybackonrv.model.markers.Header;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A marker interface which is to be implemented by the
 * adapter to provide the Header Item support.
 *
 * @param <VH> View Holder
 *
 * @author arthur3486
 */
public interface SupportsHeader<VH extends RecyclerView.ViewHolder> {

    /**
     * Adds the Header Item to the dataset.
     *
     * @param header the Header Item to be added to the dataset
     */
    <VHT extends VH> void addHeader(@NonNull Header<VHT> header);

    /**
     * Removes the Header Item from the dataset (if there's any).
     */
    void removeHeader();

    /**
     * Sets the Header Item click listener.
     *
     * @param onHeaderClickListener the listener to be set
     */
    void setOnHeaderClickListener(@Nullable OnItemClickListener<Header<VH>> onHeaderClickListener);

}

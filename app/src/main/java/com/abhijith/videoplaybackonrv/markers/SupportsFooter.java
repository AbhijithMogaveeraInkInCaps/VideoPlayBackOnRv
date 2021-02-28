package com.abhijith.videoplaybackonrv.markers;

import com.abhijith.videoplaybackonrv.listeners.OnItemClickListener;
import com.abhijith.videoplaybackonrv.model.markers.Footer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A marker interface which is to be implemented by the
 * adapter to provide the Footer Item support.
 *
 * @param <VH> View Holder
 *
 * @author arthur3486
 */
public interface SupportsFooter<VH extends RecyclerView.ViewHolder> {

    /**
     * Adds the Footer Item to the dataset.
     *
     * @param footer the Footer Item to be added to the dataset
     */
    <VHT extends VH> void addFooter(@NonNull Footer<VHT> footer);

    /**
     * Removes the Footer Item from the dataset (if there's any).
     */
    void removeFooter();

    /**
     * Sets the Footer Item click listener.
     *
     * @param onFooterClickListener the listener to be set
     */
    void setOnFooterClickListener(@Nullable OnItemClickListener<Footer<VH>> onFooterClickListener);

}

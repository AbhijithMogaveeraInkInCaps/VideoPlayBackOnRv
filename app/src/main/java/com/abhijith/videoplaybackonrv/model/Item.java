package com.abhijith.videoplaybackonrv.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.abhijith.videoplaybackonrv.adapter.Adapter;
import com.abhijith.videoplaybackonrv.markers.ItemResources;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A base contract-interface to be implemented by the dataset item.
 *
 * @param <VH> item view holder type
 * @param <IR> reusable resources type
 */
public interface Item<VH extends RecyclerView.ViewHolder, IR extends ItemResources> extends Serializable {

    int VIEW_TYPE_INVALID = -1;


    /**
     * Initializes the Item View Holder.
     *
     * @param adapter the adapter
     * @param parent parent view
     * @param inflater layout inflater
     * @param resources reusable resources
     * @return the created Item View Holder
     */
    @NonNull
    VH init(@Nullable Adapter<? extends Item> adapter,
            @NonNull ViewGroup parent,
            @NonNull LayoutInflater inflater,
            @Nullable IR resources);


    /**
     * Binds the data.
     *
     * @param adapter the adapter
     * @param viewHolder item view holder
     * @param resources reusable resources
     */
    void bind(@Nullable Adapter<? extends Item> adapter,
              @NonNull VH viewHolder,
              @Nullable IR resources);


    /**
     * @return the Layout Id belonging to this particular Item.
     *      (A Unique ID used to identify the View Type of this Item)
     */
    int getLayout();


}
package com.abhijith.videoplaybackonrv.listeners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * A convenience abstract class which implements the methods of the {@link OnDatasetChangeListener}.
 *
 * @param <DS> dataset type
 * @param <IT> item type
 * @author arthur3486
 */
public abstract class DatasetChangeListenerAdapter<DS extends List<IT>, IT> implements OnDatasetChangeListener<DS, IT> {




    @Override
    public void onItemAdded(@NonNull DS dataset, @Nullable IT item) {

    }




    @Override
    public void onItemUpdated(@NonNull DS dataset, @Nullable IT item) {

    }




    @Override
    public void onItemReplaced(@NonNull DS dataset, @Nullable IT oldItem, @Nullable IT newItem) {

    }




    @Override
    public void onItemDeleted(@NonNull DS dataset, @Nullable IT item) {

    }




    @Override
    public void onDatasetSizeChanged(int oldSize, int newSize) {

    }




    @Override
    public void onDatasetReplaced(@NonNull DS newDataset) {

    }




    @Override
    public void onDatasetCleared(@NonNull DS dataset) {

    }




}

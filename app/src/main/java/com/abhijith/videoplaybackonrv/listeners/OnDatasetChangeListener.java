package com.abhijith.videoplaybackonrv.listeners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * A contract-interface for the observation of the dataset-related events.
 *
 * @param <DS> dataset type
 * @param <IT> item type
 */
public interface OnDatasetChangeListener<DS extends List<IT>, IT> {

    /**
     * Called when the new item is added to the dataset.
     *
     * @param dataset the dataset
     * @param item the added item
     */
    void onItemAdded(@NonNull DS dataset, @Nullable IT item);

    /**
     * Called when the item, contained by the dataset, is updated.
     *
     * @param dataset the dataset
     * @param item the updated item
     */
    void onItemUpdated(@NonNull DS dataset, @Nullable IT item);

    /**
     * Called when the item, contained by the dataset, is replaced with a new one.
     *
     * @param dataset the dataset
     * @param oldItem the old item (replaced)
     * @param newItem the new item (replacement)
     */
    void onItemReplaced(@NonNull DS dataset, @Nullable IT oldItem, @Nullable IT newItem);

    /**
     * Called when the item is deleted from the dataset.
     *
     * @param dataset the dataset
     * @param item the deleted item
     */
    void onItemDeleted(@NonNull DS dataset, @Nullable IT item);

    /**
     * Called when the interaction with the Dataset resulted in the changes of its size.
     *
     * @param oldSize the size of the dataset before the modification
     * @param newSize the size of the dataset after the modification
     */
    void onDatasetSizeChanged(int oldSize, int newSize);

    /**
     * Called when the dataset is replaced with a new one.
     *
     * @param newDataset the new dataset (replacement)
     */
    void onDatasetReplaced(@NonNull DS newDataset);

    /**
     * Called when the dataset is cleared.
     *
     * @param dataset the cleared dataset
     */
    void onDatasetCleared(@NonNull DS dataset);

}

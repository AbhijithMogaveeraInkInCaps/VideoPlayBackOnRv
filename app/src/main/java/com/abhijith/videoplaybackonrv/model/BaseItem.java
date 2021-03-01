package com.abhijith.videoplaybackonrv.model;

import android.view.View;

import com.abhijith.videoplaybackonrv.adapter.Adapter;
import com.abhijith.videoplaybackonrv.markers.ItemResources;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * An abstract class for the Dataset Item implementation.
 *
 * @param <IM> item model type
 * @param <VH> view holder type
 * @param <IR> reusable resources type
 */
public abstract class BaseItem<IM, VH extends BaseItem.ViewHolder<IM>, IR extends ItemResources> implements Item<VH, IR> {


    public static final long NO_ID = -1L;


    private IM mItemModel;
    private Object mTag;




    public BaseItem(IM itemModel) {
        mItemModel = itemModel;
    }




    @CallSuper
    @Override
    public void bind(@Nullable Adapter<? extends Item> adapter,
                     @NonNull VH viewHolder,
                     @Nullable IR resources) {
        viewHolder.bindData(getItemModel());
    }




    public long getId() {
        return NO_ID;
    }




    public final void setItemModel(IM itemModel) {
        mItemModel = itemModel;
    }




    public final IM getItemModel() {
        return mItemModel;
    }




    public final BaseItem setTag(Object tag) {
        mTag = tag;
        return this;
    }




    public final Object getTag() {
        return mTag;
    }




    public final boolean hasTag() {
        return (mTag != null);
    }




    @Override
    public int hashCode() {
        return ((getItemModel() != null) ? getItemModel().hashCode() : super.hashCode());
    }




    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof BaseItem) && (obj.hashCode() == hashCode()));
    }




    /**
     * An abstract Item ViewHolder class that's to be implemented by every concrete Item View Holder implementation.
     *
     * @param <Data> the type of the data represented by the host Item.
     */
    public abstract static class ViewHolder<Data> extends RecyclerView.ViewHolder {

        private Data mBoundData;


        public ViewHolder(View itemView) {
            super(itemView);
        }


        /**
         * Binds the data to the UI.
         *
         * @param data the data item to base the binding on
         */
        @CallSuper
        public void bindData(Data data) {
            mBoundData = data;
        }


        /**
         * Retrieves the data item associated with this View Holder.
         *
         * @return the associated data item
         */
        public Data getBoundData() {
            return mBoundData;
        }


    }




}
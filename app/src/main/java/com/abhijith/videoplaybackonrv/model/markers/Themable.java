package com.abhijith.videoplaybackonrv.model.markers;

/**
 * A marker interface that's to be implemented by the Item's View Holder, to enable its theming.
 *
 * @param <T> Theme item type
 * @author arthur3486
 */
public interface Themable<T> {

    /**
     * Applies the theme to the Item View Holder.
     *
     * @param theme the theme to be applied
     */
    void applyTheme(T theme);

}
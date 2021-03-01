package com.abhijith.videoplaybackonrv.sample.util.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use


@SuppressLint("Recycle")
fun Context.extractStyledAttributes(attributes : AttributeSet, attrs : IntArray,
                                    block : TypedArray.() -> Unit) {
    obtainStyledAttributes(attributes, attrs, 0, 0).use {
        block(it)
    }
}


fun Context.getColorCompat(@ColorRes colorResourceId : Int) : Int {
    return ContextCompat.getColor(this, colorResourceId)
}


fun Context.getColoredDrawable(@DrawableRes drawableResourceId : Int, @ColorInt color : Int) : Drawable? {
    return ContextCompat.getDrawable(this, drawableResourceId)?.applyColor(color)
}
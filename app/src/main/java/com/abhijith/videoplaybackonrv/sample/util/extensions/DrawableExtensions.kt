package com.abhijith.videoplaybackonrv.sample.util.extensions

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

fun Drawable.applyColor(@ColorInt color : Int, mode : PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP) : Drawable {
    return this.mutate().let {
        it.setColorFilter(color, mode)
        return@let it
    }
}

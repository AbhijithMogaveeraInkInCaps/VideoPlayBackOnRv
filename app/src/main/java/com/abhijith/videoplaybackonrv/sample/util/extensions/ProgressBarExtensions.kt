package com.abhijith.videoplaybackonrv.sample.util.extensions

import android.widget.ProgressBar
import androidx.annotation.ColorInt
import com.abhijith.videoplaybackonrv.sample.util.extensions.applyColor


fun ProgressBar.setColor(@ColorInt color : Int) {
    this.indeterminateDrawable = this.indeterminateDrawable?.applyColor(color)
}

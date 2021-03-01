package com.abhijith.videoplaybackonrv.sample.util.extensions

import android.view.View
import androidx.annotation.ColorRes
import com.abhijith.videoplaybackonrv.sample.util.extensions.getColorCompat


fun View.enable() {
    isEnabled = true
}


fun View.disable() {
    isEnabled = false
}


fun View.makeVisible() {
    this.visibility = View.VISIBLE
}


fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}


fun View.makeGone() {
    this.visibility = View.GONE
}


/**
 * @isVisible sets the View visibility to [View.VISIBLE] if true, [View.GONE] if false.
 */
fun View.setVisible(isVisible : Boolean) {
    this.visibility = (if(isVisible) View.VISIBLE else View.GONE)
}


fun View.getColor(@ColorRes colorResourceId : Int) : Int {
    return this.context.getColorCompat(colorResourceId)
}
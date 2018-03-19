package com.jordansilva.dailyeat.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by jordansilva on 18/03/18.
 */
fun ImageView.loadUrl(url: String) {
    Picasso.get().load(url).into(this)
}

fun ImageView.loadUrlCenterCrop(url: String) {
    Picasso.get().setIndicatorsEnabled(true)
    Picasso.get().load(url).fit().centerCrop().into(this)
}
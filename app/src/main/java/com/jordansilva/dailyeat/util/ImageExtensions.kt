package com.jordansilva.dailyeat.util

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.lang.Exception


/**
 * Created by jordansilva on 18/03/18.
 */
fun ImageView.loadUrl(imageUrl: String) {
    Picasso.get().load(imageUrl).into(this)
}

fun ImageView.loadUrlWithTransformation(imageUrl: String, transformation: Transformation) {
    Picasso.get().load(imageUrl).fit().centerCrop().transform(transformation).into(this)
}

fun ImageView.loadUrlCenterCrop(imageUrl: String) {
    Picasso.get().load(imageUrl).fit().centerCrop().into(this)
}

fun ImageView.loadUrl(imageUrl: String,
                      successCallback: () -> Unit,
                      errorCallback: (e: Exception?) -> Unit) {
    Picasso.get().load(imageUrl).into(this, object : Callback {
        override fun onError(e: Exception?) {
            errorCallback(e)
        }

        override fun onSuccess() {
            successCallback()
        }
    })
}
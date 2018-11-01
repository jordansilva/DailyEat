package com.jordansilva.dailyeat.util

import android.support.design.widget.AppBarLayout

/**
 * Created by jordansilva on 19/03/18.
 */


fun AppBarLayout.collapsed(f: () -> Unit) {
    this.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                run {
                    if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange)
                        f()
                }
            })
}

fun AppBarLayout.expanded(f: () -> Unit) {
    this.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                run {
                    if (Math.abs(verticalOffset) == 0)
                        f()
                }
            }
    )
}

fun AppBarLayout.sliding(f: () -> Unit) {
    this.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                run {
                    if (!arrayOf(appBarLayout.totalScrollRange, 0)
                                    .contains(Math.abs(verticalOffset)))
                        f()
                }
            }
    )
}
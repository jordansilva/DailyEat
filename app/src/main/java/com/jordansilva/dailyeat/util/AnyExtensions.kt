package com.jordansilva.dailyeat.util

import android.content.res.Resources
import android.text.format.DateUtils
import java.util.*

/**
 * Created by jordansilva on 15/03/18.
 */
inline fun <T : Any, R> whenNotNull(input: T?, callback: (T) -> R): R? {
    return input?.let(callback)
}

inline fun <T, reified R> List<T>.mapToTypedArray(transform: (T) -> R): Array<R> {
    return when (this) {
        is RandomAccess -> Array(size) { index -> transform(this[index]) }
        else -> with(iterator()) { Array(size) { transform(next()) } }
    }
}

fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

val Int.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Date.relativeTime: String
    get() = DateUtils.getRelativeTimeSpanString(this.time, Calendar.getInstance(Locale.getDefault()).timeInMillis, 0).toString()

fun Number.format(format: String): String {
    return String.format(Locale.getDefault(), format, this)
}

fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null
fun <E> List<E>.random(random: java.util.Random): E? = if (size > 0) get(random.nextInt(size)) else null

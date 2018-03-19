package unimedbh.app.prestador.util

import android.content.res.Resources
import android.text.format.DateUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jordansilva.dailyeat.data.model.BaseModel
import java.util.*

/**
 * Created by jordansilva on 15/03/18.
 */
inline fun <T : Any, R> whenNotNull(input: T?, callback: (T) -> R): R? {
    return input?.let(callback)
}

fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

fun <T : BaseModel> toJson(input: List<T>?, klass: Class<T>): String? {

    input.let {
        val gson = Gson()
        val type = TypeToken.getParameterized(List::class.java, klass.javaClass).type
        return gson.toJson(input, type)
    }
}

fun <T : BaseModel> fromJson(input: String, klass: Class<T>): List<T> {
    val gson = Gson()
    val type = TypeToken.getParameterized(List::class.java, klass.javaClass).type
    return gson.fromJson(input, type)
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

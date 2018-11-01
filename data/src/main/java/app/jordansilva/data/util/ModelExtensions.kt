package app.jordansilva.data.util

import app.jordansilva.data.model.BaseModel
import app.jordansilva.data.repository.remote.GeneralApiException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.io.IOException


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

suspend fun <R> Deferred<R>.awaitWithParseError(): R {
    try {
        return await()
    } catch (throwable: Exception) {

        if (throwable is HttpException) {

            val gson = Gson()
            try {
                val generalApiException = gson.fromJson(throwable.response().errorBody()!!.string(), GeneralApiException::class.java)
                throw generalApiException//invokes observable onError
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        // if not the kind we're interested in, then just report the same error to onError
        throw throwable//invokes observable onError
    }
}
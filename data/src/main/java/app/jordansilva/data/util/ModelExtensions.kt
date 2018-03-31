package app.jordansilva.data.util

import app.jordansilva.data.model.BaseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


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
package app.jordansilva.data.repository.remote

import retrofit2.Response

class GeneralApiException(@field:Transient private val response: Response<*>) : Exception() {

    var errors: String? = null

    /**
     * HTTP status code.
     */
    fun code(): Int {
        return response.code()
    }

    /**
     * HTTP status message.
     */
    fun message(): String? {
        return message
    }

    /**
     * The full HTTP response. This may be null if the exception was serialized.
     */
    fun response(): Response<*> {
        return response
    }
}
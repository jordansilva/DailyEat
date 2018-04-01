package app.jordansilva.data.repository.remote

import app.jordansilva.domain.model.Feed
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface ApiFeedService {

    @GET("./")
    fun getUserFeeds(): Deferred<List<Feed>>

}
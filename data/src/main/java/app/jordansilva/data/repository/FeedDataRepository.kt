package app.jordansilva.data.repository

import app.jordansilva.data.repository.remote.ApiFeedService
import app.jordansilva.data.util.awaitWithParseError
import app.jordansilva.domain.model.Feed
import app.jordansilva.domain.repository.FeedRepository

class FeedDataRepository(private val api : ApiFeedService) : FeedRepository {

    override suspend fun getUserFeeds(): List<Feed> {
        return api.getUserFeeds().awaitWithParseError()
    }

    override suspend fun favouriteFeed(feedId: Feed): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun unFavouriteFeed(feedId: String): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
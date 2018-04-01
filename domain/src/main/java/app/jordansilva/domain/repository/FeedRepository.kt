package app.jordansilva.domain.repository

import app.jordansilva.domain.model.Feed

interface FeedRepository {

    suspend fun getUserFeeds(): List<Feed>

    suspend fun favouriteFeed(feedId: Feed): Void

    suspend fun unFavouriteFeed(feedId: String): Void
}
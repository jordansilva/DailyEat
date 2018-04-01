package app.jordansilva.domain.repository

import app.jordansilva.domain.model.Feed

interface FeedRepository {

    fun getUserFeeds(): List<Feed>

    fun favouriteFeed(feedId: Feed): Void

    fun unFavouriteFeed(feedId: String): Void
}
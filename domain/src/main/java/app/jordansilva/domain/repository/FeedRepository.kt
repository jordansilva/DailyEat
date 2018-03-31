package app.jordansilva.domain.repository

import app.jordansilva.domain.model.Feed
import io.reactivex.Completable
import io.reactivex.Single

interface FeedRepository {

    fun getUserFeeds(): Single<List<Feed>>

    fun favouriteFeed(feedId: Feed): Completable

    fun unFavouriteFeed(feedId: String): Completable
}
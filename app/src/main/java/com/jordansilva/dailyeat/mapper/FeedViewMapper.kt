package com.jordansilva.dailyeat.mapper

import app.jordansilva.domain.model.Feed
import com.jordansilva.dailyeat.model.FeedView

class FeedViewMapper : MapperView<FeedView, Feed> {

    override fun mapToView(type: Feed): FeedView {
        return FeedView(type.id.toString(), type.recipeId.toString(),
                type.name, type.description, type.imageUrl,
                type.authorId.toString(), type.authorName, type.authorAvatar,
                type.created).apply {

            amountLikes = type.amountLikes
            amountCooked = type.amountCooked

            cooked = type.cooked
            liked = type.liked
            saved = type.saved
        }
    }

}
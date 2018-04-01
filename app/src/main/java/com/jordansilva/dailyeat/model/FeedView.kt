package com.jordansilva.dailyeat.model

import java.util.*

data class FeedView(val id : String, val recipeId: String, val name: String, val description: String, val imageUrl: String,
                    val authorId: String, val authorName: String, val authorAvatar: String,
                    val created: Date) {

    var amountLikes: Int = 0
    var amountCooked: Int = 0

    var cooked: Boolean = false
    var liked: Boolean = false
    var saved: Boolean = false
}

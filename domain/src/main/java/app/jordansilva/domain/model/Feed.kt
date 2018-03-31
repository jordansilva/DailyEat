package app.jordansilva.domain.model

import java.util.*

data class Feed(val id: UUID, val name: String, val description: String, val imageUrl: String,
                val authorId: UUID, var authorName: String, var authorAvatar: String,
                var created: Date) {

    //stats
    var amountLikes: Int = 0
    var amountCooked: Int = 0

    var cooked: Boolean = false
    var liked: Boolean = false
    var saved: Boolean = false
}
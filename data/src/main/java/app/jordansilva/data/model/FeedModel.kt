package app.jordansilva.data.model

import java.util.*

data class FeedModel(val id: String, val recipeId: UUID,
                var name: String, var description: String, var imageUrl: String,
                val authorId: UUID, var authorName: String, var authorAvatar: String,
                var created: Date) : BaseModel() {

    //stats
    var amountLikes: Int = 0
    var amountCooked: Int = 0

    var cooked: Boolean = false
    var liked: Boolean = false
    var saved: Boolean = false

}
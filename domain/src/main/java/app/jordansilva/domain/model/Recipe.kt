package app.jordansilva.domain.model

data class Recipe(val id: String,
                  var name: String,
                  var description: String,
                  var imageUrl: String,
                  var userId: String,
                  var ingredients: List<RecipeIngredient>? = null) {

    var userName: String = ""
    var userAvatar: String? = null
    var tags: List<String>? = null

    //Stats
    var rating: Float = 0f

    var cooked: Boolean = false
    var liked: Boolean = false
    var rated: Boolean = false
    var favourited: Boolean = false

    var amountRatings: Int = 0
    var amountLikes: Int = 0
    var amountCooked: Int = 0

}
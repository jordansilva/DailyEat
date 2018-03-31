package app.jordansilva.domain.model

data class Recipe(val id: String,
                  var name: String,
                  var description: String,
                  var user: User,
                  var ingredients: List<RecipeIngredient>? = null) {

    var tags: List<String>? = null

    //Stats
    var rating: Float = 0f
    var amountRatings: Int = 0

    var cooked: Boolean = false
    var liked: Boolean = false
    var rated: Boolean = false
    var favourited: Boolean = false

}
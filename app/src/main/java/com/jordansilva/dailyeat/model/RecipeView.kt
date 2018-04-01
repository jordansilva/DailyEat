package com.jordansilva.dailyeat.model

data class RecipeView(val id: String,
                      var name: String,
                      var description: String,
                      var imageUrl: String,
                      var authorId: String,
                      var authorAvatar: String,
                      var authorName: String,
                      var ingredients: List<RecipeIngredientView>? = null) {

    var tags: List<String>? = null

    //Stats
    var rating: Float = 0f
    var amountRatings: Int = 0

    var cooked: Boolean = false
    var liked: Boolean = false
    var rated: Boolean = false
    var favourited: Boolean = false

}
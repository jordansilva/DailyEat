package com.jordansilva.dailyeat

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun createDataset() {
//        val gson = KoinModule.makeGson()
//
//        val dir = "/Users/jordansilva/Documents/Projects/DailyEat/util/"
//        val jsonData = ArrayList(Mock.mockList())
//        val json = gson.toJson(jsonData)
//
//        var file = File(dir + "feed.json")
//        file.delete()
//        file.createNewFile()
//        file.writeText(json)
//
//        println( "feed.json created!")
//
//        jsonData.map {
//            val recipe = Recipe(it.id, name = it.name, description = it.description, imageUrl = it.imageUrl,
//                    userId = it.authorId, ingredients = null).apply {
//                favourited = it.saved
//                liked = false
//                cooked = false
//                rated = false
//
//                rating = Random().nextFloat() * 5f
//                tags = arrayListOf("Vegan", "Italian", "Brazilian")
//
//                amountRatings = Random().nextInt(500)
//                amountLikes = 0
//                amountCooked = 0
//            }
//
//            var recipeJson: String = gson.toJson(recipe)
//
//            val filename = dir + "recipe/${it.recipeId}.json"
//            file = File(filename)
//            file.delete()
//            file.createNewFile()
//            file.writeText(recipeJson)
//            println( "$filename created!")
//
//        }
//    }
}

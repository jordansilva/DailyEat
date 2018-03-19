package com.jordansilva.dailyeat.data.model

import java.util.*

/**
 * Created by jordansilva on 18/03/18.
 */
data class DashboardPost(var id: UUID,
                         var name: String,
                         var description: String,
                         var imageUrl: String,
                         var authorId: UUID,
                         var author: String,
                         var avatar: String,
                         var date: Date) : BaseModel() {

    //stats
    var likesAmount: Float = 0f
    var cookedAmount: Float = 0f
    var rateAmount: Float = 0f

    var cooked: Boolean = false
    var liked: Boolean = false
    var rated: Boolean = false
}
package com.jordansilva.dailyeat.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by jordansilva on 18/03/18.
 */
@Entity(tableName = "users")
data class User(@PrimaryKey var id: String,
                var login: String = "",
                var password: String = "") : BaseModel() {

    var name: String = ""
    var avatar: ByteArray? = null
}
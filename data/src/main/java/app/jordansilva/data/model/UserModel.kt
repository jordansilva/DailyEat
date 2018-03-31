package app.jordansilva.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by jordansilva on 18/03/18.
 */
@Entity(tableName = "users")
data class UserModel(@PrimaryKey var id: String, val login: String, var password: String) : BaseModel() {
    var name: String = ""
    var avatar: String? = null
}
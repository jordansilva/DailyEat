package app.jordansilva.domain.repository

import app.jordansilva.domain.model.User

interface UserRepository {
    fun getUser(userId: String): User
    fun saveUser(user: User): Void
    fun updateUser(user: User): Void
    fun deleteUserById(userId: String): Void
    fun deleteAllUsers(): Void
}
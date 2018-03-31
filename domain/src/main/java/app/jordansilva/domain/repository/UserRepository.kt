package app.jordansilva.domain.repository

import app.jordansilva.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    fun getUser(userId: String): Single<User>
    fun saveUser(user: User): Completable
    fun updateUser(user: User): Completable
    fun deleteUserById(userId: String): Completable
    fun deleteAllUsers(): Completable
}
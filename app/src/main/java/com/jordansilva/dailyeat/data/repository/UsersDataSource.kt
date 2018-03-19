package com.jordansilva.dailyeat.data.repository

import com.jordansilva.dailyeat.data.model.User

/**
 * Created by jordansilva on 18/03/18.
 */
interface UsersDataSource {

    interface LoadUsersCallback {

        fun onUsersLoaded(tasks: List<User>)

        fun onDataNotAvailable()
    }

    interface GetUserCallback {

        fun onUserLoaded(user: User)

        fun onDataNotAvailable()
    }

    fun getUser(userId: String, callback: GetUserCallback)
    fun saveUser(user: User)
    fun updateUser(user: User)
    fun deleteUserById(userId: String)
    fun deleteAllUsers()
}
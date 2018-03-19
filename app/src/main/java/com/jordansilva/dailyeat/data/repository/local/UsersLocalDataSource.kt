package com.jordansilva.dailyeat.data.repository.local

import com.jordansilva.dailyeat.data.model.User
import com.jordansilva.dailyeat.data.repository.UsersDataSource
import com.jordansilva.dailyeat.util.AppExecutors

/**
 * Created by jordansilva on 02/03/18.
 */
class UsersLocalDataSource private constructor(val appExecutors: AppExecutors,
                                               val usersDao: UsersDao) : UsersDataSource {
    /**
     * Note: [GetUserCallback.onDataNotAvailable] is fired if the [User] isn't
     * found.
     */
    override fun getUser(userId: String, callback: UsersDataSource.GetUserCallback) {
        appExecutors.diskIO.execute {
            val user = usersDao.getUserById(userId)

            appExecutors.mainThread.execute {
                if (user != null) {
                    callback.onUserLoaded(user)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveUser(user: User) {
        appExecutors.diskIO.execute { usersDao.insertUser(user) }
    }

    override fun updateUser(user: User) {
        appExecutors.diskIO.execute { usersDao.updateUser(user) }
    }

    override fun deleteUserById(userId: String) {
        appExecutors.diskIO.execute { usersDao.deleteUserById(userId) }
    }

    override fun deleteAllUsers() {
        appExecutors.diskIO.execute { usersDao.deleteAllUsers() }
    }

}
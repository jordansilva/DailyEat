package com.jordansilva.dailyeat.data.repository.local

import com.jordansilva.dailyeat.data.model.User
import com.jordansilva.dailyeat.data.repository.UserRepository
import com.jordansilva.dailyeat.util.AppExecutors

/**
 * Created by jordansilva on 02/03/18.
 */
class UserLocalDataSource private constructor(val appExecutors: AppExecutors,
                                              val usersDao: UsersDao) : UserRepository {
    /**
     * Note: [GetUserCallback.onDataNotAvailable] is fired if the [User] isn't
     * found.
     */
    override fun getUser(userId: String, callback: UserRepository.GetUserCallback) {
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
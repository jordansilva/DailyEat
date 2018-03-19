package com.jordansilva.dailyeat.data.repository.local

import android.arch.persistence.room.*
import com.jordansilva.dailyeat.data.model.User

/**
 * Created by jordansilva on 18/03/18.
 */
@Dao interface UsersDao {

    /**
     * Select a user by id.
     *
     * @param userId the user id.
     * @return the User with userId.
     */
    @Query("SELECT * FROM Users WHERE id = :userId") fun getUserById(userId: String): User?

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUser(user: User)

    /**
     * Update a user.
     *
     * @param user user to be updated
     * @return the number of users updated. This should always be 1.
     */
    @Update fun updateUser(user: User): Int


    /**
     * Delete a user by id.
     *
     * @param userId the user id.
     * @return the number of users deleted. This should always be 1.
     */
    @Query("DELETE FROM Users WHERE id = :userId") fun deleteUserById(userId: String): Int

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users") fun deleteAllUsers()

}
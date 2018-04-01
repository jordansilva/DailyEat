package app.jordansilva.data.repository.local

import android.arch.persistence.room.*
import app.jordansilva.data.model.UserModel

/**
 * Created by jordansilva on 18/03/18.
 */
@Dao interface UserDao {

    /**
     * Select a user by id.
     *
     * @param userId the user id.
     * @return the UserModel with userId.
     */
    @Query("SELECT * FROM Users WHERE id = :userId") fun getUserById(userId: String): UserModel?

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUser(user: UserModel)

    /**
     * Update a user.
     *
     * @param user user to be updated
     * @return the number of users updated. This should always be 1.
     */
    @Update fun updateUser(user: UserModel): Int


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
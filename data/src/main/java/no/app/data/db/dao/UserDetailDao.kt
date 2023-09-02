package no.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.app.data.db.models.UserDetailEntity

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDetailEntity)

    @Query("SELECT * FROM user_detail WHERE id = :id")
    suspend fun getUserById(id: String): UserDetailEntity
}

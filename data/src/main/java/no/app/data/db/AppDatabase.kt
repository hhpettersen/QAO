package no.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.app.data.db.dao.UserDetailDao
import no.app.data.db.models.UserDetailEntity
import no.app.data.db.models.UserTypeConverters

@Database(entities = [UserDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(UserTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDetailDao(): UserDetailDao
}

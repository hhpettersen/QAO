package no.app.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import no.app.data.model.api.UserType

@Entity(tableName = "user_detail")
data class UserDetailEntity(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val type: UserType,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
)

object UserTypeConverters {
    @JvmStatic
    fun fromUserType(type: UserType): String = type.name

    @JvmStatic
    fun toUserType(value: String): UserType = UserType.valueOf(value)
}

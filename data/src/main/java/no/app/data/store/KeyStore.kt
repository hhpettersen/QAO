package no.app.data.store

import android.content.Context
import javax.inject.Inject

class KeyStore @Inject constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("auth_store", Context.MODE_PRIVATE)

    fun saveLoginSession(key: String) = sharedPreferences.edit().putString("auth_key", key).apply()

    fun getLoginSession(): String? = sharedPreferences.getString("auth_key", null)
}

package ru.social.demo.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefs @Inject constructor(
    @ApplicationContext context: Context
) {

    private val prefs = context.getSharedPreferences("ru.social.demo", Context.MODE_PRIVATE)

    fun getUserId() = prefs.getString(USER_ID, null)
    fun setUserId(id: String?) = prefs.edit().putString(USER_ID, id).apply()
    fun clearUserId() = prefs.edit().remove(USER_ID).apply()


    companion object {
        const val USER_ID = "userId"
    }

}
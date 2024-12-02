package ru.social.demo.data

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

abstract class CNavType<T: Parcelable> (
    override val isNullableAllowed: Boolean = false
) : NavType<T>(isNullableAllowed = isNullableAllowed) {

    final override fun get(bundle: Bundle, key: String): T? = bundle.getParcelable(key)

    final override fun put(bundle: Bundle, key: String, value: T) { bundle.putParcelable(key, value) }

}

inline fun <reified T: Parcelable> parseValueT(value: String): T {
    return Gson().fromJson(value, T::class.java);
}
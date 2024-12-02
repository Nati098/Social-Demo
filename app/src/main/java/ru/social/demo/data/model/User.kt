package ru.social.demo.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import ru.social.demo.R

@Parcelize
data class User(
    @SerialName("id") override var id: String = "",
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("gender") val gender: User.Gender? = null,
    @SerialName("birthday") val birthday: Timestamp? = null
) : BaseModel(), Parcelable {

    enum class Gender(val value: String, @StringRes val stringId: Int, @DrawableRes val iconId: Int) {
        @SerialName("male") MALE("male", R.string.gender_male, R.drawable.ic_gender_male),
        @SerialName("female") FEMALE("female", R.string.gender_female, R.drawable.ic_gender_female)
    }

}
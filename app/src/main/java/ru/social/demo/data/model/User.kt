package ru.social.demo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class User(
    @SerialName("id") override var id: String = "",
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("name") val name: String? = null
) : BaseModel(), Parcelable
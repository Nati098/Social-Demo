package ru.social.demo.data.model

import kotlinx.serialization.SerialName

data class User(
    @SerialName("id") override var id: String = "",
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("name") val name: String? = null
) : BaseModel()
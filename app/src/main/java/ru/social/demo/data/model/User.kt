package ru.social.demo.data.model

import kotlinx.serialization.SerialName

data class User(
    @SerialName("_id")
    val id: Int = -1,
    val imageUrl: String? = null,
    val name: String? = null
)
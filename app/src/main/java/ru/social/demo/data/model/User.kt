package ru.social.demo.data.model

data class User(
    override var id: String = "",
    val imageUrl: String? = null,
    val name: String? = null
) : BaseModel()
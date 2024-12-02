package ru.social.demo.data.model

import ru.social.demo.data.CNavType
import ru.social.demo.data.parseValueT

class PostCNavType : CNavType<Post>(true) {

    override fun parseValue(value: String): Post = parseValueT<Post>(value)

}
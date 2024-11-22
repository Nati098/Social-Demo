package ru.social.demo.data.model

import androidx.annotation.StringRes
import com.google.firebase.Timestamp
import kotlinx.serialization.SerialName
import ru.social.demo.R
import java.io.File

data class Post (
    @SerialName("id")
    val id: String = "",
    val createDate: Timestamp? = null,
    val user: User? = null,
//    val type: Post.TYPE? = null,
    val title: String? = null,
    val text: String? = null,
    val media: List<File>? = null,
//    val audios: List<File>? = null,
//    val files: List<File>? = null,
//    val survey: Survey? = null,
//    val checklist: Checklist? = null,
    var reactionCounts: Int? = null,
    val commentsCount: Int? = null,
) {
    fun containMedia() = !media.isNullOrEmpty()

//    fun containAudios() = !audios.isNullOrEmpty()
//
//    fun containFiles() = !files.isNullOrEmpty()
//
//    fun containTags() = !tags.isNullOrEmpty()

    enum class TYPE(val value: String, @StringRes val idString: Int) {
        @SerialName("post") POST("post", R.string.post_type_default),
        @SerialName("event") EVENT("event", R.string.post_type_event)
    }

}

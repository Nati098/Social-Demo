package ru.social.demo.data.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import ru.social.demo.R
import java.io.File

@Parcelize
data class Post (
    @SerialName("id") override var id: String = "",
    @SerialName("createDate") val createDate: Timestamp? = null,
    @SerialName("user") val user: User? = null,
    @SerialName("type") val type: Post.TYPE? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("text") val text: String? = null,
    @SerialName("media") val media: List<File>? = null,
//    val audios: List<File>? = null,
//    val files: List<File>? = null,
//    val survey: Survey? = null,
//    val checklist: Checklist? = null,
    @SerialName("reactionCounts") var reactionCounts: Int? = null,
    @SerialName("commentsCount") val commentsCount: Int? = null,
) : BaseModel(), Parcelable {
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

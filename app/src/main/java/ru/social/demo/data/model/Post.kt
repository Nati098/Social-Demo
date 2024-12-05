package ru.social.demo.data.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName
import ru.social.demo.R
import java.io.File

@Parcelize
data class Post (
    @SerializedName("id") override var id: String = "",
    @SerializedName("createDate") val createDate: Timestamp? = null,
    @SerializedName("updateDate") val updateDate: Timestamp? = null,
    @SerializedName("user") val user: User? = null,
    @SerializedName("type") val type: Post.Type? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("media") val media: List<File>? = null,
//    val audios: List<File>? = null,
//    val files: List<File>? = null,
//    val survey: Survey? = null,
//    val checklist: Checklist? = null,
    @SerializedName("reactionCounts") var reactionCounts: Int? = null,
    @SerializedName("commentsCount") val commentsCount: Int? = null,
) : BaseModel(), Parcelable {
    fun containMedia() = !media.isNullOrEmpty()

//    fun containAudios() = !audios.isNullOrEmpty()
//
//    fun containFiles() = !files.isNullOrEmpty()
//
//    fun containTags() = !tags.isNullOrEmpty()

    enum class Type(val value: String, @StringRes val idString: Int) {
        @SerializedName("post") POST("post", R.string.post_type_default),
        @SerializedName("event") EVENT("event", R.string.post_type_event)
    }

}

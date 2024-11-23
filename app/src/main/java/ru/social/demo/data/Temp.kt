package ru.social.demo.data.model

import com.google.firebase.Timestamp
import kotlin.random.Random

val TEMP_USER = User(
    id = "0",
    name = "Maria Robbins",
    imageUrl = "https://media.istockphoto.com/id/1326417862/photo/young-woman-laughing-while-relaxing-at-home.jpg?s=612x612&w=0&k=20&c=cd8e6RBGOe4b8a8vTcKW0Jo9JONv1bKSMTKcxaCra8c="
)

val TEMP_POST = Post(
        id = "",
        createDate = Timestamp(seconds=1732273380, nanoseconds=24000000),
        TEMP_USER,
        title = "Lorem Ipsum",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi aliquam, turpis suscipit sodales sodales, mauris ligula imperdiet tortor, et laoreet enim arcu at est. Nam eros augue, ultricies non blandit sit amet, sagittis vel ante.",
    )

val TEMP_POST1 = Post(
    id = "",
    createDate = Timestamp.now(),
    TEMP_USER,
    title = "Random God",
    text = "tetete",
)
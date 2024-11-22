package ru.social.demo.services

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FsPath {
    const val POSTS = "posts"
}

class FirestoreInteractor(
    val db: FirebaseFirestore
) {

    companion object {

        @Volatile
        private var instance: FirestoreInteractor? = null

        fun getInstance() = instance ?: synchronized(this) {
            FirestoreInteractor(db = Firebase.firestore).also { instance = it }
        }

    }

    fun <T : Any> setData(path: String, data: T) {
        db.collection(path)
            .document().set(data)
            .addOnSuccessListener { result -> Log.d("TEST", "updateData $result ") }
            .addOnFailureListener { e -> Log.e("TEST","Firebase.firestore error: $e") }
    }

//    inline fun <reified T> readData(path: String) {
//        db.collection(FsPath.POSTS).get()
//            .addOnSuccessListener { result ->
//                postsList.value = result.toObjects(Post::class.java).sortedByDescending { it.createDate }
//            }
//            .addOnFailureListener { e -> Log.e("TEST","Firebase.firestore error: $e") }
//    }

//    inline fun <reified T> readDataContinuously(path: String) {
//        val listener = {snapShot, e ->
//            postsList.value = snapShot?.toObjects(Post::class.java)?.sortedByDescending { it.createDate } ?: emptyList()
//        }
//        db.collection(FsPath.POSTS).addSnapshotListener(listener)
//        // !!! remove listener if it's unuseful: listener.remove()
//    }

}
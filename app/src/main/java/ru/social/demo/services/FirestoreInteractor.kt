package ru.social.demo.services

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ru.social.demo.data.model.BaseModel

object FsPath {
    const val POSTS = "posts"
    const val USERS = "users"
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

    fun <T : BaseModel> setData(
        path: String,
        data: T,
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {},
    ) {
        val key = db.collection(path).document().id
        db.collection(path)
            .document(key).set(data.apply { this.id = key })
            .addOnSuccessListener { result ->
                Log.d("TEST", "set result: $result ")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("TEST","Firebase.firestore set error: $e")
                onError()
            }
    }

    inline fun <reified T> readData(
        path: String,
        crossinline onSuccess: (List<T>?) -> Unit,
        crossinline onError: (Exception) -> Unit = {},
    ) {
        db.collection(path).get()
            .addOnSuccessListener { result ->
                Log.d("TEST", "read success ")
                onSuccess(result.toObjects(T::class.java))
            }
            .addOnFailureListener { e ->
                Log.e("TEST","Firebase.firestore read error: $e")
                onError(e)
            }
    }

//    inline fun <reified T> readDataContinuously(path: String) {
//        val listener = {snapShot, e ->
//            postsList.value = snapShot?.toObjects(Post::class.java)?.sortedByDescending { it.createDate } ?: emptyList()
//        }
//        db.collection(FsPath.POSTS).addSnapshotListener(listener)
//        // !!! remove listener if it's unuseful: listener.remove()
//    }


//    fun <T : BaseModel> updateData(
//        path: String,
//        data: T,
//        onSuccess: () -> Unit = {},
//        onError: () -> Unit = {},
//    ) {
//        db.collection(path).document(data.id)
//            .update(data)
//            .addOnSuccessListener { result ->
//                Log.d("TEST", "set result: $result ")
//                onSuccess()
//            }
//            .addOnFailureListener { e ->
//                Log.e("TEST","Firebase.firestore set error: $e")
//                onError()
//            }
//    }

}
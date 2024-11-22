package ru.social.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.ktx.Firebase
import ru.social.demo.ui.components.*
import ru.social.demo.ui.theme.SocialDemoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.firestore

        enableEdgeToEdge()
        setContent {
            SocialDemoTheme {
                NavigationBar()
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SocialDemoTheme {
//        Greeting("Android", counter = null)
//    }
//}
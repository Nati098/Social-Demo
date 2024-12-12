package ru.social.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.social.demo.ui.components.BottomBar
import ru.social.demo.ui.theme.SocialDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SocialDemoTheme {
                BottomBar()
            }
        }
    }

    override fun onStop() {
        viewModel.handle(MainContract.Event.UserRemoved)
        super.onStop()
    }

}

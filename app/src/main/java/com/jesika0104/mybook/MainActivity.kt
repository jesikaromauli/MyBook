package com.jesika0104.mybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jesika0104.mybook.screen.MainScreen
import com.jesika0104.mybook.ui.theme.MyBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBookTheme {
                MainScreen()
            }
        }
    }
}
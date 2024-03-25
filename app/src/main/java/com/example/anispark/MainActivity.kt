package com.example.anispark

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.anispark.ui.theme.AniSparkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniSparkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Using the Greeting function which will center the text
                    Greeting("AniSpark")
                }
            }
        }

        // Delay the transition to Next_Activity by 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Next_Activity::class.java)
            startActivity(intent)
            finish()  // Finish MainActivity to remove it from the back stack
        }, 3000)
    }
}

@Composable
fun Greeting(name: String) {
    // Using Box to center the Text composable
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AniSparkTheme {
        Greeting("AniSpark")
    }
}

package com.example.prueba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prueba.ui.theme.PruebaTheme
import com.example.prueba.ui.viewmodel.DashboardNavigator


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // PruebaTheme {
            // A surface container using the 'background' color from the theme
            //     Surface(
            //         modifier = Modifier.fillMaxSize(),
            //         color = MaterialTheme.colorScheme.background,
            //     ) {
            //         Greeting("Android")
            //     }
            // }
            DashboardNavigator()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PruebaTheme {
        Greeting("Android")
    }
}

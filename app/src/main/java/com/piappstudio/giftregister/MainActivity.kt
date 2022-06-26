@file:OptIn(ExperimentalMaterial3Api::class)

package com.piappstudio.giftregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.piappstudio.giftregister.navgraph.AppNavGraph
import com.piappstudio.giftregister.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
               SetUpAppNavGraph()
            }
        }
    }

    @Composable
    fun SetUpAppNavGraph() {
        navController = rememberNavController()

        NavHost(navController = navController, startDestination = "SplashScreen" ) {
            composable("WelcomeScreen") {
                WelcomeScreen()
            }
            composable("SplashScreen") {
                SplashScreen(navController = navController)
            }
        }
    }
}



@Composable
fun WelcomeScreen() {
    Scaffold() {
        Text(text = "Welcome Screen", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.headlineLarge)
    }
}
@Composable
fun SplashScreen(navController: NavController) {
    Scaffold() {
        Column {
            Text(text = "Splash Screen", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.headlineMedium)
            Button(onClick = {navController.navigate("WelcomeScreen")}) {
                Text(text = "Go to Welcome Screen")
            }
        }

    }
}


@Composable
fun Greeting(name: String) {

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello $name!")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Submit")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting("Android")
    }
}
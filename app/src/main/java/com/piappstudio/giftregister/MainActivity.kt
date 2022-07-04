package com.piappstudio.giftregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piappstudio.giftregister.navgraph.AppNavGraph
import com.piappstudio.pitheme.route.Route
import com.piappstudio.pitheme.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        AppNavGraph(navController = navController)
        lifecycleScope.launch {
            delay(1000)
            navController.navigate(Route.Welcome.SPLASH)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
    }
}
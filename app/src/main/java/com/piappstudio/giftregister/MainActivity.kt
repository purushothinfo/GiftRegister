package com.piappstudio.giftregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piappstudio.giftregister.navgraph.AppNavGraph
import com.piappstudio.pinavigation.NavManager
import com.piappstudio.pitheme.route.Route
import com.piappstudio.pitheme.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navManager: NavManager
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

        // Listen for navigation change and execute the navigation
        val navInfo by navManager.routeInfo.collectAsState()
        LaunchedEffect(key1 = navInfo) {
            navInfo.id?.let {
                navController.navigate(it, navOptions = navInfo.navOption)
                navManager.navigate(null)
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
    }
}
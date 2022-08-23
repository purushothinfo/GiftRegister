package com.piappstudio.giftregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.piappstudio.pigiftmodel.data.GiftDatabase
import com.piappstudio.giftregister.navgraph.AppNavGraph
import com.piappstudio.pitheme.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Room.databaseBuilder(
            applicationContext,
            GiftDatabase::class.java, "GiftDatabase"
        ).fallbackToDestructiveMigration().build()
        val eventDao = database. eventInfo()
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

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
    }
}
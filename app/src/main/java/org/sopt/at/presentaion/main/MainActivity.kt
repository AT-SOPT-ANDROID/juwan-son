package org.sopt.at.presentaion.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.data.remote.model.BottomNavItem
import org.sopt.at.presentaion.history.HistoryScreen
import org.sopt.at.presentaion.home.HomeScreen
import org.sopt.at.presentaion.live.LiveScreen
import org.sopt.at.presentaion.my.MyScreen
import org.sopt.at.presentaion.search.SearchScreen
import org.sopt.at.presentaion.shorts.ShortsScreen
import org.sopt.at.presentaion.signin.SignInScreen
import org.sopt.at.presentaion.signup.SignUpScreen
import org.sopt.at.presentation.home.BottomNavBar
import org.sopt.at.presentation.home.HomeViewModel
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme  {
                AppContent()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun AppContent() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem(name = "HOME", iconRes = R.drawable.ic_home, selectedIconRes = R.drawable.ic_home),
        BottomNavItem(name = "SHORTS", iconRes = R.drawable.ic_shorts, selectedIconRes = R.drawable.ic_shorts),
        BottomNavItem(name = "LIVE", iconRes = R.drawable.ic_live, selectedIconRes = R.drawable.ic_live),
        BottomNavItem(name = "SEARCH", iconRes = R.drawable.ic_search, selectedIconRes = R.drawable.ic_search),
        BottomNavItem(name = "HISTORY", iconRes = R.drawable.ic_history, selectedIconRes = R.drawable.ic_history)
    )

    var selectedItem by remember { mutableStateOf("HOME") }


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val shouldShowBottomNav = currentRoute in setOf("home", "shorts", "live", "search", "history")

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNav) {
                BottomNavBar(
                    items = bottomNavItems,
                    selectedItem = selectedItem,
                    onItemSelected = {
                        selectedItem = it
                        when (it) {
                            "HOME" -> navController.navigate("home")
                            "SHORTS" -> navController.navigate("shorts")
                            "LIVE" -> navController.navigate("live")
                            "SEARCH" -> navController.navigate("search")
                            "HISTORY" -> navController.navigate("history")
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            MainNavHost(navController = navController)
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signin") {
        composable("signin") { SignInScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController, homeViewModel = HomeViewModel()) }
        composable("shorts") {ShortsScreen(navController) }
        composable("live") { LiveScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("history") { HistoryScreen(navController) }
        composable("my") { MyScreen(navController) }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ATSOPTANDROIDTheme  {
        Greeting("Android")
    }
}


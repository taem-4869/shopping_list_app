package com.taemallah.shoppinglist.mainActivity.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation.ExploreViewModel
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation.components.ExploreScreen
import com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.HomeViewModel
import com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.components.HomeScreen
import com.taemallah.shoppinglist.ui.theme.ShoppingListTheme
import com.taemallah.shoppinglist.utils.Rout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val exploreViewModel by viewModels<ExploreViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                val state by mainViewModel.state.collectAsState()
                Surface {
                    Scaffold(
                        bottomBar = {
                            NavigationBar(
                                modifier = Modifier.clip(RoundedCornerShape(20)),
                                tonalElevation = 4.dp
                            ) {
                                state.navItems.forEachIndexed{index,item->
                                    NavigationBarItem(
                                        selected = state.currentScreenIndex == index,
                                        onClick = { mainViewModel.onEvent(
                                            MainEvent.SetCurrentScreenIndex(
                                                index
                                            )
                                        ) },
                                        icon = {
                                            BadgedBox(badge = {
                                                if (item.badgeCount!=null && item.badgeCount>0){
                                                    Badge{
                                                        Text(text = item.badgeCount.toString())
                                                    }
                                                }else if (item.hasNews){
                                                    Badge()
                                                }
                                            }) {
                                                Icon(
                                                    imageVector = if (index == state.currentScreenIndex) item.selectedIcon else item.unselectedIcon,
                                                    contentDescription = null
                                                )
                                            }
                                        })
                                }
                            }
                        }
                    ) {paddingValues ->
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)) {
                            val navHostController = rememberNavController()
                            mainViewModel.setNavController(navHostController)
                            NavHost(navController = navHostController, startDestination = Rout.HomeScreen) {
                                composable<Rout.HomeScreen>{
                                    val homeState by homeViewModel.state.collectAsState()
                                    HomeScreen(state = homeState, onEvent = homeViewModel::onEvent)
                                }
                                composable<Rout.ExploreScreen>{
                                    exploreViewModel.onMainEvent = mainViewModel::onEvent
                                    val exploreState by exploreViewModel.state.collectAsState()
                                    ExploreScreen(modifier = Modifier.padding(paddingValues),state = exploreState, onEvent = exploreViewModel::onEvent)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

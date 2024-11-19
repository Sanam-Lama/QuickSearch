package com.example.quicksearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.quickimage.viewmodel.QuickSearchViewModel
import com.example.quicksearch.model.CustomNavType
import com.example.quicksearch.model.Item
import com.example.quicksearch.model.Search
import com.example.quicksearch.model.SearchDetail
import com.example.quicksearch.ui.SearchDetailsScreen
import com.example.quicksearch.ui.SearchScreen
import com.example.quicksearch.ui.theme.QuickSearchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickSearchTheme {
                val viewModel: QuickSearchViewModel = hiltViewModel() // Initialize the ViewModel
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController,
                        startDestination = Search
                    ) {
                        composable<Search> {
                            SearchScreen(
                                viewModel = viewModel,
                                onItemClick = { item ->
                                    navController.navigate(SearchDetail(item = item))
                                }
                            )
                        }
                        composable<SearchDetail>(
                            typeMap = mapOf(
                                typeOf<Item>() to CustomNavType.ItemType
                            )
                        ) { backStackEntry ->
                            val args = backStackEntry.toRoute<SearchDetail>()
                            SearchDetailsScreen(
                                item = args.item,
                                onBackClicked = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

package com.example.main_impl.presentation.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.danish.home_impl.presentation.screens.home.HomeScreen
import com.danish.home_impl.presentation.screens.home.HomeViewModel
import com.danish.navigation.Navigator
import com.example.main_impl.presentation.BottomNavItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navigator: Navigator
) {
    var selectedItem by rememberSaveable { mutableStateOf(BottomNavItem.HOME) }
    val stateHolder = rememberSaveableStateHolder()

    val homeViewModel: HomeViewModel = koinViewModel()
    BackHandler(
        enabled = selectedItem != BottomNavItem.HOME
    ) {
        selectedItem= BottomNavItem.HOME
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0,0,0,0)
    ) {innerPadding->
        stateHolder.SaveableStateProvider(key = selectedItem) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                when(selectedItem){
                    BottomNavItem.HOME -> {
                        HomeScreen(
                            viewModel = homeViewModel,
                            navigator = navigator
                        )
                    }
                    BottomNavItem.SEARCH ->{

                    }
                }
            }
        }
    }

}
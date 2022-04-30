package com.example.todoapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.ui.screens.list.listScreen
import com.example.todoapp.util.Constants.LIST_ARGUMENT_KEY
import com.example.todoapp.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId:Int) -> Unit
){
     composable(
         route = LIST_SCREEN,
         arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
             type= NavType.StringType
         })
     ){
          listScreen(navigateToTaskScreen=navigateToTaskScreen)
     }
}
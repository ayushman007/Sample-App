package com.example.todoapp.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
import com.example.todoapp.ui.theme.fabBackgroundColor

@Composable
fun listScreen(
    navigateToTaskScreen: (taskId:Int) -> Unit

){
   Scaffold (
       topBar = {
                ListAppBar()
       },
       content = {},
       floatingActionButton ={
           listFAB(onFABClicked = navigateToTaskScreen)
       }
           )
}

@Composable
fun listFAB(
    onFABClicked: (taskId:Int) -> Unit
){
    FloatingActionButton(
        onClick = {
        onFABClicked(-1)
    },
    backgroundColor = MaterialTheme.colors.fabBackgroundColor)
    {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.addButton),
            tint= Color.White
        )
    }
}
@Composable
@Preview
private fun ListScreenPreview(){
    listScreen(navigateToTaskScreen = {})
}
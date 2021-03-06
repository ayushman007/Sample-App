package com.example.todoapp.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.todoapp.components.PriorityItem
import com.example.todoapp.ui.theme.*

@Composable
fun ListAppBar(){
     DefaultListAppBar(
         onSearchClicked = {},
         onSortClicked = {},
         onDeleteClicked = {}
     )
}

@Composable
fun DefaultListAppBar(
    onSearchClicked:() -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit

){
    TopAppBar(
        title = {
            Text(text="Tasks",
                color = MaterialTheme.colors.topAppBarContentColor)

        },
        actions={
           ListAppBarActions(onSearchClicked=onSearchClicked,
               onSortClicked = onSortClicked,
            onDeleteClicked=onDeleteClicked)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked:() -> Unit,
    onSortClicked:(Priority)->Unit,
    onDeleteClicked: () -> Unit

){
   SearchAction (onSearchClicked = onSearchClicked)
    sortAction(onSortClicked = onSortClicked)
    DeleteAllAction (onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked:() -> Unit
){
   IconButton(
       onClick = { onSearchClicked()}
   ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.Search_task),
                tint=MaterialTheme.colors.topAppBarContentColor
            )
   }
}

@Composable
fun sortAction(
    onSortClicked:(Priority)->Unit
){
    var expanded by remember { mutableStateOf(false)}

    IconButton(
        onClick = {  expanded=true  }
    ) {

        Icon(
            painter= painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(R.string.sort_task),
            tint = MaterialTheme.colors.topAppBarContentColor
                    )
        DropdownMenu(
            expanded =expanded ,
            onDismissRequest = { expanded=false}
        )
        {
         DropdownMenuItem(onClick = { expanded=false}) {
             PriorityItem(priority = Priority.LOW)
             onSortClicked(Priority.LOW)
         }
            DropdownMenuItem(onClick = { expanded=false}) {
                PriorityItem(priority = Priority.MEDIUM)
                onSortClicked(Priority.MEDIUM)
            }

            DropdownMenuItem(onClick = { expanded=false}) {
                PriorityItem(priority = Priority.HIGH)
                onSortClicked(Priority.HIGH)
            }

            DropdownMenuItem(onClick = { expanded=false}) {
                PriorityItem(priority = Priority.LOW)
                onSortClicked(Priority.NONE)
            }
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked:()->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(R.string.delete_all),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        )
        {
            DropdownMenuItem(
                onClick = {
                    expanded = false 
                     onDeleteClicked()
            }
            ) {
                Text(text = stringResource(R.string.delete_all),
                style= Typography.subtitle2,
                modifier = Modifier.padding(start= LARGE_PADDING) )
            }
        }

    }
}

@Composable
fun SearchAppBar(
    text:String,
    onTextChange:(String)->Unit,
    onCloseClicked:()->Unit,
    onSearchClicked:(String)->Unit
){
  Surface(
      modifier = Modifier
          .fillMaxWidth()
          .height(TOP_APP_BAR_HEIGHT),
      elevation = AppBarDefaults.TopAppBarElevation,
      color=MaterialTheme.colors.topAppBarBackgroundColor

  ){
      TextField(modifier=Modifier.fillMaxWidth(),
          value = text,
          onValueChange ={
              onTextChange(it)
          },

      placeholder = {
          Text(
              modifier=Modifier.alpha(ContentAlpha.medium),
              text = "Search",
              color = Color.White
          )
      },
          textStyle = TextStyle(
              color = MaterialTheme.colors.topAppBarContentColor,
              fontSize = MaterialTheme.typography.subtitle1.fontSize,
              ),
          singleLine = true,

          leadingIcon = {
              IconButton(
                  modifier=Modifier.alpha(ContentAlpha.disabled),
                  onClick = { }
              ) {
                  Icon(imageVector =Icons.Filled.Search ,
                      contentDescription ="Search Icon",
                      tint=MaterialTheme.colors.topAppBarContentColor)
              }
          },
          trailingIcon = {
              IconButton(onClick = {
                  onCloseClicked()
              }
              ) {
                  Icon(
                      imageVector =Icons.Filled.Close,
                      contentDescription = "Close Icon",
                      tint=MaterialTheme.colors.topAppBarContentColor
                  )
              }
          },
          keyboardOptions = KeyboardOptions(
              imeAction = ImeAction.Search
          ),
          keyboardActions = KeyboardActions(
              onSearch = {
                  onSearchClicked(text)
              }
          ),
          colors = TextFieldDefaults.textFieldColors(
              cursorColor = MaterialTheme.colors.topAppBarContentColor,
              focusedIndicatorColor = Color.Transparent,
              disabledIndicatorColor = Color.Transparent,
              unfocusedIndicatorColor = Color.Transparent,
              backgroundColor = Color.Transparent
          )

      )
  }
}
@Composable
@Preview
fun DefaultListAppBarPreview(){
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}


@Composable
@Preview
private fun SearchAppBarPreview(){
    SearchAppBar(
        text = "",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )

}
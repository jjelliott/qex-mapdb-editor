package com.github.jjelliott.components.map

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.jjelliott.ActionType
import com.github.jjelliott.Episode
import com.github.jjelliott.JsonFile
import com.github.jjelliott.Map

@Composable
fun listRow(
    map: Map, setAction: (map: Map, actionType: ActionType) -> Unit
) {
    var deleteText by remember { mutableStateOf("Delete") }
    Row(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(map.title)
        }
        Column {
            Row() {
                Column (Modifier.padding(2.dp, 0.dp)){
                    Button(onClick = {
                        if (deleteText == "Delete") {
                            deleteText = "Confirm"
                        } else {
                            setAction(map, ActionType.DELETE)
                        }
                    }) {
                        Text(deleteText)
                    }
                }
                Column (Modifier.padding(2.dp, 0.dp)){

                    Button(onClick = {
                        setAction(map, ActionType.ADD)
                    }) {
                        Text("Duplicate")
                    }
                }
                Column (Modifier.padding(2.dp, 0.dp)){
//            Column (horizontalAlignment = Alignment.End){
                    Button(onClick = {
                        setAction(map, ActionType.EDIT)
                    }) {
                        Text("Edit")
                    }
                }
            }
        }
    }
}

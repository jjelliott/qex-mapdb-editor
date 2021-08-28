package com.github.jjelliott.components.episode

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.jjelliott.ActionType
import com.github.jjelliott.Episode


@Composable
fun list(
    episodes: List<Episode>,
    setAction: (episode: Episode, actionType: ActionType) -> Unit
) {

    var scrollState = rememberLazyListState()
    Text("Episode List")
    Row {
        LazyColumn(Modifier.fillMaxWidth(), state = scrollState, contentPadding = PaddingValues(5.dp, 8.dp)) {
            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {
                            setAction(Episode(), ActionType.ADD)
                        }) {
                            Text("New")
                        }
                    }
                }
            }
            episodes.forEach {
                item {
                    listRow(it, setAction)
                }
            }
            if (episodes.isNotEmpty()) {
                item {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(onClick = {
                                setAction(Episode(), ActionType.ADD)
                            }) {
                                Text("New")
                            }
                        }
                    }
                }
            }
        }
    }
}


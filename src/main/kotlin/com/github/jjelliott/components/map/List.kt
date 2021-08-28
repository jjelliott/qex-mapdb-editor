package com.github.jjelliott.components.map

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.github.jjelliott.ActionType
import com.github.jjelliott.Episode
import com.github.jjelliott.Map

@Composable
fun list(
    maps: List<Map>,
    episodes: List<Episode>,
    setAction: (map: Map, actionType: ActionType) -> Unit
) {
//    var scrollState = rememberScrollState(0)
    var scrollState = rememberLazyListState()

    val mapsByEpisode = HashMap<String, List<Map>>()

    episodes.forEach { e ->
        mapsByEpisode[e.name] = maps.filter { it.episode == e.dir }
    }

    Text("Map List")
    Row {
        LazyColumn(Modifier.fillMaxWidth(), state = scrollState, contentPadding = PaddingValues(5.dp, 8.dp)) {
            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {
                            setAction(Map(), ActionType.ADD)
                        }) {
                            Text("New")
                        }
                    }
                }
            }
            mapsByEpisode.forEach {
                if (it.value.isNotEmpty()) {
                    item {
                        Row {
                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(it.key, fontSize = 2.em)
                            }
                        }
                        it.value.forEach { m ->
                            listRow(m, setAction)
                        }
                    }
                }
            }
            if (maps.isNotEmpty()) {
                item {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(onClick = {
                                setAction(Map(), ActionType.ADD)
                            }) {
                                Text("New")
                            }
                        }
                    }
                }
            }

        }
//        VerticalScrollbar(Modifier.align(Alignment.CenterVertically), scrollState, maps.size + 1, 14.dp)
    }

}


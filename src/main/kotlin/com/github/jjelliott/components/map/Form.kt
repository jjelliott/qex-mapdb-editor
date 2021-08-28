package com.github.jjelliott.components.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.github.jjelliott.Episode
import com.github.jjelliott.JsonFile
import com.github.jjelliott.Map
import com.github.jjelliott.MapDb


@Composable
fun form(map: Map, episodes: List<Episode>, toMain: () -> Unit, add: Boolean = true, getFile: () -> JsonFile?) {

    var title by remember { mutableStateOf(map.title) }
    var bsp by remember { mutableStateOf(map.bsp) }
    var game by remember { mutableStateOf(map.game) }
    var episode by remember { mutableStateOf(map.episode) }
    var dm by remember { mutableStateOf(map.dm) }
    var bots by remember { mutableStateOf(map.bots) }
    var coop by remember { mutableStateOf(map.coop) }
    var sp by remember { mutableStateOf(map.sp) }

    var episodeMenuOpen by remember{ mutableStateOf(false)}
    Text(if (add) "New Map" else "Editing ${map.title}")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        TextField(value = title, label = { Text("Title") }, onValueChange = {
            title = it
        })
        TextField(value = bsp, label = { Text("BSP Name") }, onValueChange = {
            bsp = it
        })
        Button(onClick = {episodeMenuOpen = true}){
            Text("Select Episode")
        }
        DropdownMenu(expanded = episodeMenuOpen, onDismissRequest = {episodeMenuOpen = false}){
            episodes.forEach {
                DropdownMenuItem(onClick = {
                    episode = it.dir
                    game = it.dir
                    episodeMenuOpen = false
                }) {
                    Text(it.name)
                }
            }
        }
        Row {
            Text("DM")
            Checkbox(
                checked = dm,
                onCheckedChange = { dm = it })
        }
        Row {
            Text("Coop")
            Checkbox(
                checked = coop,
                onCheckedChange = { coop = it })
        }
        Row {
            Text("Bots")
            Checkbox(
                checked = bots,
                onCheckedChange = { bots = it })
        }
        Row {
            Text("SP")
            Checkbox(
                checked = sp,
                onCheckedChange = { sp = it })
        }
        Row {
            Column {
                Button(onClick = {
                    toMain()
                }) {
                    Text("Cancel")
                }
            }
            Column {
                Button(onClick = {
                    val mapDb: MapDb? = getFile()?.read()
                    if (mapDb != null) {
                        val newMap = Map(title,bsp, episode,game, dm, coop, bots, sp)
                        if (add) {
                            mapDb.addMap(newMap)
                        } else {
                            mapDb.replaceMap(old = map, new = newMap)
                        }
                        getFile()?.write(mapDb)
                    }

                    toMain()
                }) {
                    Text("Save")
                }
            }
        }
    }

}

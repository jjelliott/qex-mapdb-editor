package com.github.jjelliott.components.episode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.github.jjelliott.Episode
import com.github.jjelliott.JsonFile
import com.github.jjelliott.MapDb


@Composable
fun form(episode: Episode, toMain: () -> Unit, add: Boolean = true, getFile: () -> JsonFile?) {

    var name by remember { mutableStateOf(episode.name) }
    var dir by remember { mutableStateOf(episode.dir) }
    var needsSkillSelect by remember { mutableStateOf(episode.needsSkillSelect) }
    Text(if (add) "New Episode" else "Editing ${episode.name}")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        TextField(value = name, label = { Text("Name") }, onValueChange = {
            name = it
        })
        TextField(value = dir, label = { Text("Directory") }, onValueChange = {
            dir = it
        })
        Row {
            Text("Needs skill select?")
            Checkbox(
                checked = needsSkillSelect,
                onCheckedChange = { needsSkillSelect = it })
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
                        if (add) {
                            mapDb.addEpisode(Episode(name, dir, needsSkillSelect))
                        } else {
                            mapDb.replaceEpisode(old = episode, new = Episode(name, dir, needsSkillSelect))
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

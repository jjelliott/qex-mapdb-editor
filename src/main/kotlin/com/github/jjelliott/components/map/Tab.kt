package com.github.jjelliott.components.map

import androidx.compose.runtime.*
import com.github.jjelliott.*
import com.github.jjelliott.Map

class MapAction(val map: Map, val actionType: ActionType)

@Composable
fun MapTab(getFile: () -> JsonFile?) {
    val mapDb = getFile()?.read()
    val episodes = mapDb?.episodes
    val maps = mapDb?.maps
    var action: MapAction? by remember { mutableStateOf(null) }
    val reset = {
        action = null
    }
    if (action != null) {
        if (action!!.actionType == ActionType.DELETE) {
            val newDb: MapDb = getFile()?.read()!!
            newDb.deleteMap(action?.map!!)
            getFile()?.write(newDb)
            action = null
        } else {
            form(action?.map!!, episodes!!, reset, action?.actionType!! == ActionType.ADD, getFile)
        }
    } else {
        list(maps!!, episodes!!) { map: Map, actionType: ActionType ->
            action = MapAction(map, actionType)
        }
    }
}

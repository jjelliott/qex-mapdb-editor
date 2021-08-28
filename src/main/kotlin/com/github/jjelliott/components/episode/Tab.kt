package com.github.jjelliott.components.episode

import androidx.compose.runtime.*
import com.github.jjelliott.ActionType
import com.github.jjelliott.Episode
import com.github.jjelliott.JsonFile
import com.github.jjelliott.MapDb

class EpisodeAction(val episode: Episode, val actionType: ActionType)

@Composable
fun EpisodeTab(getFile: () -> JsonFile?) {
    var episodes = getFile()?.read()?.episodes
    var action: EpisodeAction? by remember { mutableStateOf(null) }
    val reset = {
        action = null
    }
    if (action != null) {
        if (action!!.actionType == ActionType.DELETE) {
            val newDb: MapDb = getFile()?.read()!!
            newDb.deleteEpisode(action?.episode!!)
            getFile()?.write(newDb)
            action = null
        } else {
            form(action?.episode!!, reset, action?.actionType!! == ActionType.ADD, getFile)
        }
    } else {
        list(episodes!!) { episode: Episode, actionType: ActionType ->
            action = EpisodeAction(episode, actionType)
        }
    }
}

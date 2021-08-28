package com.github.jjelliott

class MapDb(var episodes: List<Episode>, var maps: List<Map>) {

    constructor() : this(ArrayList<Episode>(), ArrayList<Map>())

    fun addEpisode(episode: Episode) {
        episodes += episode
    }

    fun replaceEpisode(old: Episode, new: Episode) {
        deleteEpisode(old)
        addEpisode(new)
    }

    fun deleteEpisode(episode: Episode){
        episodes = episodes.filter {
            it != episode
        }
    }

    fun addMap(map: Map) {
        maps += map
    }

    fun replaceMap(old: Map, new: Map) {
        deleteMap(old)
        addMap(new)
    }

    fun deleteMap(map: Map){
        maps = maps.filter {
            it != map
        }
    }

    override fun toString(): String {
        return "MapDb(episodes=$episodes, maps=$maps)"
    }
}

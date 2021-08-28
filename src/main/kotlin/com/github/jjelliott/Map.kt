package com.github.jjelliott

class Map(
    val title: String,
    val bsp: String,
    val episode: String,
    val game: String,
    val dm: Boolean,
    val coop: Boolean,
    val bots: Boolean,
    val sp: Boolean
) {

    constructor() : this("","","","", false, false, false, false)
    override fun toString(): String {
        return "Map(title='$title', bsp='$bsp', episode='$episode', game='$game', dm=$dm, coop=$coop, bots=$bots, sp=$sp)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Map

        if (title != other.title) return false
        if (bsp != other.bsp) return false
        if (episode != other.episode) return false
        if (game != other.game) return false
        if (dm != other.dm) return false
        if (coop != other.coop) return false
        if (bots != other.bots) return false
        if (sp != other.sp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + bsp.hashCode()
        result = 31 * result + episode.hashCode()
        result = 31 * result + game.hashCode()
        result = 31 * result + dm.hashCode()
        result = 31 * result + coop.hashCode()
        result = 31 * result + bots.hashCode()
        result = 31 * result + sp.hashCode()
        return result
    }
}

package com.github.jjelliott

class Episode(var name: String, var dir: String, var needsSkillSelect: Boolean) {

    constructor() : this("", "", false)



    override fun toString(): String {
        return "Episode(name='$name', dir='$dir', needsSkillSelect=$needsSkillSelect)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Episode

        if (name != other.name) return false
        if (dir != other.dir) return false
        if (needsSkillSelect != other.needsSkillSelect) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + dir.hashCode()
        result = 31 * result + needsSkillSelect.hashCode()
        return result
    }
}

package com.github.jjelliott

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.nio.file.Paths

class JsonFile(private val path: String) {

    fun write(db: MapDb) {
        val mapper = ObjectMapper().registerKotlinModule().enable(SerializationFeature.INDENT_OUTPUT)
        mapper.writeValue(Paths.get(path).toFile(), db)
    }

    fun read(): MapDb {
        val file = File(path)
        if (!file.exists()){
            file.createNewFile()
        }
        val json = file.readText()
        val mapper = ObjectMapper().registerKotlinModule()
        return if (json.isNotEmpty()) {
            mapper.readValue(json, MapDb::class.java)
        } else {
            MapDb()
        }
    }
}


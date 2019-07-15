package com.github.omarmiatello.actions.data

import com.github.omarmiatello.actions.readText
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Test

@Serializable
data class DevFestModel(
        val schedule: Map<String, Schedule>,
        val sessions: Map<String, Talk>,
        val speakers: Map<String, Speaker>
)

internal class DevFestModelTest {
    @Test
    fun test_parse_devfest_json() {
        val devFestText = readText("devfest.json")
        val devFestModel = Json.nonstrict.parse(DevFestModel.serializer(), devFestText)
        // parse without throw
    }
}
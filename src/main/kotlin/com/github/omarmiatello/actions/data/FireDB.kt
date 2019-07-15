package com.github.omarmiatello.actions.data

import com.github.omarmiatello.actions.appengine.FirebaseDatabaseApi
import com.github.omarmiatello.actions.appengine.fireMap
import kotlinx.serialization.map
import kotlinx.serialization.serializer


object FireDB : FirebaseDatabaseApi() {
    override val basePath = "https://gdglevante.firebaseio.com/"

    private val SERIALIZER_STRING_SCHEDULE = (String.serializer() to Schedule.serializer()).map
    private val SERIALIZER_STRING_TALK = (String.serializer() to Talk.serializer()).map
    private val SERIALIZER_STRING_SPEAKER = (String.serializer() to Speaker.serializer()).map

    var scheduleByDay by fireMap("schedule", SERIALIZER_STRING_SCHEDULE, useCache = true)
    var sessions by fireMap("sessions", SERIALIZER_STRING_TALK, useCache = true)
    var speakers by fireMap("speakers", SERIALIZER_STRING_SPEAKER, useCache = true)
}
package com.github.omarmiatello.actions

import com.github.omarmiatello.actions.data.FireDB
import com.github.omarmiatello.actions.utils.EasyDialogflowApp
import com.google.actions.api.ActionRequest
import com.google.actions.api.ForIntent

class MyActionsApp : EasyDialogflowApp() {
    @ForIntent("Default Welcome Intent")
    fun welcome(request: ActionRequest) = request.toResponse {
        response.add(getString(if (request.user.isFirstAccess) "welcome" else "welcome_back"))
    }

    @ForIntent("bye")
    fun bye(request: ActionRequest) = request.toResponse {
        response.add(getString("bye")).endConversation()
    }

    @ForIntent("schedule.next")
    fun scheduleNext(request: ActionRequest) = request.toResponse {
        val currentDate = "2018-01-01" // example
        val currentTime = "10:20" // example

        val dbSchedule = FireDB.scheduleByDay
        val dbSessions = FireDB.sessions
        val dbSpeakers = FireDB.speakers

        val schedule = dbSchedule[currentDate] ?: dbSchedule.values.first()
        val rooms = schedule.tracks.map { it.title }
        val timeslot = schedule.timeslots
                .sortedBy { it.startTime }
                .first { it.startTime > currentTime}
        val sessionsString = timeslot
                .sessions
                .mapIndexed { index, session ->
                    val talk = dbSessions.getValue(session?.items?.first()!!)
                    val speakersName = talk.speakers!!.map { dbSpeakers.getValue(it).name }
                    val speakersString = if (speakersName.size > 1) "${speakersName.joinToString(" e ")} presentano" else "${speakersName.first()} presenta"
                    "Nella sala ${rooms[index]}: $speakersString \"${talk.title}\""
                }
                .joinToString("\n")

        response.add("Il ${schedule.date} alle ${timeslot.startTime}:\n$sessionsString").endConversation()
    }

    @ForIntent("schedule.by-date")
    fun scheduleByDate(request: ActionRequest) = request.toResponse {
        response.add("schedule.by-date").endConversation()
    }
}

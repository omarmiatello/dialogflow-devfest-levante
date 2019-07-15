package com.github.omarmiatello.actions.data

import kotlinx.serialization.Serializable

// Schedule

@Serializable
data class Schedule(
        val date: String,
        val dateReadable: String,
        val timeslots: List<Timeslot>,
        val tracks: List<Track>
)

@Serializable
data class Timeslot(val endTime: String, val sessions: List<Session?>, val startTime: String)

@Serializable
data class Session(val items: List<String>, val extend: Long? = null)

@Serializable
data class Track(val title: String)

// Talk

@Serializable
data class Talk(
        val complexity: Complexity? = null,
        val description: String,
        val icon: String,
        val image: String,
        val language: Language? = null,
        val presentation: String? = null,
        val speakers: List<String>? = null,
        val tags: List<String>? = null,
        val title: String
)

enum class Complexity { Beginner, Intermediate }
enum class Language { English, Italian }

// Speaker

@Serializable
data class Speaker(
        val bio: String,
        val company: String,
        val companyLogo: String,
        val companyLogoUrl: String,
        val country: String,
        val featured: Boolean,
        val name: String,
        val order: Long,
        val photo: String,
        val photoUrl: String,
        val shortBio: String,
        val title: String,
        val badges: List<Badge>? = null,
        val socials: List<Social>? = null
)

@Serializable
data class Badge(val description: String, val name: BadgeName)

enum class BadgeName { gde, gdg, wtm }

@Serializable
data class Social(val icon: Icon, val link: String, val name: String)

enum class Icon { linkedin, twitter, website }
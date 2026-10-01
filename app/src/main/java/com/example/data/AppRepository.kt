package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("learn_bootstrap_prefs", Context.MODE_PRIVATE)

    private val _userProfile = MutableStateFlow(loadProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _bookmarkedLessonIds = MutableStateFlow(setOf("bs_1_1", "bs_3_1", "bs_4_1"))
    val bookmarkedLessonIds: StateFlow<Set<String>> = _bookmarkedLessonIds.asStateFlow()

    private fun loadProfile(): UserProfile {
        val name = prefs.getString("user_name", "Awiskar") ?: "Awiskar"
        val onboarded = prefs.getBoolean("is_onboarded", false)
        val exp = prefs.getString("experience", "Intermediate") ?: "Intermediate"
        val track = prefs.getString("active_track", "Bootstrap 5") ?: "Bootstrap 5"
        val streak = prefs.getInt("streak", 7)
        val minutes = prefs.getInt("minutes_today", 14)
        val darkTheme = prefs.getBoolean("dark_theme", true)
        val completedLessons = prefs.getStringSet("completed_lessons", setOf("bs_1_1", "bs_1_2", "bs_2_1")) ?: setOf("bs_1_1", "bs_1_2", "bs_2_1")

        return UserProfile(
            name = name,
            experienceLevel = exp,
            streakDays = streak,
            minutesPracticedToday = minutes,
            completedLessonIds = completedLessons,
            activeTrack = track,
            isOnboarded = onboarded,
            darkTheme = darkTheme
        )
    }

    fun completeOnboarding(name: String, experience: String, track: String, goals: List<String>, minutes: Int) {
        val updated = _userProfile.value.copy(
            name = if (name.isNotBlank()) name else "Learner",
            experienceLevel = experience,
            activeTrack = track,
            goals = goals,
            dailyGoalMinutes = minutes,
            isOnboarded = true
        )
        _userProfile.value = updated
        prefs.edit()
            .putString("user_name", updated.name)
            .putString("experience", updated.experienceLevel)
            .putString("active_track", track)
            .putInt("daily_goal", minutes)
            .putBoolean("is_onboarded", true)
            .apply()
    }

    fun markLessonCompleted(lessonId: String) {
        val updatedSet = _userProfile.value.completedLessonIds + lessonId
        _userProfile.value = _userProfile.value.copy(completedLessonIds = updatedSet)
        prefs.edit().putStringSet("completed_lessons", updatedSet).apply()
    }

    fun markChallengeCompleted(challengeId: String) {
        val updated = _userProfile.value.completedChallengeIds + challengeId
        _userProfile.value = _userProfile.value.copy(completedChallengeIds = updated)
    }

    fun toggleBookmark(lessonId: String) {
        val current = _bookmarkedLessonIds.value
        _bookmarkedLessonIds.value = if (current.contains(lessonId)) {
            current - lessonId
        } else {
            current + lessonId
        }
    }

    fun toggleDarkTheme() {
        val next = !_userProfile.value.darkTheme
        _userProfile.value = _userProfile.value.copy(darkTheme = next)
        prefs.edit().putBoolean("dark_theme", next).apply()
    }

    fun toggleActiveTrack() {
        val next = if (_userProfile.value.activeTrack == "Bootstrap 5") "React-Bootstrap" else "Bootstrap 5"
        _userProfile.value = _userProfile.value.copy(activeTrack = next)
        prefs.edit().putString("active_track", next).apply()
    }

    fun resetProgress() {
        _userProfile.value = _userProfile.value.copy(
            completedLessonIds = emptySet(),
            completedChallengeIds = emptySet(),
            completedProjectIds = emptySet()
        )
        prefs.edit().putStringSet("completed_lessons", emptySet()).apply()
    }
}

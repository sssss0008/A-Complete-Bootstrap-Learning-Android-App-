package com.example.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppRepository
import com.example.data.CurriculumData
import com.example.model.Lesson
import com.example.model.UserProfile
import com.example.ui.screens.aitutor.AiTutorScreen
import com.example.ui.screens.creator.CreatorScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.interview.InterviewScreen
import com.example.ui.screens.learn.LearnScreen
import com.example.ui.screens.learn.LessonDetailScreen
import com.example.ui.screens.onboarding.OnboardingFlow
import com.example.ui.screens.playground.CodeLabScreen
import com.example.ui.screens.practice.PracticeScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.reference.ReferenceScreen
import com.example.ui.screens.settings.SettingsScreen
import com.example.ui.theme.*

sealed class Screen {
    object Onboarding : Screen()
    object Home : Screen()
    object Learn : Screen()
    object CodeLab : Screen()
    object Practice : Screen()
    object Profile : Screen()

    // Sub-screens
    data class LessonDetail(val lesson: Lesson) : Screen()
    object AiTutor : Screen()
    object Interview : Screen()
    object Reference : Screen()
    object Creator : Screen()
    object Settings : Screen()
}

enum class NavTab(val title: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Dashboard),
    LEARN("Learn", Icons.Default.School),
    CODE("Grid Lab", Icons.Default.GridView),
    PRACTICE("Practice", Icons.Default.Terminal),
    PROFILE("Profile", Icons.Default.Person)
}

@Composable
fun MainAppContainer(
    repository: AppRepository
) {
    val userProfile by repository.userProfile.collectAsState()
    val bookmarkedLessonIds by repository.bookmarkedLessonIds.collectAsState()

    var currentScreen by remember {
        mutableStateOf<Screen>(if (userProfile.isOnboarded) Screen.Home else Screen.Onboarding)
    }
    var activeTab by remember { mutableStateOf(NavTab.HOME) }

    // Handle Android Back Press cleanly
    BackHandler(enabled = currentScreen !is Screen.Home && currentScreen !is Screen.Onboarding) {
        when (currentScreen) {
            is Screen.LessonDetail -> currentScreen = Screen.Learn
            Screen.AiTutor, Screen.Interview, Screen.Reference, Screen.Creator, Screen.Settings -> {
                currentScreen = when (activeTab) {
                    NavTab.HOME -> Screen.Home
                    NavTab.LEARN -> Screen.Learn
                    NavTab.CODE -> Screen.CodeLab
                    NavTab.PRACTICE -> Screen.Practice
                    NavTab.PROFILE -> Screen.Profile
                }
            }
            Screen.Learn, Screen.CodeLab, Screen.Practice, Screen.Profile -> {
                activeTab = NavTab.HOME
                currentScreen = Screen.Home
            }
            else -> {}
        }
    }

    MyApplicationTheme(darkTheme = userProfile.darkTheme) {
        if (currentScreen is Screen.Onboarding) {
            OnboardingFlow(
                onComplete = { name, exp, track, goals, minutes ->
                    repository.completeOnboarding(name, exp, track, goals, minutes)
                    currentScreen = Screen.Home
                    activeTab = NavTab.HOME
                }
            )
            return@MyApplicationTheme
        }

        val showBottomNav = currentScreen in listOf(
            Screen.Home,
            Screen.Learn,
            Screen.CodeLab,
            Screen.Practice,
            Screen.Profile
        )

        Scaffold(
            containerColor = BsDark950,
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                if (showBottomNav) {
                    NavigationBar(
                        containerColor = BsDark900,
                        contentColor = BsTextMuted,
                        tonalElevation = 8.dp,
                        modifier = Modifier.navigationBarsPadding()
                    ) {
                        NavTab.values().forEach { tab ->
                            val isSelected = activeTab == tab
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    activeTab = tab
                                    currentScreen = when (tab) {
                                        NavTab.HOME -> Screen.Home
                                        NavTab.LEARN -> Screen.Learn
                                        NavTab.CODE -> Screen.CodeLab
                                        NavTab.PRACTICE -> Screen.Practice
                                        NavTab.PROFILE -> Screen.Profile
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = tab.icon,
                                        contentDescription = tab.title,
                                        tint = if (isSelected) BootstrapPurpleLight else BsTextMuted
                                    )
                                },
                                label = {
                                    Text(
                                        text = tab.title,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) BootstrapPurpleLight else BsTextMuted
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = BsDark800
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (val screen = currentScreen) {
                    Screen.Home -> {
                        HomeScreen(
                            userProfile = userProfile,
                            onNavigateToLearn = {
                                activeTab = NavTab.LEARN
                                currentScreen = Screen.Learn
                            },
                            onNavigateToCodeLab = {
                                activeTab = NavTab.CODE
                                currentScreen = Screen.CodeLab
                            },
                            onNavigateToPractice = {
                                activeTab = NavTab.PRACTICE
                                currentScreen = Screen.Practice
                            },
                            onNavigateToProjects = {
                                activeTab = NavTab.PRACTICE
                                currentScreen = Screen.Practice
                            },
                            onNavigateToReference = { currentScreen = Screen.Reference },
                            onNavigateToAiTutor = { currentScreen = Screen.AiTutor },
                            onOpenLesson = { lessonId ->
                                val target = CurriculumData.levels.flatMap { it.lessons }.find { it.id == lessonId }
                                if (target != null) currentScreen = Screen.LessonDetail(target)
                            },
                            onOpenCreator = { currentScreen = Screen.Creator },
                            onToggleTrack = { repository.toggleActiveTrack() }
                        )
                    }
                    Screen.Learn -> {
                        LearnScreen(
                            completedLessonIds = userProfile.completedLessonIds,
                            onSelectLesson = { lesson ->
                                currentScreen = Screen.LessonDetail(lesson)
                            }
                        )
                    }
                    Screen.CodeLab -> {
                        CodeLabScreen()
                    }
                    Screen.Practice -> {
                        PracticeScreen(
                            completedChallengeIds = userProfile.completedChallengeIds,
                            onCompleteChallenge = { challengeId ->
                                repository.markChallengeCompleted(challengeId)
                            }
                        )
                    }
                    Screen.Profile -> {
                        ProfileScreen(
                            userProfile = userProfile,
                            onOpenCreator = { currentScreen = Screen.Creator },
                            onOpenSettings = { currentScreen = Screen.Settings },
                            onOpenReference = { currentScreen = Screen.Reference }
                        )
                    }
                    is Screen.LessonDetail -> {
                        val isDone = userProfile.completedLessonIds.contains(screen.lesson.id)
                        val isBookmarked = bookmarkedLessonIds.contains(screen.lesson.id)

                        LessonDetailScreen(
                            lesson = screen.lesson,
                            isCompleted = isDone,
                            isBookmarked = isBookmarked,
                            onBack = { currentScreen = Screen.Learn },
                            onToggleBookmark = { repository.toggleBookmark(screen.lesson.id) },
                            onCompleteLesson = {
                                repository.markLessonCompleted(screen.lesson.id)
                            },
                            onOpenPlayground = {
                                activeTab = NavTab.CODE
                                currentScreen = Screen.CodeLab
                            }
                        )
                    }
                    Screen.AiTutor -> {
                        AiTutorScreen(
                            onBack = {
                                currentScreen = when (activeTab) {
                                    NavTab.HOME -> Screen.Home
                                    NavTab.LEARN -> Screen.Learn
                                    NavTab.CODE -> Screen.CodeLab
                                    NavTab.PRACTICE -> Screen.Practice
                                    NavTab.PROFILE -> Screen.Profile
                                }
                            }
                        )
                    }
                    Screen.Interview -> {
                        InterviewScreen(
                            onBack = { currentScreen = Screen.Practice }
                        )
                    }
                    Screen.Reference -> {
                        ReferenceScreen(
                            onBack = {
                                currentScreen = when (activeTab) {
                                    NavTab.HOME -> Screen.Home
                                    NavTab.LEARN -> Screen.Learn
                                    NavTab.CODE -> Screen.CodeLab
                                    NavTab.PRACTICE -> Screen.Practice
                                    NavTab.PROFILE -> Screen.Profile
                                }
                            }
                        )
                    }
                    Screen.Creator -> {
                        CreatorScreen(
                            onBack = {
                                currentScreen = when (activeTab) {
                                    NavTab.HOME -> Screen.Home
                                    NavTab.LEARN -> Screen.Learn
                                    NavTab.CODE -> Screen.CodeLab
                                    NavTab.PRACTICE -> Screen.Practice
                                    NavTab.PROFILE -> Screen.Profile
                                }
                            }
                        )
                    }
                    Screen.Settings -> {
                        SettingsScreen(
                            userProfile = userProfile,
                            onBack = { currentScreen = Screen.Profile },
                            onToggleTheme = { repository.toggleDarkTheme() },
                            onToggleTrack = { repository.toggleActiveTrack() },
                            onResetProgress = { repository.resetProgress() }
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}

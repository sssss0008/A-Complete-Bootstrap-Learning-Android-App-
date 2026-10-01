package com.example.model

enum class VisualType {
    GRID_SYSTEM,
    BREAKPOINTS_FLOW,
    UTILITY_CLASSES,
    COMPONENT_LAB,
    REACT_BOOTSTRAP_FLOW,
    FORM_VALIDATION,
    RESPONSIVE_NAVBAR,
    COMPONENT_TREE,
    STATE_LIFECYCLE
}

data class UserProfile(
    val name: String = "Frontend Learner",
    val experienceLevel: String = "Beginner",
    val frameworkExperience: String = "Know the basics",
    val goals: List<String> = listOf("Master Bootstrap 5", "Build Responsive Websites", "Learn React-Bootstrap"),
    val dailyGoalMinutes: Int = 20,
    val minutesPracticedToday: Int = 14,
    val streakDays: Int = 7,
    val currentLevel: Int = 2,
    val completedLessonIds: Set<String> = setOf("bs_1_1", "bs_1_2", "bs_2_1"),
    val completedChallengeIds: Set<String> = setOf("ch_bs_1"),
    val completedProjectIds: Set<String> = emptySet(),
    val unlockedBadgeIds: Set<String> = setOf("first_grid", "streak_7"),
    val activeTrack: String = "Bootstrap 5", // "Bootstrap 5" or "React-Bootstrap"
    val isOnboarded: Boolean = false,
    val darkTheme: Boolean = true
)

data class Lesson(
    val id: String,
    val levelNumber: Int,
    val title: String,
    val subtitle: String,
    val durationMin: Int,
    val summary: String,
    val conceptExplanation: String,
    val codeSnippet: String,
    val visualType: VisualType,
    val interactiveTask: String,
    val category: String = "Bootstrap",
    val quizQuestion: String? = null,
    val quizOptions: List<String> = emptyList(),
    val correctQuizIndex: Int = 0,
    val quizExplanation: String? = null
)

data class CurriculumLevel(
    val levelNumber: Int,
    val title: String,
    val category: String, // Foundation, Grid, Utilities, Components, React-Bootstrap, Architecture
    val description: String,
    val lessons: List<Lesson>
)

data class BootstrapUtilityItem(
    val id: String,
    val name: String,
    val category: String, // Spacing, Flexbox, Colors, Display, Borders, Shadows
    val syntax: String,
    val summary: String,
    val explanation: String,
    val codeExample: String,
    val commonMistakes: List<String>,
    val realWorldCase: String
)

data class UIComponentItem(
    val id: String,
    val name: String,
    val category: String, // Buttons, Cards, Navigation, Modals, Forms, Alerts
    val description: String,
    val htmlSnippet: String,
    val reactBootstrapSnippet: String,
    val classNames: List<String>,
    val accessibilityNotes: String
)

data class CodingChallenge(
    val id: String,
    val title: String,
    val category: String,
    val difficulty: String, // Beginner, Intermediate, Advanced
    val problem: String,
    val requirements: List<String>,
    val starterCode: String,
    val solutionCode: String,
    val testExpectations: List<String>,
    val hint: String,
    val solutionExplanation: String
)

data class ProjectItem(
    val id: String,
    val title: String,
    val category: String,
    val difficulty: String,
    val estimatedHours: String,
    val description: String,
    val requirements: List<String>,
    val starterCode: String,
    val milestones: List<String>,
    val tags: List<String>
)

data class InterviewItem(
    val id: String,
    val question: String,
    val category: String,
    val type: String, // MCQ, Conceptual, Scenario
    val options: List<String> = emptyList(),
    val correctIndex: Int = 0,
    val detailedAnswer: String,
    val keyTakeaways: List<String>
)

data class GlossaryItem(
    val term: String,
    val category: String,
    val definition: String,
    val example: String
)

data class CheatSheetItem(
    val title: String,
    val category: String,
    val bullets: List<String>,
    val quickSnippet: String
)

data class AchievementBadge(
    val id: String,
    val title: String,
    val description: String,
    val iconCategory: String, // GRID, COMPONENT, STREAK, PROJECT, UTILITY
    val isUnlocked: Boolean = false
)

data class TerminalLog(
    val id: Long = System.currentTimeMillis(),
    val level: String, // "LOG", "WARN", "ERROR", "INFO"
    val message: String
)

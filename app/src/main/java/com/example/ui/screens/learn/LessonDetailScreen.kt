package com.example.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Lesson
import com.example.model.VisualType
import com.example.ui.components.*
import com.example.ui.theme.*

@Composable
fun LessonDetailScreen(
    lesson: Lesson,
    isCompleted: Boolean,
    isBookmarked: Boolean,
    onBack: () -> Unit,
    onToggleBookmark: () -> Unit,
    onCompleteLesson: () -> Unit,
    onOpenPlayground: () -> Unit
) {
    var selectedQuizOption by remember { mutableStateOf<Int?>(null) }
    var quizSubmitted by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BsDark950,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onToggleBookmark) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) BootstrapPurpleLight else BsTextMuted
                        )
                    }

                    IconButton(onClick = onOpenPlayground) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = "Open in Grid Lab",
                            tint = BootstrapPurpleLight
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 40.dp)
        ) {
            // Header
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = BootstrapPurple.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "LEVEL ${lesson.levelNumber}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BootstrapPurpleLight,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${lesson.durationMin} min read & practice",
                            fontSize = 11.sp,
                            color = BsTextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = lesson.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = lesson.subtitle,
                        fontSize = 13.sp,
                        color = BsTextLight
                    )

                    Spacer(modifier = Modifier.height(18.dp))
                }
            }

            // Step 1: Explain Concept
            item {
                SectionCard(title = "1. EXPLAIN CONCEPT") {
                    Text(
                        text = lesson.conceptExplanation,
                        fontSize = 13.sp,
                        color = BsTextLight,
                        lineHeight = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Step 2: Interactive Visualizer
            item {
                SectionCard(title = "2. INTERACTIVE VISUALIZER") {
                    when (lesson.visualType) {
                        VisualType.GRID_SYSTEM -> BootstrapGridVisualizer()
                        VisualType.BREAKPOINTS_FLOW -> BreakpointVisualizer()
                        VisualType.UTILITY_CLASSES -> UtilityClassesVisualizer()
                        else -> BootstrapGridVisualizer()
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Step 3: Show Code
            item {
                SectionCard(title = "3. CODE IMPLEMENTATION") {
                    CodeBlock(
                        code = lesson.codeSnippet,
                        language = if (lesson.category == "React-Bootstrap") "React JSX" else "HTML + CSS"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Step 4: Interactive Challenge Quiz
            if (lesson.quizQuestion != null && lesson.quizOptions.isNotEmpty()) {
                item {
                    SectionCard(title = "4. KNOWLEDGE CHECK") {
                        Text(
                            text = lesson.quizQuestion,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        lesson.quizOptions.forEachIndexed { index, option ->
                            val isSelected = selectedQuizOption == index
                            val isCorrect = index == lesson.correctQuizIndex

                            val borderCol = when {
                                !quizSubmitted && isSelected -> BootstrapPurpleLight
                                quizSubmitted && isCorrect -> BootstrapSuccess
                                quizSubmitted && isSelected && !isCorrect -> BootstrapDanger
                                else -> BsDark800
                            }

                            val bgCol = when {
                                !quizSubmitted && isSelected -> BsDark800
                                quizSubmitted && isCorrect -> BootstrapSuccess.copy(alpha = 0.15f)
                                quizSubmitted && isSelected && !isCorrect -> BootstrapDanger.copy(alpha = 0.15f)
                                else -> BsDark950
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(bgCol)
                                    .border(1.dp, borderCol, RoundedCornerShape(8.dp))
                                    .clickable(enabled = !quizSubmitted) {
                                        selectedQuizOption = index
                                    }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, borderCol, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isSelected || (quizSubmitted && isCorrect)) {
                                        Box(
                                            modifier = Modifier
                                                .size(10.dp)
                                                .clip(CircleShape)
                                                .background(borderCol)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = option,
                                    fontSize = 12.sp,
                                    color = if (isSelected || (quizSubmitted && isCorrect)) Color.White else BsTextLight
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        if (!quizSubmitted) {
                            Button(
                                onClick = { quizSubmitted = true },
                                enabled = selectedQuizOption != null,
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Check Answer", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BsDark950)
                            }
                        } else {
                            val isUserCorrect = selectedQuizOption == lesson.correctQuizIndex
                            Surface(
                                color = if (isUserCorrect) BootstrapSuccess.copy(alpha = 0.15f) else BootstrapDanger.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(8.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, if (isUserCorrect) BootstrapSuccess else BootstrapDanger),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = if (isUserCorrect) "✨ Correct Answer!" else "❌ Incorrect",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = if (isUserCorrect) BootstrapSuccess else BootstrapDanger
                                    )
                                    if (lesson.quizExplanation != null) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = lesson.quizExplanation,
                                            fontSize = 11.sp,
                                            color = BsTextLight
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            // Complete Lesson Button
            item {
                Button(
                    onClick = onCompleteLesson,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCompleted) BootstrapSuccess else BootstrapPurpleLight
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Done,
                        contentDescription = null,
                        tint = BsDark950
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isCompleted) "Completed (+50 XP)" else "Mark Lesson as Completed",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BsDark950
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BootstrapPurpleLight,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

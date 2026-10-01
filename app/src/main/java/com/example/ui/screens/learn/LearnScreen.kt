package com.example.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import com.example.data.CurriculumData
import com.example.model.CurriculumLevel
import com.example.model.Lesson
import com.example.ui.theme.*

@Composable
fun LearnScreen(
    completedLessonIds: Set<String>,
    onSelectLesson: (Lesson) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val categories = listOf("All", "Foundation", "Grid", "Utilities", "Components", "React-Bootstrap")

    val filteredLevels = remember(selectedCategory, searchQuery) {
        CurriculumData.levels.filter { level ->
            (selectedCategory == "All" || level.category.equals(selectedCategory, ignoreCase = true)) &&
            (searchQuery.isBlank() || level.title.contains(searchQuery, ignoreCase = true) ||
             level.lessons.any { it.title.contains(searchQuery, ignoreCase = true) || it.summary.contains(searchQuery, ignoreCase = true) })
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BsDark950),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)) {
                Text(
                    text = "Bootstrap Curriculum",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Master Grid, Breakpoints, Components, Utilities & React-Bootstrap",
                    fontSize = 12.sp,
                    color = BootstrapPurpleLight
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Search field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search grid, col-md, navbar, cards, utilities...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = BsTextMuted) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear", tint = BsTextMuted)
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BootstrapPurpleLight,
                        unfocusedBorderColor = BsDark800,
                        focusedContainerColor = BsDark900,
                        unfocusedContainerColor = BsDark900,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Category chips
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories) { cat ->
                        val isSelected = selectedCategory == cat
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) BootstrapPurpleLight else BsDark900,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) BootstrapPurpleLight else BsDark800
                            ),
                            modifier = Modifier.clickable { selectedCategory = cat }
                        ) {
                            Text(
                                text = cat,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) BsDark950 else BsTextLight,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                            )
                        }
                    }
                }
            }
        }

        items(filteredLevels) { level ->
            CurriculumLevelCard(
                level = level,
                completedLessonIds = completedLessonIds,
                onSelectLesson = onSelectLesson
            )
        }
    }
}

@Composable
fun CurriculumLevelCard(
    level: CurriculumLevel,
    completedLessonIds: Set<String>,
    onSelectLesson: (Lesson) -> Unit
) {
    var isExpanded by remember { mutableStateOf(level.levelNumber <= 4) }
    val totalLessons = level.lessons.size
    val completedCount = level.lessons.count { completedLessonIds.contains(it.id) }
    val isLevelDone = completedCount == totalLessons && totalLessons > 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isLevelDone) BootstrapSuccess.copy(alpha = 0.5f) else BsDark800
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isLevelDone -> BootstrapSuccess
                                    level.levelNumber <= 3 -> BootstrapPurpleLight
                                    else -> BsDark800
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isLevelDone) "✓" else "${level.levelNumber}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isLevelDone || level.levelNumber <= 3) BsDark950 else Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "LEVEL ${level.levelNumber}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BootstrapPurpleLight,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = BsDark800,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = level.category,
                                    fontSize = 9.sp,
                                    color = BsTextMuted,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                        Text(
                            text = level.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Toggle",
                        tint = BsTextMuted
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = level.description,
                    fontSize = 12.sp,
                    color = BsTextMuted,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Lessons list
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    level.lessons.forEach { lesson ->
                        val isDone = completedLessonIds.contains(lesson.id)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(BsDark950)
                                .clickable { onSelectLesson(lesson) }
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                    contentDescription = null,
                                    tint = if (isDone) BootstrapSuccess else BsTextMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = lesson.title,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (isDone) BsTextMuted else Color.White
                                    )
                                    Text(
                                        text = "${lesson.durationMin} min • ${lesson.visualType.name.replace("_", " ")}",
                                        fontSize = 10.sp,
                                        color = BsTextMuted
                                    )
                                }
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Start lesson",
                                tint = BootstrapPurpleLight,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

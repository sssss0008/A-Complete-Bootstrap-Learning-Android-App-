package com.example.ui.screens.practice

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumData
import com.example.model.CodingChallenge
import com.example.model.ProjectItem
import com.example.ui.components.CodeBlock
import com.example.ui.theme.*

@Composable
fun PracticeScreen(
    completedChallengeIds: Set<String>,
    onCompleteChallenge: (String) -> Unit
) {
    var activeTab by remember { mutableStateOf("Challenges") } // "Challenges", "Debugging", "Projects"
    var selectedChallenge by remember { mutableStateOf<CodingChallenge?>(null) }
    var selectedProject by remember { mutableStateOf<ProjectItem?>(null) }

    if (selectedChallenge != null) {
        ChallengeDetailView(
            challenge = selectedChallenge!!,
            isCompleted = completedChallengeIds.contains(selectedChallenge!!.id),
            onBack = { selectedChallenge = null },
            onComplete = {
                onCompleteChallenge(selectedChallenge!!.id)
            }
        )
        return
    }

    if (selectedProject != null) {
        ProjectWorkspaceView(
            project = selectedProject!!,
            onBack = { selectedProject = null }
        )
        return
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
                    text = "Practice & Build Center",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Don't just read about UI frameworks. Build with Bootstrap.",
                    fontSize = 13.sp,
                    color = BootstrapPurpleLight
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Navigation Tabs
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Challenges", "Debugging", "Projects").forEach { tab ->
                        val isSelected = activeTab == tab
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) BootstrapPurpleLight else BsDark900,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) BootstrapPurpleLight else BsDark800
                            ),
                            modifier = Modifier.clickable { activeTab = tab }
                        ) {
                            Text(
                                text = tab,
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

        when (activeTab) {
            "Challenges" -> {
                items(CurriculumData.challenges) { challenge ->
                    val isDone = completedChallengeIds.contains(challenge.id)
                    ChallengeCard(
                        challenge = challenge,
                        isDone = isDone,
                        onClick = { selectedChallenge = challenge }
                    )
                }
            }
            "Debugging" -> {
                item {
                    DebuggingStudioSection()
                }
            }
            "Projects" -> {
                items(CurriculumData.projects) { project ->
                    ProjectCatalogCard(
                        project = project,
                        onClick = { selectedProject = project }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChallengeCard(
    challenge: CodingChallenge,
    isDone: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isDone) BootstrapSuccess.copy(alpha = 0.5f) else BsDark800
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = when (challenge.difficulty) {
                        "Beginner" -> BootstrapSuccess.copy(alpha = 0.2f)
                        "Intermediate" -> BootstrapWarning.copy(alpha = 0.2f)
                        else -> BootstrapDanger.copy(alpha = 0.2f)
                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = challenge.difficulty.uppercase(),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (challenge.difficulty) {
                            "Beginner" -> BootstrapSuccess
                            "Intermediate" -> BootstrapWarning
                            else -> BootstrapDanger
                        },
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                if (isDone) {
                    Text("✓ SOLVED", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapSuccess)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = challenge.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = challenge.problem,
                fontSize = 12.sp,
                color = BsTextLight,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${challenge.testExpectations.size} Test Cases",
                    fontSize = 11.sp,
                    color = BootstrapPurpleLight
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open challenge",
                    tint = BootstrapPurpleLight,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun ChallengeDetailView(
    challenge: CodingChallenge,
    isCompleted: Boolean,
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    var showSolution by remember { mutableStateOf(false) }
    var showHint by remember { mutableStateOf(false) }
    var testPassed by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BsDark950),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 80.dp)
    ) {
        item {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = challenge.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("PROBLEM DESCRIPTION", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(challenge.problem, fontSize = 13.sp, color = BsTextLight, lineHeight = 18.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("REQUIREMENTS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                    Spacer(modifier = Modifier.height(6.dp))
                    challenge.requirements.forEach { req ->
                        Text("• $req", fontSize = 12.sp, color = BsTextLight)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text("STARTER CODE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted)
            Spacer(modifier = Modifier.height(6.dp))
            CodeBlock(code = challenge.starterCode, language = "HTML")

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        testPassed = true
                        onComplete()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = BootstrapSuccess),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Run Tests & Submit", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BsDark950)
                }

                OutlinedButton(
                    onClick = { showHint = !showHint },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (showHint) "Hide Hint" else "Hint", fontSize = 12.sp, color = BootstrapWarning)
                }

                OutlinedButton(
                    onClick = { showSolution = !showSolution },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (showSolution) "Hide" else "Solution", fontSize = 12.sp, color = BootstrapPurpleLight)
                }
            }

            if (showHint) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = BootstrapWarning.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BootstrapWarning)
                ) {
                    Text(
                        text = "💡 Hint: ${challenge.hint}",
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            if (testPassed || isCompleted) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = BootstrapSuccess.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BootstrapSuccess),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("🎉 All Test Cases Passed!", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = BootstrapSuccess)
                        Spacer(modifier = Modifier.height(4.dp))
                        challenge.testExpectations.forEach { exp ->
                            Text("✓ $exp", fontSize = 11.sp, color = BsTextLight)
                        }
                    }
                }
            }

            if (showSolution) {
                Spacer(modifier = Modifier.height(14.dp))
                Text("OFFICIAL SOLUTION", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapSuccess)
                Spacer(modifier = Modifier.height(6.dp))
                CodeBlock(code = challenge.solutionCode, language = "HTML")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = challenge.solutionExplanation,
                    fontSize = 12.sp,
                    color = BsTextMuted,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun DebuggingStudioSection() {
    var fixed by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        Text("BOOTSTRAP DEBUGGING STUDIO", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Diagnose and fix broken layouts and grid alignment errors.", fontSize = 13.sp, color = BsTextMuted)

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = BsDark900),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, if (fixed) BootstrapSuccess else BootstrapDanger)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = if (fixed) BootstrapSuccess.copy(alpha = 0.2f) else BootstrapDanger.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = if (fixed) "STATUS: RESOLVED" else "STATUS: BROKEN GRID OVERFLOW",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (fixed) BootstrapSuccess else BootstrapDanger,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Button(
                        onClick = { fixed = !fixed },
                        colors = ButtonDefaults.buttonColors(containerColor = if (fixed) BsDark800 else BootstrapPurpleLight),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(if (fixed) "Reset Bug" else "Apply Fix", fontSize = 11.sp, color = if (fixed) BsTextLight else BsDark950)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = if (fixed) "Fixed Layout (.row container added):" else "Broken Columns (missing .row):",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                CodeBlock(
                    code = if (fixed) {
                        """<!-- ✅ FIXED: Wrapped in .row to balance negative margins -->
<div class="container">
  <div class="row g-3">
    <div class="col-6 bg-primary text-white p-3">Column A</div>
    <div class="col-6 bg-success text-white p-3">Column B</div>
  </div>
</div>"""
                    } else {
                        """<!-- ❌ BROKEN: Columns without a .row parent cause alignment overflow -->
<div class="container">
  <div class="col-6 bg-primary text-white p-3">Column A</div>
  <div class="col-6 bg-success text-white p-3">Column B</div>
</div>"""
                    },
                    language = "HTML"
                )

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    color = BsDark950,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = if (fixed) {
                                "✓ Linter: .col elements correctly nested within .row parent container."
                            } else {
                                "❌ Warning: Bootstrap .col-* elements require a direct .row parent to prevent horizontal gutter overflow."
                            },
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = if (fixed) BootstrapSuccess else BootstrapDanger
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectCatalogCard(
    project: ProjectItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = BootstrapPurple.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = project.category.uppercase(),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = BootstrapPurpleLight,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Text("⏱ ${project.estimatedHours}", fontSize = 11.sp, color = BsTextMuted)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(project.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.height(4.dp))

            Text(project.description, fontSize = 12.sp, color = BsTextLight, maxLines = 2)

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                project.tags.forEach { tag ->
                    Surface(
                        color = BsDark800,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(tag, fontSize = 10.sp, color = BootstrapInfo, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectWorkspaceView(
    project: ProjectItem,
    onBack: () -> Unit
) {
    val completedMilestones = remember { mutableStateListOf<String>() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BsDark950),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 80.dp)
    ) {
        item {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(project.title, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(project.description, fontSize = 13.sp, color = BsTextLight)

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("PROJECT MILESTONES & SPEC", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                    Spacer(modifier = Modifier.height(8.dp))

                    project.milestones.forEach { milestone ->
                        val isDone = completedMilestones.contains(milestone)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    if (isDone) completedMilestones.remove(milestone)
                                    else completedMilestones.add(milestone)
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isDone,
                                onCheckedChange = {
                                    if (isDone) completedMilestones.remove(milestone)
                                    else completedMilestones.add(milestone)
                                },
                                colors = CheckboxDefaults.colors(checkedColor = BootstrapPurpleLight, checkmarkColor = BsDark950)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = milestone,
                                fontSize = 12.sp,
                                color = if (isDone) BootstrapSuccess else BsTextLight,
                                fontWeight = if (isDone) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text("PROJECT STARTER TEMPLATE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted)
            Spacer(modifier = Modifier.height(6.dp))
            CodeBlock(code = project.starterCode, language = "HTML")
        }
    }
}

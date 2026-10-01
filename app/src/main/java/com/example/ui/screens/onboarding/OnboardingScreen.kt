package com.example.ui.screens.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.ui.components.BootstrapLogoVisualizer
import com.example.ui.components.CreatorVectorAvatar
import com.example.ui.theme.*

@Composable
fun OnboardingFlow(
    onComplete: (name: String, exp: String, track: String, goals: List<String>, minutes: Int) -> Unit
) {
    var step by remember { mutableStateOf(1) }
    var nameInput by remember { mutableStateOf("Awiskar") }
    var selectedExp by remember { mutableStateOf("Intermediate") }
    var selectedTrack by remember { mutableStateOf("Bootstrap 5") }
    val selectedGoals = remember {
        mutableStateListOf(
            "Master the 12-Column Grid",
            "Build Responsive Websites",
            "Learn React-Bootstrap"
        )
    }
    var selectedSchedule by remember { mutableStateOf("20 minutes/day") }

    Scaffold(
        containerColor = BsDark950,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            if (step > 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { if (step > 1) step-- }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        (1..7).forEach { s ->
                            Box(
                                modifier = Modifier
                                    .size(if (s == step) 16.dp else 8.dp, 6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(if (s <= step) BootstrapPurpleLight else BsDark800)
                            )
                        }
                    }

                    Text(
                        text = "$step / 7",
                        fontSize = 12.sp,
                        color = BsTextMuted,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            when (step) {
                1 -> ScreenWelcome(onStart = { step = 2 })
                2 -> ScreenName(
                    name = nameInput,
                    onNameChange = { nameInput = it },
                    onNext = { step = 3 }
                )
                3 -> ScreenExperience(
                    selected = selectedExp,
                    onSelect = { selectedExp = it },
                    onNext = { step = 4 }
                )
                4 -> ScreenTrack(
                    selected = selectedTrack,
                    onSelect = { selectedTrack = it },
                    onNext = { step = 5 }
                )
                5 -> ScreenGoals(
                    selectedGoals = selectedGoals,
                    onToggleGoal = { goal ->
                        if (selectedGoals.contains(goal)) selectedGoals.remove(goal)
                        else selectedGoals.add(goal)
                    },
                    onNext = { step = 6 }
                )
                6 -> ScreenSchedule(
                    selected = selectedSchedule,
                    onSelect = { selectedSchedule = it },
                    onNext = { step = 7 }
                )
                7 -> ScreenPersonalizedRoadmap(
                    name = nameInput,
                    exp = selectedExp,
                    track = selectedTrack,
                    onStartRoadmap = {
                        val minutes = when (selectedSchedule) {
                            "10 minutes/day" -> 10
                            "20 minutes/day" -> 20
                            "30 minutes/day" -> 30
                            "1 hour/day" -> 60
                            else -> 20
                        }
                        onComplete(nameInput, selectedExp, selectedTrack, selectedGoals.toList(), minutes)
                    }
                )
            }
        }
    }
}

@Composable
private fun ScreenWelcome(onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BootstrapLogoVisualizer(size = 110)

            Spacer(modifier = Modifier.height(28.dp))

            Surface(
                color = BsDark900,
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
            ) {
                Text(
                    text = "Created by Awiskar Acharya",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BootstrapPurpleLight,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Welcome to\nLearn Bootstrap",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Master the 12-column grid, responsive breakpoints, flexbox utilities, and React-Bootstrap components.",
                fontSize = 14.sp,
                color = BsTextLight,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Learn • Visualize • Grid • Style • Build • Master",
                fontSize = 12.sp,
                color = BootstrapSuccess,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Start Learning Bootstrap",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = BsDark950
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = BsDark950)
        }
    }
}

@Composable
private fun ScreenName(
    name: String,
    onNameChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "What should we call you?",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Personalizing your developer workspace and certificate.",
                fontSize = 14.sp,
                color = BsTextMuted
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Your Name") },
                placeholder = { Text("e.g. Awiskar") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BootstrapPurpleLight,
                    unfocusedBorderColor = BsDark700,
                    focusedLabelColor = BootstrapPurpleLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
        }

        Button(
            onClick = onNext,
            enabled = name.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BsDark950)
        }
    }
}

@Composable
private fun ScreenExperience(
    selected: String,
    onSelect: (String) -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        "Complete Beginner" to "New to HTML and CSS",
        "Beginner" to "Know basic HTML tags and CSS selectors",
        "Intermediate" to "Comfortable with CSS flexbox and responsive media queries",
        "Experienced Developer" to "Building web applications wanting fast UI prototyping"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "How much web experience do you have?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We will calibrate the lesson depth for you.",
                fontSize = 13.sp,
                color = BsTextMuted
            )

            Spacer(modifier = Modifier.height(24.dp))

            options.forEach { (title, subtitle) ->
                SelectableOptionCard(
                    title = title,
                    subtitle = subtitle,
                    isSelected = selected == title,
                    onClick = { onSelect(title) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BsDark950)
        }
    }
}

@Composable
private fun ScreenTrack(
    selected: String,
    onSelect: (String) -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        "Bootstrap 5" to "Pure HTML5, CSS utilities, and Vanilla JS components (Recommended)",
        "React-Bootstrap" to "Type-safe React components (<Container>, <Row>, <Col>, <Button>)",
        "Both Combined" to "Learn pure Bootstrap 5 first, then bridge into React"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Choose your primary learning track",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "You can toggle tracks at any time in the app.",
                fontSize = 13.sp,
                color = BsTextMuted
            )

            Spacer(modifier = Modifier.height(24.dp))

            options.forEach { (title, subtitle) ->
                SelectableOptionCard(
                    title = title,
                    subtitle = subtitle,
                    isSelected = selected == title,
                    onClick = { onSelect(title) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BsDark950)
        }
    }
}

@Composable
private fun ScreenGoals(
    selectedGoals: List<String>,
    onToggleGoal: (String) -> Unit,
    onNext: () -> Unit
) {
    val allGoals = listOf(
        "Master the 12-Column Grid",
        "Build Responsive Websites",
        "Master Flexbox & Spacing Utilities",
        "Build Mobile-Friendly Navbars",
        "Create Modal Dialogs & Lightboxes",
        "Design Landing Pages Fast",
        "Learn React-Bootstrap",
        "Prepare for Frontend Interviews"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "What are your Bootstrap goals?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Select all that apply to your roadmap.",
                fontSize = 13.sp,
                color = BsTextMuted
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allGoals) { goal ->
                    val isSelected = selectedGoals.contains(goal)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) BsDark800 else BsDark900)
                            .border(1.dp, if (isSelected) BootstrapPurpleLight else BsDark800, RoundedCornerShape(10.dp))
                            .clickable { onToggleGoal(goal) }
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = { onToggleGoal(goal) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = BootstrapPurpleLight,
                                checkmarkColor = BsDark950,
                                uncheckedColor = BsDark700
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = goal,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else BsTextLight
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onNext,
            enabled = selectedGoals.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BsDark950)
        }
    }
}

@Composable
private fun ScreenSchedule(
    selected: String,
    onSelect: (String) -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        "10 minutes/day" to "Quick daily grid and component concept",
        "20 minutes/day" to "Recommended: 1 concept + 1 challenge",
        "30 minutes/day" to "Rapid learning with hands-on projects",
        "Flexible" to "Practice at your own leisure"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Choose your learning schedule",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Consistent practice turns responsive design into second nature.",
                fontSize = 13.sp,
                color = BsTextMuted
            )

            Spacer(modifier = Modifier.height(24.dp))

            options.forEach { (title, subtitle) ->
                SelectableOptionCard(
                    title = title,
                    subtitle = subtitle,
                    isSelected = selected == title,
                    onClick = { onSelect(title) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Generate My Roadmap", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BsDark950)
        }
    }
}

@Composable
private fun ScreenPersonalizedRoadmap(
    name: String,
    exp: String,
    track: String,
    onStartRoadmap: () -> Unit
) {
    val roadmapStages = listOf(
        "1. Bootstrap 5 Foundation" to "Mobile-first philosophy, CDN setup, viewport meta tag",
        "2. Containers & Layout" to ".container, .container-fluid, and responsive containers",
        "3. The 12-Column Grid" to ".row, .col, col-* spanning math and auto columns",
        "4. Responsive Breakpoints" to "xs, sm, md, lg, xl, xxl mobile-to-desktop scaling",
        "5. Flexbox Utilities" to ".d-flex, justify-content-*, align-items-*",
        "6. Spacing Scale" to "m-*, p-*, mx-auto, my-*, and responsive gutters",
        "7. Core Components" to "Buttons, Cards, Navbar with hamburger collapse, Modals",
        "8. Forms & Validation" to ".form-control, floating labels, input groups, validation states",
        "9. React-Bootstrap" to "Type-safe React components (<Container>, <Row>, <Col>)",
        "10. Production Projects" to "SaaS Landing Page, Admin Dashboard UI"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Your Bootstrap Roadmap",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Personalized for $name • $track",
                        fontSize = 12.sp,
                        color = BootstrapPurpleLight
                    )
                }

                CreatorVectorAvatar(size = 44.dp)
            }

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800),
                modifier = Modifier.weight(1f, fill = false)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(roadmapStages) { (stage, desc) ->
                        Row(verticalAlignment = Alignment.Top) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(BootstrapPurpleLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("✓", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BsDark950)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(stage, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Text(desc, fontSize = 11.sp, color = BsTextMuted)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onStartRoadmap,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Start My Bootstrap Roadmap", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BsDark950)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = BsDark950)
        }
    }
}

@Composable
private fun SelectableOptionCard(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) BsDark800 else BsDark900
        ),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) BootstrapPurpleLight else BsDark800
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) BootstrapPurpleLight else Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = BsTextMuted
                )
            }

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .border(2.dp, if (isSelected) BootstrapPurpleLight else BsDark700, CircleShape)
                    .background(if (isSelected) BootstrapPurpleLight else Color.Transparent)
            )
        }
    }
}

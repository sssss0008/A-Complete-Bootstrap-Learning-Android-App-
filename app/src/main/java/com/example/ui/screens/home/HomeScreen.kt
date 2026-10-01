package com.example.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserProfile
import com.example.ui.components.BootstrapLogoVisualizer
import com.example.ui.components.CreatorCard
import com.example.ui.components.CreatorVectorAvatar
import com.example.ui.theme.*
import java.util.Calendar

@Composable
fun HomeScreen(
    userProfile: UserProfile,
    onNavigateToLearn: () -> Unit,
    onNavigateToCodeLab: () -> Unit,
    onNavigateToPractice: () -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToReference: () -> Unit,
    onNavigateToAiTutor: () -> Unit,
    onOpenLesson: (String) -> Unit,
    onOpenCreator: () -> Unit,
    onToggleTrack: () -> Unit
) {
    val greeting = rememberGreeting()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BsDark950),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Top Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "$greeting, ${userProfile.name}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "Master Bootstrap 5 & Responsive Web Design.",
                            fontSize = 13.sp,
                            color = BsTextMuted
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(BsDark900)
                            .border(1.dp, BootstrapPurpleLight, CircleShape)
                            .clickable { onOpenCreator() },
                        contentAlignment = Alignment.Center
                    ) {
                        CreatorVectorAvatar(size = 42.dp)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Active Learning Track Chip
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = BsDark900,
                    border = androidx.compose.foundation.BorderStroke(1.dp, BootstrapPurple.copy(alpha = 0.5f)),
                    modifier = Modifier.clickable { onToggleTrack() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🎯 Active Track: ", fontSize = 11.sp, color = BsTextMuted)
                        Text(
                            text = "${userProfile.activeTrack} ⇄",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BootstrapPurpleLight
                        )
                    }
                }
            }
        }

        // Current Course Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Surface(
                                    color = BootstrapPurple.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "CURRENT MODULE",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BootstrapPurpleLight,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Bootstrap 5 Masterclass",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Level 3: The 12-Column Grid System",
                                    fontSize = 12.sp,
                                    color = BsTextLight
                                )
                            }

                            BootstrapLogoVisualizer(size = 50)
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Progress Bar
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Progress", fontSize = 12.sp, color = BsTextMuted)
                            Text("35% Completed", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { 0.35f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = BootstrapPurpleLight,
                            trackColor = BsDark800
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { onOpenLesson("bs_3_1") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Continue Learning (12-Column Grid)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = BsDark950
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = BsDark950, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }

        // Daily Goal & Streak Stats
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Streak Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🔥", fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Streak", fontSize = 12.sp, color = BsTextMuted)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${userProfile.streakDays} Days",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = BootstrapWarning
                        )
                        Text("Active learning streak", fontSize = 10.sp, color = BsTextMuted)
                    }
                }

                // Daily Goal Card
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Timer, contentDescription = null, tint = BootstrapSuccess, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Daily Goal", fontSize = 12.sp, color = BsTextMuted)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${userProfile.minutesPracticedToday} / ${userProfile.dailyGoalMinutes} min",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = BootstrapSuccess
                        )
                        Text("70% of today completed", fontSize = 10.sp, color = BsTextMuted)
                    }
                }
            }
        }

        // Quick Actions Grid
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "BOOTSTRAP QUICK ACTIONS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BsTextMuted,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.GridView,
                        iconTint = BootstrapPurpleLight,
                        title = "Grid Lab",
                        subtitle = "12-Col Sandbox",
                        onClick = onNavigateToCodeLab
                    )
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Terminal,
                        iconTint = BootstrapSuccess,
                        title = "Practice",
                        subtitle = "Challenges & Fixes",
                        onClick = onNavigateToPractice
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Architecture,
                        iconTint = BootstrapInfo,
                        title = "Projects",
                        subtitle = "Landing Page & UI",
                        onClick = onNavigateToProjects
                    )
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.SmartToy,
                        iconTint = BootstrapWarning,
                        title = "Bootstrap Tutor",
                        subtitle = "Explain & Fix Layout",
                        onClick = onNavigateToAiTutor
                    )
                }
            }
        }

        // Skill Overview
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "BOOTSTRAP SKILL OVERVIEW",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BsTextMuted,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                val skills = listOf(
                    Triple("12-Column Responsive Grid", 0.85f, BootstrapPurpleLight),
                    Triple("Responsive Breakpoints (xs-xxl)", 0.70f, BootstrapInfo),
                    Triple("Flexbox & Spacing Utilities", 0.65f, BootstrapPrimary),
                    Triple("Cards & Surface Containers", 0.50f, BootstrapSuccess),
                    Triple("Navbar & Hamburger Menus", 0.40f, BootstrapWarning),
                    Triple("Forms & Custom Validation", 0.30f, BootstrapDanger)
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        skills.forEachIndexed { index, (skillName, progress, color) ->
                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(skillName, fontSize = 12.sp, color = BsTextLight)
                                    Text("${(progress * 100).toInt()}%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                LinearProgressIndicator(
                                    progress = { progress },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp)),
                                    color = color,
                                    trackColor = BsDark800
                                )
                            }
                            if (index < skills.size - 1) Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
            }
        }

        // Creator Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                CreatorCard(onViewFullProfile = onOpenCreator)
            }
        }
    }
}

@Composable
private fun QuickActionCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(subtitle, fontSize = 10.sp, color = BsTextMuted)
            }
        }
    }
}

private fun rememberGreeting(): String {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when {
        hour < 12 -> "Good morning"
        hour < 17 -> "Good afternoon"
        else -> "Good evening"
    }
}

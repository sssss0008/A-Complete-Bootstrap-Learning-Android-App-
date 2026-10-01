package com.example.ui.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumData
import com.example.model.UserProfile
import com.example.ui.components.CreatorCard
import com.example.ui.theme.*

@Composable
fun ProfileScreen(
    userProfile: UserProfile,
    onOpenCreator: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenReference: () -> Unit
) {
    val context = LocalContext.current
    var showCertificateDialog by remember { mutableStateOf(false) }

    val completedCount = userProfile.completedLessonIds.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BsDark950),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 100.dp)
    ) {
        // User Profile Header Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(CircleShape)
                                    .background(BootstrapPurple),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = userProfile.name.take(1).uppercase(),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = userProfile.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Track: ${userProfile.activeTrack}",
                                    fontSize = 12.sp,
                                    color = BootstrapPurpleLight
                                )
                            }
                        }

                        IconButton(onClick = onOpenSettings) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = BsTextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        StatItem(label = "Streak", value = "${userProfile.streakDays}d 🔥", valueColor = BootstrapWarning)
                        StatItem(label = "Completed", value = "$completedCount lessons", valueColor = BootstrapPurpleLight)
                        StatItem(label = "Daily Goal", value = "${userProfile.dailyGoalMinutes} min", valueColor = BootstrapSuccess)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Certificate Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1338)),
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BootstrapPurpleLight.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showCertificateDialog = true }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("📜", fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Bootstrap 5 Certificate of Completion",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Learn Bootstrap by Awiskar Acharya credential",
                            fontSize = 11.sp,
                            color = BsTextLight
                        )
                    }
                    Text("View", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Skill Tree
        item {
            Text("BOOTSTRAP SKILL TREE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    val nodes = listOf(
                        "Containers & Responsive Layout" to true,
                        "12-Column Responsive Grid (.row > .col-*)" to true,
                        "Mobile-First Breakpoints (xs-xxl)" to true,
                        "Flexbox & Spacing Utilities" to true,
                        "Cards & Surface Architecture" to (completedCount >= 4),
                        "Responsive Navbar & Hamburger Drawer" to (completedCount >= 6),
                        "Forms & Validation States" to (completedCount >= 8),
                        "Modals, Dialogs & Tooltips" to (completedCount >= 10),
                        "React-Bootstrap Component Integration" to (completedCount >= 11)
                    )

                    nodes.forEachIndexed { index, (nodeTitle, isUnlocked) ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isUnlocked) BootstrapPurpleLight else BsDark800)
                                    .border(1.dp, if (isUnlocked) Color.White else BsDark700, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isUnlocked) "✓" else "•",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isUnlocked) BsDark950 else BsTextMuted
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = nodeTitle,
                                fontSize = 13.sp,
                                fontWeight = if (isUnlocked) FontWeight.Bold else FontWeight.Normal,
                                color = if (isUnlocked) Color.White else BsTextMuted
                            )
                        }

                        if (index < nodes.size - 1) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 11.dp)
                                    .width(2.dp)
                                    .height(14.dp)
                                    .background(if (nodes[index + 1].second) BootstrapPurpleLight.copy(alpha = 0.5f) else BsDark800)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Achievements Badges
        item {
            Text("ACHIEVEMENTS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(CurriculumData.achievements) { badge ->
            val isUnlocked = userProfile.unlockedBadgeIds.contains(badge.id) || badge.isUnlocked
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isUnlocked) BootstrapPurpleLight.copy(alpha = 0.4f) else BsDark800
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(if (isUnlocked) BootstrapPurple.copy(alpha = 0.2f) else BsDark800),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (badge.iconCategory) {
                                "GRID" -> "📐"
                                "COMPONENT" -> "🧩"
                                "STREAK" -> "🔥"
                                "PROJECT" -> "🚀"
                                else -> "🛠"
                            },
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(badge.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = if (isUnlocked) Color.White else BsTextMuted)
                        Text(badge.description, fontSize = 11.sp, color = if (isUnlocked) BsTextLight else BsDark600)
                    }

                    if (isUnlocked) {
                        Text("UNLOCKED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = BootstrapSuccess)
                    }
                }
            }
        }

        // Creator Card Section
        item {
            Spacer(modifier = Modifier.height(20.dp))
            CreatorCard(onViewFullProfile = onOpenCreator)
        }
    }

    // Official Certificate Dialog
    if (showCertificateDialog) {
        AlertDialog(
            onDismissRequest = { showCertificateDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/awiskaracharya/"))
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A66C2))
                ) {
                    Text("Connect with Awiskar Acharya", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showCertificateDialog = false }) {
                    Text("Close", color = BsTextMuted)
                }
            },
            containerColor = BsDark900,
            title = {
                Text("Certificate of Completion", fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BsDark950)
                        .border(2.dp, BootstrapPurpleLight, RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("BOOTSTRAP ACADEMY CREDENTIAL", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight, letterSpacing = 2.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("This is proudly presented to", fontSize = 11.sp, color = BsTextMuted)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(userProfile.name, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("for successfully mastering the 12-Column Responsive Grid, Breakpoints, Flexbox Utilities, Components, and React-Bootstrap in", fontSize = 11.sp, color = BsTextLight, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Learn Bootstrap by Awiskar Acharya", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Author & Educator", fontSize = 9.sp, color = BsTextMuted)
                            Text("Awiskar Acharya", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Issued", fontSize = 9.sp, color = BsTextMuted)
                            Text("October 2026", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "You completed your Bootstrap learning journey. Want to connect with the creator? Message Awiskar Acharya on LinkedIn.",
                        fontSize = 11.sp,
                        color = BootstrapSuccess,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        )
    }
}

@Composable
private fun StatItem(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = valueColor)
        Text(label, fontSize = 11.sp, color = BsTextMuted)
    }
}

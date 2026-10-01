package com.example.ui.screens.creator

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.CreatorVectorAvatar
import com.example.ui.theme.*

@Composable
fun CreatorScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val linkedInUrl = "https://www.linkedin.com/in/awiskaracharya/"

    Scaffold(
        containerColor = BsDark950,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("About the Creator", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CreatorVectorAvatar(size = 90.dp)

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Awiskar Acharya", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.Verified, contentDescription = "Verified", tint = BootstrapPurpleLight, modifier = Modifier.size(20.dp))
                    }

                    Text("Bootstrap & Frontend Developer • Programming Educator", fontSize = 13.sp, color = BootstrapPurpleLight)
                    Text("Creator of Learn Bootstrap", fontSize = 11.sp, color = BsTextMuted)

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A66C2)), // LinkedIn Blue
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Connect on LinkedIn", fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.OpenInNew, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Learning Philosophy Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("CORE LEARNING PHILOSOPHY", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "\"Don't just read about UI frameworks. Build real responsive layouts.\"",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Frontend development should be practical and hands-on. By visualizing how 12-column grids adapt to breakpoints, experimenting with utility classes, and testing responsive mobile navigation, you build the muscle memory needed to build production-grade interfaces rapidly.",
                            fontSize = 12.sp,
                            color = BsTextLight,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Why This Platform Exists
                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("WHY THIS PLATFORM EXISTS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "This application is 100% free for learners worldwide. Awiskar built Learn Bootstrap to empower developers to master responsive mobile-first design, understand the 12-column grid deeply, and bridge Bootstrap 5 with modern frontend workflows like React-Bootstrap.",
                            fontSize = 12.sp,
                            color = BsTextLight,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Skills & Expertise
                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("SKILLS & EXPERTISE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        val skills = listOf(
                            "Bootstrap 5", "12-Column Grid", "Responsive Breakpoints", "Flexbox Utilities",
                            "React-Bootstrap", "HTML5 & CSS3", "Sass Custom Theming", "UI Component Systems"
                        )
                        skills.chunked(2).forEach { rowItems ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                rowItems.forEach { skill ->
                                    Surface(
                                        color = BsDark800,
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = skill,
                                            fontSize = 11.sp,
                                            color = BsTextLight,
                                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                            modifier = Modifier.padding(vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

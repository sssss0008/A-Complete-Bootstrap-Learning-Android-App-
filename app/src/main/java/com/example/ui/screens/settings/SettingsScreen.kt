package com.example.ui.screens.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserProfile
import com.example.ui.theme.*

@Composable
fun SettingsScreen(
    userProfile: UserProfile,
    onBack: () -> Unit,
    onToggleTheme: () -> Unit,
    onToggleTrack: () -> Unit,
    onResetProgress: () -> Unit
) {
    val context = LocalContext.current
    var showResetDialog by remember { mutableStateOf(false) }

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
                Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            // Profile & Account
            item {
                Text("ACCOUNT & PROFILE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted, letterSpacing = 1.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Learner Name", fontSize = 13.sp, color = Color.White)
                                Text(userProfile.name, fontSize = 11.sp, color = BsTextMuted)
                            }
                            Text("Learn Bootstrap Academy", fontSize = 11.sp, color = BootstrapPurpleLight)
                        }

                        Divider(color = BsDark800, modifier = Modifier.padding(vertical = 10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onToggleTrack() },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Primary Track", fontSize = 13.sp, color = Color.White)
                                Text("Tap to switch track", fontSize = 11.sp, color = BsTextMuted)
                            }
                            Text(userProfile.activeTrack, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            // Appearance & Theme
            item {
                Text("APPEARANCE", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted, letterSpacing = 1.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Developer Dark Theme", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Sleek surfaces optimized for responsive web coding", fontSize = 11.sp, color = BsTextMuted)
                        }
                        Switch(
                            checked = userProfile.darkTheme,
                            onCheckedChange = { onToggleTheme() },
                            colors = SwitchDefaults.colors(checkedThumbColor = BootstrapPurpleLight, checkedTrackColor = BsDark800)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            // Offline & Reset Progress
            item {
                Text("DATA & PROGRESS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted, letterSpacing = 1.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = BsDark900),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    Toast.makeText(context, "All lessons are stored offline on device!", Toast.LENGTH_SHORT).show()
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Offline Mode Content", fontSize = 13.sp, color = Color.White)
                                Text("100% curriculum accessible without internet", fontSize = 11.sp, color = BootstrapSuccess)
                            }
                            Icon(Icons.Default.CloudDone, contentDescription = null, tint = BootstrapSuccess)
                        }

                        Divider(color = BsDark800, modifier = Modifier.padding(vertical = 10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showResetDialog = true },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Reset Learning Progress", fontSize = 13.sp, color = BootstrapDanger)
                                Text("Restart curriculum progress from Level 1", fontSize = 11.sp, color = BsTextMuted)
                            }
                            Icon(Icons.Default.RestartAlt, contentDescription = null, tint = BootstrapDanger)
                        }
                    }
                }
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Progress?", color = Color.White) },
            text = { Text("Are you sure you want to reset your completed lessons and streak? This action cannot be undone.", color = BsTextLight) },
            confirmButton = {
                Button(
                    onClick = {
                        onResetProgress()
                        showResetDialog = false
                        Toast.makeText(context, "Progress reset successfully", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BootstrapDanger)
                ) {
                    Text("Reset Progress", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showResetDialog = false }) {
                    Text("Cancel", color = BsTextMuted)
                }
            },
            containerColor = BsDark900
        )
    }
}

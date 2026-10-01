package com.example.ui.screens.interview

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
import com.example.model.InterviewItem
import com.example.ui.theme.*

@Composable
fun InterviewScreen(
    onBack: () -> Unit
) {
    var isMockInterviewRunning by remember { mutableStateOf(false) }
    var currentQuestionIdx by remember { mutableStateOf(0) }
    var isFinished by remember { mutableStateOf(false) }

    val questions = CurriculumData.interviewQuestions

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
                Text(
                    text = "Bootstrap & Frontend Interview Center",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    ) { innerPadding ->
        if (isMockInterviewRunning) {
            if (!isFinished) {
                val currentQ = questions[currentQuestionIdx]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Question ${currentQuestionIdx + 1} of ${questions.size}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = BootstrapPurpleLight
                            )
                            Surface(
                                color = BsDark800,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = currentQ.category,
                                    fontSize = 10.sp,
                                    color = BsTextLight,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = currentQ.question,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Card(
                            colors = CardDefaults.cardColors(containerColor = BsDark900),
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text("💡 Interviewer Tip:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BootstrapWarning)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Formulate your answer clearly explaining why Bootstrap is architected this way. When ready, click 'Next Question'.", fontSize = 12.sp, color = BsTextMuted)
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (currentQuestionIdx > 0) {
                            Button(
                                onClick = { currentQuestionIdx-- },
                                colors = ButtonDefaults.buttonColors(containerColor = BsDark800)
                            ) {
                                Text("Previous", color = BsTextLight)
                            }
                        } else {
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        Button(
                            onClick = {
                                if (currentQuestionIdx < questions.size - 1) {
                                    currentQuestionIdx++
                                } else {
                                    isFinished = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight)
                        ) {
                            Text(
                                text = if (currentQuestionIdx == questions.size - 1) "Finish Review" else "Next Question",
                                color = BsDark950,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            } else {
                // Review
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(20.dp)
                ) {
                    item {
                        Text("Interview Practice Review", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Structured feedback & model answers.", fontSize = 13.sp, color = BsTextMuted)

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                isMockInterviewRunning = false
                                isFinished = false
                                currentQuestionIdx = 0
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Back to Interview Catalog", color = BsDark950, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    items(questions) { q ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = BsDark900),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(q.question, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(q.detailedAnswer, fontSize = 12.sp, color = BsTextLight, lineHeight = 17.sp)

                                Spacer(modifier = Modifier.height(8.dp))
                                q.keyTakeaways.forEach { takeaway ->
                                    Text("• $takeaway", fontSize = 11.sp, color = BootstrapPurpleLight)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = BsDark900),
                        shape = RoundedCornerShape(14.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("MOCK INTERVIEW SIMULATOR", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Test your technical frontend knowledge on the 12-column grid, responsive breakpoints, container math, and flexbox alignment.", fontSize = 13.sp, color = BsTextLight)

                            Spacer(modifier = Modifier.height(14.dp))

                            Button(
                                onClick = {
                                    isMockInterviewRunning = true
                                    currentQuestionIdx = 0
                                    isFinished = false
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = BootstrapPurpleLight),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Start Mock Interview", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BsDark950)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("FREQUENTLY ASKED QUESTIONS", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BsTextMuted)
                    Spacer(modifier = Modifier.height(10.dp))
                }

                items(questions) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = BsDark900),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(item.type, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                                Text(item.category, fontSize = 10.sp, color = BsTextMuted)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(item.question, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(item.detailedAnswer, fontSize = 12.sp, color = BsTextLight, lineHeight = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

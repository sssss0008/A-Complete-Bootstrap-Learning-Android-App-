package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

/**
 * Custom Vector Developer Avatar for Awiskar Acharya with Bootstrap branding
 */
@Composable
fun CreatorVectorAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 72.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                Brush.linearGradient(
                    listOf(BsDark800, BsDark900)
                )
            )
            .border(2.dp, BootstrapPurpleLight, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Background subtle purple glow
            drawCircle(
                color = BootstrapPurple.copy(alpha = 0.2f),
                radius = w * 0.46f,
                center = Offset(w / 2f, h / 2f)
            )

            // Developer Hoodie / Shoulders
            drawOval(
                color = BsDark700,
                topLeft = Offset(w * 0.12f, h * 0.65f),
                size = Size(w * 0.76f, h * 0.5f)
            )
            // Hoodie inner neckline
            drawOval(
                color = BsDark950,
                topLeft = Offset(w * 0.35f, h * 0.62f),
                size = Size(w * 0.3f, h * 0.2f)
            )

            // Head / Face
            drawCircle(
                color = Color(0xFFE2C4A2),
                radius = w * 0.26f,
                center = Offset(w / 2f, h * 0.44f)
            )

            // Hair
            drawOval(
                color = Color(0xFF1E293B),
                topLeft = Offset(w * 0.22f, h * 0.18f),
                size = Size(w * 0.56f, h * 0.32f)
            )

            // Eyeglasses
            val glassY = h * 0.42f
            val glassRadius = w * 0.08f
            drawCircle(
                color = BsDark950,
                radius = glassRadius,
                center = Offset(w * 0.38f, glassY),
                style = Stroke(width = 2.dp.toPx())
            )
            drawCircle(
                color = BsDark950,
                radius = glassRadius,
                center = Offset(w * 0.62f, glassY),
                style = Stroke(width = 2.dp.toPx())
            )
            drawLine(
                color = BsDark950,
                start = Offset(w * 0.46f, glassY),
                end = Offset(w * 0.54f, glassY),
                strokeWidth = 2.dp.toPx()
            )

            // Smile
            drawArc(
                color = Color(0xFF6B4226),
                startAngle = 10f,
                sweepAngle = 160f,
                useCenter = false,
                topLeft = Offset(w * 0.42f, h * 0.52f),
                size = Size(w * 0.16f, h * 0.08f),
                style = Stroke(width = 2.dp.toPx())
            )

            // Tiny Bootstrap purple badge
            drawCircle(
                color = BootstrapPurpleLight,
                radius = 3.dp.toPx(),
                center = Offset(w * 0.5f, h * 0.82f)
            )
        }
    }
}

/**
 * Creator Profile Card with LinkedIn Connect
 */
@Composable
fun CreatorCard(
    modifier: Modifier = Modifier,
    onViewFullProfile: () -> Unit = {}
) {
    val context = LocalContext.current
    val linkedInUrl = "https://www.linkedin.com/in/awiskaracharya/"

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CreatorVectorAvatar(size = 60.dp)

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Awiskar Acharya",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified Educator",
                            tint = BootstrapPurpleLight,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(
                        text = "Bootstrap & Frontend Developer • Programming Educator",
                        fontSize = 11.sp,
                        color = BootstrapPurpleLight,
                        lineHeight = 14.sp
                    )
                    Text(
                        text = "Creator of Learn Bootstrap",
                        fontSize = 10.sp,
                        color = BsTextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "\"Don't just read about UI frameworks. Build real responsive layouts, master 12-column grids, and create production websites.\"",
                fontSize = 12.sp,
                color = BsTextLight,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A66C2)), // LinkedIn Blue
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(vertical = 10.dp)
                ) {
                    Text(
                        text = "Connect on LinkedIn",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = "Open LinkedIn",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }

                OutlinedButton(
                    onClick = onViewFullProfile,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = BootstrapPurpleLight),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BootstrapPurpleLight.copy(alpha = 0.5f)),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    Text("About", fontSize = 12.sp)
                }
            }
        }
    }
}

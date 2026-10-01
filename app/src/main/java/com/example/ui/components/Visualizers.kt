package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

/**
 * Bootstrap Logo Badge Visualizer
 */
@Composable
fun BootstrapLogoVisualizer(
    modifier: Modifier = Modifier,
    size: Int = 100
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(RoundedCornerShape((size * 0.22).dp))
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF9065DD), Color(0xFF6610F2))
                )
            )
            .border(2.dp, Color(0xFFC4B5FD), RoundedCornerShape((size * 0.22).dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "B",
            fontSize = (size * 0.58).sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
    }
}

/**
 * Interactive 12-Column Bootstrap Grid Visualizer
 */
@Composable
fun BootstrapGridVisualizer(
    modifier: Modifier = Modifier
) {
    var layoutMode by remember { mutableStateOf("3 Cols (col-4)") }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "12-COLUMN BOOTSTRAP GRID VISUALIZER",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BootstrapPurpleLight,
                    letterSpacing = 1.sp
                )
                Text("Total = 12 cols", fontSize = 10.sp, color = BsTextMuted)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Preset Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf("2 Cols (col-6)", "3 Cols (col-4)", "4 Cols (col-3)", "Uneven (col-8 + col-4)").forEach { mode ->
                    val isSel = layoutMode == mode
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isSel) BootstrapPurpleLight else BsDark800,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { layoutMode = mode }
                    ) {
                        Text(
                            text = mode.split(" ")[0] + " " + mode.split(" ")[1],
                            fontSize = 10.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSel) BsDark950 else Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Container indicator
            Surface(
                color = BsDark950,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark700),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "<div class=\"container\"><div class=\"row g-2\">",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = BootstrapInfo
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Grid Columns Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        when (layoutMode) {
                            "2 Cols (col-6)" -> {
                                GridColumnBox(label = "col-6 (50%)", weight = 6f, color = BootstrapPurple)
                                GridColumnBox(label = "col-6 (50%)", weight = 6f, color = BootstrapIndigo)
                            }
                            "3 Cols (col-4)" -> {
                                GridColumnBox(label = "col-4", weight = 4f, color = BootstrapPurple)
                                GridColumnBox(label = "col-4", weight = 4f, color = BootstrapPrimary)
                                GridColumnBox(label = "col-4", weight = 4f, color = BootstrapInfo)
                            }
                            "4 Cols (col-3)" -> {
                                GridColumnBox(label = "col-3", weight = 3f, color = BootstrapPurple)
                                GridColumnBox(label = "col-3", weight = 3f, color = BootstrapPrimary)
                                GridColumnBox(label = "col-3", weight = 3f, color = BootstrapSuccess)
                                GridColumnBox(label = "col-3", weight = 3f, color = BootstrapWarning)
                            }
                            "Uneven (col-8 + col-4)" -> {
                                GridColumnBox(label = "col-8 (66.6%) Main Content", weight = 8f, color = BootstrapPurple)
                                GridColumnBox(label = "col-4 (33.3%) Sidebar", weight = 4f, color = BootstrapSecondary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "</div></div>",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = BootstrapInfo
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "💡 Rule: Columns in a row must sum up to 12. Extra columns automatically wrap to the next line.",
                fontSize = 11.sp,
                color = BsTextMuted
            )
        }
    }
}

@Composable
private fun RowScope.GridColumnBox(label: String, weight: Float, color: Color) {
    Box(
        modifier = Modifier
            .weight(weight)
            .height(52.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.25f))
            .border(1.dp, color, RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Responsive Breakpoints Visualizer
 */
@Composable
fun BreakpointVisualizer(
    modifier: Modifier = Modifier
) {
    var activeBp by remember { mutableStateOf("md") }

    val breakpoints = listOf(
        Triple("xs", "< 576px", "Portrait Phones"),
        Triple("sm", "≥ 576px", "Landscape Phones"),
        Triple("md", "≥ 768px", "Tablets (iPad)"),
        Triple("lg", "≥ 992px", "Laptops & Desktops"),
        Triple("xl", "≥ 1200px", "Large Desktops"),
        Triple("xxl", "≥ 1400px", "Ultra-Wide Screens")
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "RESPONSIVE BREAKPOINTS (MOBILE-FIRST)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BootstrapPurpleLight,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Breakpoint selector chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                breakpoints.forEach { (bp, width, _) ->
                    val isSel = activeBp == bp
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isSel) BootstrapPurpleLight else BsDark800,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { activeBp = bp }
                    ) {
                        Text(
                            text = bp,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSel) BsDark950 else Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Active description
            val current = breakpoints.find { it.first == activeBp } ?: breakpoints[2]
            Surface(
                color = BsDark950,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Breakpoint: .col-${current.first}-*", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                        Text(current.second, fontSize = 11.sp, color = BootstrapInfo, fontFamily = FontFamily.Monospace)
                    }
                    Text("Target Devices: ${current.third}", fontSize = 11.sp, color = BsTextMuted)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Example: class=\"col-12 col-md-6 col-lg-4\"",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = Color.White
                    )
                    Text(
                        text = when (activeBp) {
                            "xs", "sm" -> "→ Renders full width (100% / 12 cols) stacked vertically on phones"
                            "md" -> "→ Renders 2 columns per row (50% / 6 cols each) on tablet"
                            else -> "→ Renders 3 columns per row (33.3% / 4 cols each) on laptop/desktop"
                        },
                        fontSize = 11.sp,
                        color = BootstrapSuccess
                    )
                }
            }
        }
    }
}

/**
 * Interactive Utility Class Visualizer
 */
@Composable
fun UtilityClassesVisualizer(
    modifier: Modifier = Modifier
) {
    var selectedColor by remember { mutableStateOf("bg-primary") }
    var selectedPadding by remember { mutableStateOf("p-3") }
    var selectedShadow by remember { mutableStateOf("shadow-sm") }
    var selectedRounded by remember { mutableStateOf("rounded-3") }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "BOOTSTRAP UTILITY CLASS LABORATORY",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BootstrapPurpleLight,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Controls
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Button(
                    onClick = {
                        selectedColor = when (selectedColor) {
                            "bg-primary" -> "bg-success"
                            "bg-success" -> "bg-danger"
                            "bg-danger" -> "bg-dark"
                            else -> "bg-primary"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BsDark800),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(selectedColor, fontSize = 10.sp, color = Color.White)
                }

                Button(
                    onClick = {
                        selectedPadding = if (selectedPadding == "p-3") "p-4" else "p-3"
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BsDark800),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(selectedPadding, fontSize = 10.sp, color = Color.White)
                }

                Button(
                    onClick = {
                        selectedRounded = if (selectedRounded == "rounded-3") "rounded-pill" else "rounded-3"
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BsDark800),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(selectedRounded, fontSize = 10.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val boxColor = when (selectedColor) {
                "bg-success" -> BootstrapSuccess
                "bg-danger" -> BootstrapDanger
                "bg-dark" -> BootstrapDark
                else -> BootstrapPrimary
            }

            // Preview Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(if (selectedRounded == "rounded-pill") RoundedCornerShape(50.dp) else RoundedCornerShape(12.dp))
                    .background(boxColor)
                    .padding(if (selectedPadding == "p-4") 20.dp else 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "<div class=\"$selectedColor $selectedPadding $selectedRounded text-white\">",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

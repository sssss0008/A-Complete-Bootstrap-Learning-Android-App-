package com.example.ui.screens.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.engine.ReactSimulationEngine
import com.example.engine.SimulationState
import com.example.ui.components.CodeBlock
import com.example.ui.theme.*

@Composable
fun CodeLabScreen() {
    val simState by ReactSimulationEngine.state.collectAsState()

    var activeFileTab by remember { mutableStateOf("index.html") }
    var activeModeTab by remember { mutableStateOf("Preview") } // "Preview", "Code", "Grid Inspector"
    var activeTerminalTab by remember { mutableStateOf("Console") } // "Console", "Terminal", "Problems"
    var viewportMode by remember { mutableStateOf("Mobile") } // "Mobile", "Tablet", "Desktop"

    val codeHtml = """<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap Grid Lab</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white p-4">
  <div class="container">
    <div class="row g-3">
      <div class="col-12 col-md-6 col-lg-4">
        <div class="card bg-secondary text-white shadow-sm p-3">
          <h5>Card 1</h5>
          <p>col-12 on mobile, col-md-6 on tablet, col-lg-4 on desktop.</p>
        </div>
      </div>
      <div class="col-12 col-md-6 col-lg-4">
        <div class="card bg-primary text-white shadow-sm p-3">
          <h5>Card 2</h5>
          <p>Responsive 12-column system.</p>
        </div>
      </div>
      <div class="col-12 col-md-12 col-lg-4">
        <div class="card bg-success text-white shadow-sm p-3">
          <h5>Card 3</h5>
          <p>Auto wraps when exceeding 12.</p>
        </div>
      </div>
    </div>
  </div>
</body>
</html>"""

    val codeReactBs = """import React from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';

export default function BootstrapApp() {
  return (
    <Container className="py-4">
      <Row className="g-3">
        <Col xs={12} md={6} lg={4}>
          <Card className="shadow-sm">
            <Card.Body>
              <Card.Title>React-Bootstrap</Card.Title>
              <Card.Text>Component-driven grid system.</Card.Text>
              <Button variant="primary">Learn More</Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}"""

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BsDark950),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // Top Toolbar: File tabs & Mode toggles
        item {
            Column(modifier = Modifier.background(BsDark900)) {
                // File tabs row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val files = listOf("index.html", "App.jsx (React-BS)", "styles.css")
                    files.forEach { file ->
                        val isSelected = activeFileTab == file
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (isSelected) BsDark800 else Color.Transparent,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) BootstrapPurpleLight.copy(alpha = 0.5f) else BsDark800
                            ),
                            modifier = Modifier.clickable { activeFileTab = file }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (file.contains("html")) Icons.Default.Html else if (file.contains("css")) Icons.Default.Style else Icons.Default.Javascript,
                                    contentDescription = null,
                                    tint = if (file.contains("html")) BootstrapWarning else if (file.contains("css")) BootstrapInfo else BootstrapPurpleLight,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = file,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = if (isSelected) Color.White else BsTextMuted,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                // Sub-bar: Mode toggles & Quick Actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Mode Chips
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("Preview", "Code", "Grid Inspector").forEach { mode ->
                            val isSelected = activeModeTab == mode
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = if (isSelected) BootstrapPurpleLight else BsDark800,
                                modifier = Modifier.clickable { activeModeTab = mode }
                            ) {
                                Text(
                                    text = mode,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) BsDark950 else BsTextLight,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }

                    // Column Switcher Buttons
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        listOf(2, 3, 4).forEach { cols ->
                            val isSel = simState.activeColumns == cols
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = if (isSel) BootstrapPurple else BsDark800,
                                modifier = Modifier.clickable { ReactSimulationEngine.setColumns(cols) }
                            ) {
                                Text(
                                    text = "${cols}c",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Main Display Section: Preview, Code, or Inspector
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                when (activeModeTab) {
                    "Preview" -> {
                        LiveBrowserPreviewCard(
                            simState = simState,
                            viewportMode = viewportMode,
                            onViewportChange = { viewportMode = it }
                        )
                    }
                    "Code" -> {
                        CodeBlock(
                            code = when (activeFileTab) {
                                "index.html" -> codeHtml
                                "App.jsx (React-BS)" -> codeReactBs
                                else -> "/* Custom Bootstrap overrides */\n:root {\n  --bs-primary: #7952b3;\n}"
                            },
                            language = if (activeFileTab.contains("html")) "HTML" else if (activeFileTab.contains("css")) "CSS" else "JSX"
                        )
                    }
                    "Grid Inspector" -> {
                        GridInspectorPanel(simState = simState, viewport = viewportMode)
                    }
                }
            }
        }

        // Terminal Panel
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                colors = CardDefaults.cardColors(containerColor = BsDark900),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(BsDark950)
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            listOf("Console", "Problems").forEach { tab ->
                                val isSelected = activeTerminalTab == tab
                                Text(
                                    text = tab,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) BootstrapPurpleLight else BsTextMuted,
                                    modifier = Modifier
                                        .clickable { activeTerminalTab = tab }
                                        .padding(vertical = 4.dp)
                                )
                            }
                        }

                        if (activeTerminalTab == "Console") {
                            Text(
                                text = "Clear",
                                fontSize = 11.sp,
                                color = BsTextMuted,
                                modifier = Modifier
                                    .clickable { ReactSimulationEngine.clearConsole() }
                                    .padding(4.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp)
                    ) {
                        when (activeTerminalTab) {
                            "Console" -> {
                                LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    items(simState.logs) { log ->
                                        Row {
                                            Text(
                                                text = when (log.level) {
                                                    "ERROR" -> "ERR "
                                                    "WARN" -> "WRN "
                                                    "INFO" -> "INF "
                                                    else -> "LOG "
                                                },
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = when (log.level) {
                                                    "ERROR" -> BootstrapDanger
                                                    "WARN" -> BootstrapWarning
                                                    "INFO" -> BootstrapInfo
                                                    else -> BootstrapSuccess
                                                }
                                            )
                                            Text(
                                                text = log.message,
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 11.sp,
                                                color = BsTextLight
                                            )
                                        }
                                    }
                                }
                            }
                            "Problems" -> {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Text("✓ HTML markup is valid Bootstrap 5", fontSize = 12.sp, color = BootstrapSuccess)
                                    Text("0 errors, 0 invalid class warnings", fontSize = 11.sp, color = BsTextMuted)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LiveBrowserPreviewCard(
    simState: SimulationState,
    viewportMode: String,
    onViewportChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column {
            // Browser Address Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BsDark950)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Viewport buttons
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Mobile", "Tablet", "Desktop").forEach { vp ->
                        val isSel = viewportMode == vp
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (isSel) BootstrapPurple.copy(alpha = 0.25f) else Color.Transparent,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSel) BootstrapPurpleLight else BsDark800
                            ),
                            modifier = Modifier.clickable { onViewportChange(vp) }
                        ) {
                            Text(
                                text = vp,
                                fontSize = 10.sp,
                                color = if (isSel) BootstrapPurpleLight else BsTextMuted,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Surface(
                    color = BsDark800,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "bootstrap.local/playground",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = BsTextLight,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                IconButton(
                    onClick = { ReactSimulationEngine.setTheme("primary") },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Reset Theme", tint = BsTextMuted, modifier = Modifier.size(16.dp))
                }
            }

            // Live Grid Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0F1523))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Live Bootstrap Grid & Component Output",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = BootstrapPurpleLight
                )
                Text(
                    text = "class=\"container\" • Showing ${simState.activeColumns} cols per row",
                    fontSize = 11.sp,
                    color = BsTextMuted
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Responsive Grid Preview
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val colSpan = 12 / simState.activeColumns
                    val themeColor = when (simState.activeTheme) {
                        "success" -> BootstrapSuccess
                        "danger" -> BootstrapDanger
                        "dark" -> BootstrapDark
                        "info" -> BootstrapInfo
                        else -> BootstrapPrimary
                    }

                    (1..simState.activeColumns).forEach { index ->
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = BsDark900),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, themeColor.copy(alpha = 0.6f))
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Surface(
                                    color = themeColor.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "col-$colSpan",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = themeColor,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Card #$index", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Text("Bootstrap 5 surface with .shadow-sm", fontSize = 10.sp, color = BsTextMuted)
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = {
                                        val nextTheme = when (simState.activeTheme) {
                                            "primary" -> "success"
                                            "success" -> "danger"
                                            "danger" -> "info"
                                            else -> "primary"
                                        }
                                        ReactSimulationEngine.setTheme(nextTheme)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                                    shape = RoundedCornerShape(6.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text("btn-${simState.activeTheme}", fontSize = 10.sp, color = Color.White)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Interactive Todo List with Bootstrap classes
                Surface(
                    color = BsDark900,
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Interactive .form-check List:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.height(6.dp))

                        simState.todos.forEach { todo ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                                    .clickable { ReactSimulationEngine.toggleTodo(todo.id) },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = todo.done,
                                    onCheckedChange = { ReactSimulationEngine.toggleTodo(todo.id) },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = BootstrapPurpleLight,
                                        checkmarkColor = BsDark950
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = todo.text,
                                    fontSize = 11.sp,
                                    color = if (todo.done) BsTextMuted else Color.White,
                                    style = if (todo.done) androidx.compose.ui.text.TextStyle(textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough) else androidx.compose.ui.text.TextStyle()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GridInspectorPanel(simState: SimulationState, viewport: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "BOOTSTRAP 12-COLUMN GRID INSPECTOR",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BootstrapPurpleLight,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = BsDark950,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Current Viewport: $viewport", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BootstrapInfo)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("• Grid Layout: ${simState.activeColumns} Columns", fontSize = 11.sp, color = BsTextLight)
                    Text("• Active Column Class: .col-${12 / simState.activeColumns}", fontSize = 11.sp, color = BootstrapPurpleLight)
                    Text("• Gutter: .g-3 (1rem / 16px horizontal and vertical padding)", fontSize = 11.sp, color = BsTextLight)
                    Text("• Theme Utility: bg-${simState.activeTheme}", fontSize = 11.sp, color = BootstrapSuccess)
                }
            }
        }
    }
}

package com.example.ui.screens.aitutor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
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
import com.example.ui.components.CodeBlock
import com.example.ui.theme.*

data class ChatMessage(
    val id: Long = System.currentTimeMillis(),
    val isUser: Boolean,
    val text: String,
    val codeSnippet: String? = null
)

@Composable
fun AiTutorScreen(
    onBack: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                isUser = false,
                text = "Hello! I am your Bootstrap AI Tutor created for Learn Bootstrap by Awiskar Acharya. Ask me any question about the 12-column grid, responsive breakpoints, flexbox utilities, components, or React-Bootstrap."
            )
        )
    }

    val quickPrompts = listOf(
        "Explain the 12-column grid",
        "container vs container-fluid",
        "How do responsive breakpoints work?",
        "Center vertically with flexbox",
        "How to use Bootstrap in React"
    )

    fun handleSend(query: String) {
        if (query.isBlank()) return
        messages.add(ChatMessage(isUser = true, text = query.trim()))
        inputText = ""

        val response = generateTutorResponse(query.trim())
        messages.add(response)
    }

    Scaffold(
        containerColor = BsDark950,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BsDark900)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                BootstrapLogoVisualizer(size = 32)

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text("Bootstrap AI Tutor", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Ready to explain grid, breakpoints & utilities", fontSize = 11.sp, color = BootstrapSuccess)
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BsDark900)
                    .padding(12.dp)
            ) {
                // Quick Suggestion Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    items(quickPrompts) { prompt ->
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = BsDark800,
                            border = androidx.compose.foundation.BorderStroke(1.dp, BsDark700),
                            modifier = Modifier.clickable { handleSend(prompt) }
                        ) {
                            Text(
                                text = prompt,
                                fontSize = 11.sp,
                                color = BootstrapPurpleLight,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                // Input bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = { Text("Ask any Bootstrap question...") },
                        modifier = Modifier.weight(1f),
                        singleLine = false,
                        maxLines = 3,
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BootstrapPurpleLight,
                            unfocusedBorderColor = BsDark700,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { handleSend(inputText) },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(BootstrapPurpleLight)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = BsDark950,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(messages) { msg ->
                ChatBubble(message = msg)
            }
        }
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isUser) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(BootstrapPurple),
                contentAlignment = Alignment.Center
            ) {
                Text("B", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Surface(
            color = if (message.isUser) BootstrapPurpleDark else BsDark900,
            shape = RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp,
                bottomStart = if (message.isUser) 14.dp else 2.dp,
                bottomEnd = if (message.isUser) 2.dp else 14.dp
            ),
            border = if (!message.isUser) androidx.compose.foundation.BorderStroke(1.dp, BsDark800) else null,
            modifier = Modifier.widthIn(max = 310.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = message.text,
                    fontSize = 13.sp,
                    color = Color.White,
                    lineHeight = 18.sp
                )

                if (message.codeSnippet != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(code = message.codeSnippet, language = "HTML")
                }
            }
        }
    }
}

private fun generateTutorResponse(query: String): ChatMessage {
    val q = query.lowercase()
    return when {
        q.contains("12") || q.contains("grid") -> {
            ChatMessage(
                isUser = false,
                text = "Bootstrap divides every row into 12 proportional columns. Columns must be placed inside a .row, which must be inside a .container:\n\n• 2 equal columns: col-6 + col-6 = 12\n• 3 equal columns: col-4 + col-4 + col-4 = 12\n• 4 equal columns: col-3 + col-3 + col-3 + col-3 = 12\n\nIf the total exceeds 12, the extra columns wrap automatically to the next line.",
                codeSnippet = """<div class="container">
  <div class="row g-3">
    <div class="col-8">8 cols (66.6%)</div>
    <div class="col-4">4 cols (33.3%)</div>
  </div>
</div>"""
            )
        }
        q.contains("container") -> {
            ChatMessage(
                isUser = false,
                text = "The difference between .container and .container-fluid:\n\n• .container: Has responsive fixed max-widths (e.g. 720px on tablet, 1140px on desktop) and auto margins centering your content with whitespace on the sides.\n• .container-fluid: Always stretches 100% across the full viewport width edge-to-edge.",
                codeSnippet = """<!-- Fixed centered: -->
<div class="container">...</div>

<!-- 100% full-width: -->
<div class="container-fluid">...</div>"""
            )
        }
        q.contains("breakpoint") -> {
            ChatMessage(
                isUser = false,
                text = "Bootstrap is mobile-first! Breakpoint tiers:\n\n• xs: < 576px (default, no infix, e.g. col-12)\n• sm: ≥ 576px\n• md: ≥ 768px\n• lg: ≥ 992px\n• xl: ≥ 1200px\n• xxl: ≥ 1400px\n\nFor example, class=\"col-12 col-md-6 col-lg-4\" means: 100% width on phones, 50% width on tablets, and 33.3% width on laptops!",
                codeSnippet = """<div class="col-12 col-md-6 col-lg-4">
  Responsive card
</div>"""
            )
        }
        q.contains("flex") || q.contains("center") -> {
            ChatMessage(
                isUser = false,
                text = "To center an element both vertically and horizontally with Bootstrap flexbox utilities, apply:\n\nd-flex justify-content-center align-items-center",
                codeSnippet = """<div class="d-flex justify-content-center align-items-center vh-100 bg-dark text-white">
  <h1>Centered in viewport!</h1>
</div>"""
            )
        }
        q.contains("react") -> {
            ChatMessage(
                isUser = false,
                text = "To use Bootstrap in React, you can either include the Bootstrap CSS CDN in index.html, or use the official React-Bootstrap package which turns Bootstrap elements into type-safe React components:",
                codeSnippet = """import { Container, Row, Col, Button } from 'react-bootstrap';

function App() {
  return (
    <Container className="py-4">
      <Row>
        <Col md={6}>
          <Button variant="primary">Click Me</Button>
        </Col>
      </Row>
    </Container>
  );
}"""
            )
        }
        else -> {
            ChatMessage(
                isUser = false,
                text = "Bootstrap 5 makes responsive web design fast and predictable! With the 12-column grid (.container > .row > .col-*), flexbox utilities (.d-flex), and pre-built components (.card, .navbar, .modal), you can build production-ready UIs without writing custom CSS.",
                codeSnippet = """<button class="btn btn-primary btn-lg shadow-sm">
  Bootstrap Button
</button>"""
            )
        }
    }
}

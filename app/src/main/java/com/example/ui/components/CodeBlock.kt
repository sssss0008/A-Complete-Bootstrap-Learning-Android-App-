package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun CodeBlock(
    code: String,
    modifier: Modifier = Modifier,
    language: String = "JSX",
    showLineNumbers: Boolean = true
) {
    val context = LocalContext.current
    val lines = remember(code) { code.lines() }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Slate950),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Slate800)
    ) {
        Column {
            // Header bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Slate900)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Traffic light dots
                    Box(modifier = Modifier.size(9.dp).clip(RoundedCornerShape(50)).background(Rose500))
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(modifier = Modifier.size(9.dp).clip(RoundedCornerShape(50)).background(Amber500))
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(modifier = Modifier.size(9.dp).clip(RoundedCornerShape(50)).background(Emerald500))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = language,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ReactCyan,
                        fontFamily = FontFamily.Monospace
                    )
                }

                IconButton(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("code", code)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy Code",
                        tint = Slate400,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            // Code Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                Row {
                    if (showLineNumbers) {
                        Column(modifier = Modifier.padding(end = 12.dp)) {
                            lines.indices.forEach { index ->
                                Text(
                                    text = "${index + 1}",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = Slate600
                                )
                            }
                        }
                    }

                    Column {
                        lines.forEach { line ->
                            Text(
                                text = highlightJsxLine(line),
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Lightweight, robust syntax highlighter for JSX / JS
 */
private fun highlightJsxLine(line: String) = buildAnnotatedString {
    val trimmed = line.trimStart()
    if (trimmed.startsWith("//")) {
        withStyle(SpanStyle(color = CodeComment)) {
            append(line)
        }
        return@buildAnnotatedString
    }

    val tokens = line.split(Regex("(?<=[\\s(),.<>{}\\[\\]=+\\-*/;:\"'`])|(?=[\\s(),.<>{}\\[\\]=+\\-*/;:\"'`])"))
    var inString = false
    var stringQuote = ' '

    for (token in tokens) {
        if (!inString && (token.startsWith("\"") || token.startsWith("'") || token.startsWith("`"))) {
            inString = true
            stringQuote = token.first()
            withStyle(SpanStyle(color = CodeString)) {
                append(token)
            }
            if (token.length > 1 && token.endsWith(stringQuote)) {
                inString = false
            }
            continue
        }

        if (inString) {
            withStyle(SpanStyle(color = CodeString)) {
                append(token)
            }
            if (token.endsWith(stringQuote)) {
                inString = false
            }
            continue
        }

        when (token) {
            "import", "from", "export", "default", "function", "const", "let", "var", "return", "if", "else", "switch", "case", "async", "await", "new", "try", "catch", "throw" -> {
                withStyle(SpanStyle(color = CodeKeyword, fontWeight = FontWeight.Bold)) {
                    append(token)
                }
            }
            "useState", "useEffect", "useContext", "useReducer", "useRef", "useMemo", "useCallback", "useId", "useTransition" -> {
                withStyle(SpanStyle(color = ReactCyan, fontWeight = FontWeight.Bold)) {
                    append(token)
                }
            }
            "true", "false", "null", "undefined" -> {
                withStyle(SpanStyle(color = CodeNumber, fontWeight = FontWeight.Bold)) {
                    append(token)
                }
            }
            "div", "span", "button", "input", "h1", "h2", "h3", "h4", "p", "ul", "li", "form", "Card", "Header", "App", "Badge" -> {
                withStyle(SpanStyle(color = CodeTag)) {
                    append(token)
                }
            }
            "onClick", "onChange", "onSubmit", "className", "style", "key", "id", "type", "value", "disabled" -> {
                withStyle(SpanStyle(color = CodeAttribute)) {
                    append(token)
                }
            }
            "=", "+", "-", "*", "/", "=>", "==", "===", "!=", "!==", "&&", "||", "?" -> {
                withStyle(SpanStyle(color = CodeOperator)) {
                    append(token)
                }
            }
            else -> {
                if (token.toIntOrNull() != null) {
                    withStyle(SpanStyle(color = CodeNumber)) {
                        append(token)
                    }
                } else {
                    withStyle(SpanStyle(color = Slate200)) {
                        append(token)
                    }
                }
            }
        }
    }
}

package com.example.ui.screens.reference

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CurriculumData
import com.example.model.BootstrapUtilityItem
import com.example.ui.components.CodeBlock
import com.example.ui.theme.*

@Composable
fun ReferenceScreen(
    onBack: () -> Unit
) {
    var activeTab by remember { mutableStateOf("Utilities") } // "Utilities", "Cheat Sheets", "Glossary"
    var searchQuery by remember { mutableStateOf("") }
    var expandedUtilId by remember { mutableStateOf<String?>("util_spacing") }

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
                    text = "Bootstrap Reference & Cheat Sheets",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
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
                // Search bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search classes, d-flex, spacing, breakpoints...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = BsTextMuted) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BootstrapPurpleLight,
                        unfocusedBorderColor = BsDark800,
                        focusedContainerColor = BsDark900,
                        unfocusedContainerColor = BsDark900,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Navigation Tabs
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Utilities", "Cheat Sheets", "Glossary").forEach { tab ->
                        val isSelected = activeTab == tab
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) BootstrapPurpleLight else BsDark900,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) BootstrapPurpleLight else BsDark800
                            ),
                            modifier = Modifier.clickable { activeTab = tab }
                        ) {
                            Text(
                                text = tab,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) BsDark950 else BsTextLight,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            when (activeTab) {
                "Utilities" -> {
                    val filteredUtils = CurriculumData.utilitiesList.filter {
                        searchQuery.isBlank() || it.name.contains(searchQuery, ignoreCase = true) ||
                        it.summary.contains(searchQuery, ignoreCase = true)
                    }

                    items(filteredUtils) { util ->
                        UtilityReferenceCard(
                            item = util,
                            isExpanded = expandedUtilId == util.id,
                            onToggle = {
                                expandedUtilId = if (expandedUtilId == util.id) null else util.id
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                "Cheat Sheets" -> {
                    items(CurriculumData.cheatSheets) { sheet ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = BsDark900),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(sheet.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = BootstrapPurpleLight)
                                Spacer(modifier = Modifier.height(8.dp))
                                sheet.bullets.forEach { b ->
                                    Text("• $b", fontSize = 12.sp, color = BsTextLight, modifier = Modifier.padding(vertical = 2.dp))
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                CodeBlock(code = sheet.quickSnippet, language = "HTML")
                            }
                        }
                    }
                }
                "Glossary" -> {
                    val filteredGlossary = CurriculumData.glossary.filter {
                        searchQuery.isBlank() || it.term.contains(searchQuery, ignoreCase = true) ||
                        it.definition.contains(searchQuery, ignoreCase = true)
                    }

                    items(filteredGlossary) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            colors = CardDefaults.cardColors(containerColor = BsDark900),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BsDark800)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(item.term, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text(item.category, fontSize = 10.sp, color = BootstrapPurpleLight)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(item.definition, fontSize = 12.sp, color = BsTextLight, lineHeight = 16.sp)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(item.example, fontSize = 11.sp, fontFamily = FontFamily.Monospace, color = BsTextMuted)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UtilityReferenceCard(
    item: BootstrapUtilityItem,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(containerColor = BsDark900),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, if (isExpanded) BootstrapPurpleLight.copy(alpha = 0.5f) else BsDark800)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(item.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = BsDark800,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(item.category, fontSize = 9.sp, color = BootstrapPurpleLight, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = BsTextMuted
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(item.summary, fontSize = 12.sp, color = BsTextLight)

            if (isExpanded) {
                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    color = BsDark950,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.syntax,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = BootstrapInfo,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(item.explanation, fontSize = 12.sp, color = BsTextLight, lineHeight = 17.sp)

                Spacer(modifier = Modifier.height(10.dp))
                CodeBlock(code = item.codeExample, language = "HTML")

                Spacer(modifier = Modifier.height(10.dp))
                Text("COMMON MISTAKES:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BootstrapDanger)
                item.commonMistakes.forEach { m ->
                    Text("• $m", fontSize = 11.sp, color = BsTextLight, modifier = Modifier.padding(vertical = 1.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text("REAL-WORLD CASE: ${item.realWorldCase}", fontSize = 11.sp, color = BootstrapSuccess)
            }
        }
    }
}

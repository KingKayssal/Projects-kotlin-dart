@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gradeapp

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun StudentGradeScreen() {
    val context = LocalContext.current
    var students by remember { mutableStateOf<List<Student>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    val excelParser = remember { ExcelParser(context) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                coroutineScope.launch {
                    students = excelParser.parse(it)
                }
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Student Grade App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF007BFF), Color(0xFF8A2BE2))
                    )
                )
            )
        },
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    onClick = { filePickerLauncher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") },
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Upload Excel File")
                }
                Spacer(modifier = Modifier.width(16.dp))
                FloatingActionButton(
                    onClick = { students = emptyList() },
                ) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (students.isNotEmpty()) {
                StatsCard(students)
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(students) { student ->
                        StudentCard(student)
                    }
                }
            } else {
                EmptyState()
            }
        }
    }
}

@Composable
fun StatsCard(students: List<Student>) {
    val average = students.map { it.score }.average()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Total Students", fontWeight = FontWeight.Bold)
                Text(students.size.toString(), fontSize = 24.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Class Average", fontWeight = FontWeight.Bold)
                Text(String.format("%.2f", average), fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RankCircle(student.rank)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(student.name, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = student.score / 100f,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${student.score}/100")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            GradeBadge(student.grade)
        }
    }
}

@Composable
fun RankCircle(rank: Int) {
    val color = when (rank) {
        1 -> Color(0xFFFFD700) // Gold
        2 -> Color(0xFFC0C0C0) // Silver
        3 -> Color(0xFFCD7F32) // Bronze
        else -> MaterialTheme.colorScheme.primary
    }
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(rank.toString(), color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun GradeBadge(grade: String) {
    val color = when (grade) {
        "A" -> Color(0xFF4CAF50) // Green
        "B" -> Color(0xFF2196F3) // Blue
        "C" -> Color(0xFFFF9800) // Orange
        "D" -> Color(0xFFFF5722) // Deep Orange
        else -> Color(0xFFF44336) // Red
    }
    Box(
        modifier = Modifier
            .background(color, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(grade, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // You can add an illustration here
            Text("No data", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Upload an Excel file to get started")
        }
    }
}
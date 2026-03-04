package com.example.studentgradeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// ─────────────────────────────────────────
// MAIN ACTIVITY
// ─────────────────────────────────────────
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentGradeScreen()
            }
        }
    }
}

// ─────────────────────────────────────────
// MAIN SCREEN
// ─────────────────────────────────────────
@Composable
fun StudentGradeScreen(vm: GradeViewModel = viewModel()) {
    val context = androidx.compose.ui.platform.LocalContext.current

    // File picker launcher
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { vm.parseExcelFile(context, it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {

        // ── TOP BAR ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1565C0), Color(0xFF7B1FA2))
                    )
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column {
                Text(
                    text = "🎓 Student Grade App",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "CA (30%) + Exam (70%) = Final Mark",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // ── BUTTONS ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { fileLauncher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
            ) {
                Icon(Icons.Filled.Upload, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Upload Excel", fontWeight = FontWeight.SemiBold)
            }

            OutlinedButton(
                onClick = { vm.clear() },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, Color(0xFFE53935)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFE53935))
            ) {
                Icon(Icons.Filled.Delete, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Clear", fontWeight = FontWeight.SemiBold)
            }
        }

        // ── CONTENT AREA ──
        when (val state = vm.uiState) {
            is GradeUiState.Idle    -> IdleState()
            is GradeUiState.Loading -> LoadingState()
            is GradeUiState.Error   -> ErrorState(message = state.message)
            is GradeUiState.Success -> SuccessState(
                students      = state.students,
                classAverage  = state.classAverage,
                totalStudents = state.totalStudents,
                highestScore  = state.highestScore,
                lowestScore   = state.lowestScore
            )
        }
    }
}

// ─────────────────────────────────────────
// IDLE STATE
// ─────────────────────────────────────────
@Composable
fun IdleState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "📂", fontSize = 72.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No file uploaded yet",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF424242)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tap 'Upload Excel' to load\nyour student grades",
                fontSize = 14.sp,
                color = Color(0xFF9E9E9E),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Excel format hint card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "📋 Expected Excel Format",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color(0xFF1565C0)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Column A → Student Name", fontSize = 12.sp, color = Color(0xFF424242))
                    Text(text = "Column B → CA Mark (out of 30)", fontSize = 12.sp, color = Color(0xFF424242))
                    Text(text = "Column C → Exam Mark (out of 70)", fontSize = 12.sp, color = Color(0xFF424242))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Final = CA + Exam (out of 100)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF7B1FA2)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────
// LOADING STATE
// ─────────────────────────────────────────
@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = Color(0xFF1565C0),
                strokeWidth = 4.dp,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Reading Excel file...", fontSize = 16.sp, color = Color(0xFF616161))
        }
    }
}

// ─────────────────────────────────────────
// ERROR STATE
// ─────────────────────────────────────────
@Composable
fun ErrorState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
            border = BorderStroke(1.dp, Color(0xFFEF9A9A)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                Text(text = "❌", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Something went wrong",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC62828),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = message, color = Color(0xFFE53935), fontSize = 13.sp)
                }
            }
        }
    }
}

// ─────────────────────────────────────────
// SUCCESS STATE
// ─────────────────────────────────────────
@Composable
fun SuccessState(
    students     : List<Student>,
    classAverage : Double,
    totalStudents: Int,
    highestScore : Double,
    lowestScore  : Double
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            StatsCard(
                totalStudents = totalStudents,
                classAverage  = classAverage,
                highestScore  = highestScore,
                lowestScore   = lowestScore
            )
        }
        item {
            Text(
                text = "📋 Student Rankings",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        itemsIndexed(students) { _, student ->
            StudentCard(student = student)
        }
    }
}

// ─────────────────────────────────────────
// STATS CARD
// ─────────────────────────────────────────
@Composable
fun StatsCard(
    totalStudents: Int,
    classAverage : Double,
    highestScore : Double,
    lowestScore  : Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Top row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem(emoji = "👥", value = "$totalStudents",
                    label = "Students", color = Color(0xFF1565C0))
                Divider(modifier = Modifier.height(60.dp).width(1.dp), color = Color(0xFFE0E0E0))
                StatItem(emoji = "📊",
                    value = String.format("%.1f", classAverage),
                    label = "Class Avg", color = Color(0xFF7B1FA2))
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFEEEEEE))
            Spacer(modifier = Modifier.height(12.dp))

            // Bottom row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem(emoji = "🏆",
                    value = String.format("%.1f", highestScore),
                    label = "Highest", color = Color(0xFF2E7D32))
                Divider(modifier = Modifier.height(60.dp).width(1.dp), color = Color(0xFFE0E0E0))
                StatItem(emoji = "📉",
                    value = String.format("%.1f", lowestScore),
                    label = "Lowest", color = Color(0xFFC62828))
            }
        }
    }
}

// ─────────────────────────────────────────
// STAT ITEM (reusable)
// ─────────────────────────────────────────
@Composable
fun StatItem(emoji: String, value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = emoji, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = color)
        Text(text = label, fontSize = 12.sp, color = Color(0xFF9E9E9E))
    }
}

// ─────────────────────────────────────────
// STUDENT CARD
// ─────────────────────────────────────────
@Composable
fun StudentCard(student: Student) {
    val gradeCol = gradeColor(student.grade)
    val rankCol  = rankColor(student.rank)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // ── Top row: rank + name + grade badge ──
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Rank circle
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(rankCol),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${student.rank}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Name and final score
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = student.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF212121)
                    )
                    Text(
                        text = "Final: ${String.format("%.1f", student.finalScore)} / 100",
                        fontSize = 13.sp,
                        color = Color(0xFF757575)
                    )
                }

                // Grade badge
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(gradeCol),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = student.grade,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ── Marks breakdown row ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F7FA))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // CA mark
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "CA Mark", fontSize = 11.sp, color = Color(0xFF9E9E9E))
                    Text(
                        text = "${String.format("%.1f", student.caScore)} / 30",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1565C0)
                    )
                }

                Divider(modifier = Modifier.height(36.dp).width(1.dp), color = Color(0xFFE0E0E0))

                // Exam mark
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Exam Mark", fontSize = 11.sp, color = Color(0xFF9E9E9E))
                    Text(
                        text = "${String.format("%.1f", student.examScore)} / 70",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF7B1FA2)
                    )
                }

                Divider(modifier = Modifier.height(36.dp).width(1.dp), color = Color(0xFFE0E0E0))

                // Final mark
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Final", fontSize = 11.sp, color = Color(0xFF9E9E9E))
                    Text(
                        text = "${String.format("%.1f", student.finalScore)} / 100",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = gradeCol
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ── Progress bar ──
            LinearProgressIndicator(
                progress = { (student.finalScore / 100).toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = gradeCol,
                trackColor = Color(0xFFEEEEEE)
            )
        }
    }
}
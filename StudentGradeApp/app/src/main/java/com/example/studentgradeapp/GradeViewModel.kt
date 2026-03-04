sAAAApackage com.example.studentgradeapp

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.xssf.usermodel.XSSFWorkbook

// ─────────────────────────────────────────
// UI STATE
// ─────────────────────────────────────────
sealed class GradeUiState {
    object Idle    : GradeUiState()
    object Loading : GradeUiState()
    data class Success(
        val students     : List<Student>,
        val classAverage : Double,
        val totalStudents: Int,
        val highestScore : Double,
        val lowestScore  : Double
    ) : GradeUiState()
    data class Error(val message: String) : GradeUiState()
}

// ─────────────────────────────────────────
// VIEWMODEL
// ─────────────────────────────────────────
class GradeViewModel : ViewModel() {

    var uiState: GradeUiState by mutableStateOf(GradeUiState.Idle)
        private set

    fun parseExcelFile(context: Context, uri: Uri) {
        viewModelScope.launch {
            uiState = GradeUiState.Loading
            try {
                val students = withContext(Dispatchers.IO) {
                    readExcel(context, uri)
                }

                if (students.isEmpty()) {
                    uiState = GradeUiState.Error(
                        "No valid student data found.\n" +
                                "Make sure your Excel has:\n" +
                                "Column A = Name\n" +
                                "Column B = CA mark (out of 30)\n" +
                                "Column C = Exam mark (out of 70)"
                    )
                    return@launch
                }

                // Sort by final score descending then assign ranks
                val sorted = students
                    .sortedByDescending { it.finalScore }
                    .mapIndexed { index, student ->
                        student.copy(rank = index + 1)
                    }

                val average = sorted.map { it.finalScore }.average()
                val highest = sorted.first().finalScore
                val lowest  = sorted.last().finalScore

                uiState = GradeUiState.Success(
                    students      = sorted,
                    classAverage  = average,
                    totalStudents = sorted.size,
                    highestScore  = highest,
                    lowestScore   = lowest
                )

            } catch (e: Exception) {
                uiState = GradeUiState.Error(
                    "Failed to read file:\n${e.localizedMessage ?: "Unknown error"}"
                )
            }
        }
    }

    private fun readExcel(context: Context, uri: Uri): List<Student> {
        val students = mutableListOf<Student>()

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw Exception("Could not open file")

        inputStream.use { stream ->
            val workbook = XSSFWorkbook(stream)
            val sheet    = workbook.getSheetAt(0)

            // Start from row 1 to skip header row
            for (rowIndex in 1..sheet.lastRowNum) {
                val row = sheet.getRow(rowIndex) ?: continue

                // Get cells A, B, C
                val nameCell  = row.getCell(0) ?: continue
                val caCell    = row.getCell(1) ?: continue
                val examCell  = row.getCell(2) ?: continue

                // Extract name
                val name = nameCell.toString().trim()
                if (name.isEmpty()) continue

                // Extract CA score (out of 30)
                val caScore = try {
                    caCell.numericCellValue
                } catch (e: Exception) {
                    caCell.toString().trim().toDoubleOrNull() ?: continue
                }

                // Extract Exam score (out of 70)
                val examScore = try {
                    examCell.numericCellValue
                } catch (e: Exception) {
                    examCell.toString().trim().toDoubleOrNull() ?: continue
                }

                // Validate ranges
                if (caScore < 0 || caScore > 30) continue
                if (examScore < 0 || examScore > 70) continue

                // Calculate final score and grade
                val finalScore = calculateFinalScore(caScore, examScore)
                val grade      = calculateGrade(finalScore)

                students.add(
                    Student(
                        name      = name,
                        caScore   = caScore,
                        examScore = examScore,
                        finalScore = finalScore,
                        grade     = grade,
                        rank      = 0
                    )
                )
            }
            workbook.close()
        }
        return students
    }

    fun clear() {
        uiState = GradeUiState.Idle
    }
}
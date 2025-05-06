package org.example.presenceapp.ui.feature.attendance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.presenceapp.domain.entities.AbsenceReason
import org.example.presenceapp.domain.entities.AttendanceType
import org.example.presenceapp.domain.common.Student
import org.example.presenceapp.ui.feature.attendance.AttendanceScreenModel
import org.example.presenceapp.ui.feature.attendance.AttendanceScreenModel.Companion.toDisplayStatus
import org.example.presenceapp.ui.feature.commons.CommonButton
import org.example.presenceapp.ui.feature.commons.CommonDialog
import org.example.presenceapp.ui.feature.commons.CommonLabel
import org.example.presenceapp.ui.feature.commons.CommonMediumText
import org.example.presenceapp.ui.feature.commons.CommonRegularText
import org.example.presenceapp.ui.theme.AppTheme

@Composable
fun AttendanceColumn(
    modifier: Modifier,
    screenModel: AttendanceScreenModel
) {
    val groups by screenModel.groupedStudents.collectAsState(emptyList())
    val attendanceMap by screenModel.attendanceMap.collectAsState()

    var expanded by remember { mutableStateOf<String?>(null) }
    val selectedStudents = remember { mutableStateOf<Set<String>>(emptySet()) }
    var isSelectionMode by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()

    LaunchedEffect(groups) {
        listState.scrollToItem(0)
    }

    LaunchedEffect(selectedStudents.value) {
        if (selectedStudents.value.isEmpty()) {
            isSelectionMode = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.white)
    ) {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (isSelectionMode) 80.dp else 0.dp)
        ) {
            fun renderStudentGroup(
                group: List<Student>,
                label: String
            ) {
                if (group.isNotEmpty()) {
                    item {
                        CommonLabel(
                            text = label,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                    items(group, key = { student -> student.id }) { student ->
                        val isSelected = selectedStudents.value.contains(student.id)
                        val attendance = attendanceMap[student.id]

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(72.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onLongPress = {
                                            isSelectionMode = true
                                            selectedStudents.value =
                                                selectedStudents.value.toMutableSet().apply {
                                                    if (contains(student.id)) remove(student.id) else add(
                                                        student.id
                                                    )
                                                }
                                        },
                                        onTap = {
                                            if (isSelectionMode) {
                                                selectedStudents.value =
                                                    selectedStudents.value.toMutableSet().apply {
                                                        if (contains(student.id)) remove(student.id) else add(
                                                            student.id
                                                        )
                                                    }
                                            }
                                            if (!isSelectionMode && attendance?.type == AttendanceType.ABSENT) {
                                                expanded = student.id
                                            }
                                        }
                                    )
                                }
                                .border(
                                    width = 1.dp,
                                    color = if (isSelected) AppTheme.colors.black else AppTheme.colors.gray,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(12.dp)
                        ) {
                            CommonRegularText(
                                text = student.name,
                                color = AppTheme.colors.black,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .widthIn(min = 80.dp)
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                                    .padding(8.dp)
                            ) {
                                attendance?.type?.let {
                                    CommonRegularText(
                                        text = attendance.toDisplayStatus(),
                                        color = AppTheme.colors.black,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }
                    }
                }
            }

            groups.forEach { (title, students) ->
                renderStudentGroup(students, title)
            }
        }

        if (isSelectionMode) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(color = AppTheme.colors.white)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                listOf(
                    AttendanceType.PRESENT to "присут",
                    AttendanceType.ABSENT to "отсут"
                ).forEach { (status, label) ->
                    CommonButton(
                        onClick = {
                            screenModel.updateAttendanceForSelected(
                                selectedStudents.value,
                                status
                            )
                            isSelectionMode = false
                            selectedStudents.value = emptySet()
                        },
                        content = {
                            CommonRegularText(
                                text = label,
                                color = AppTheme.colors.white,
                                modifier = Modifier
                            )
                        },
                        modifier = Modifier
                    )
                }
            }
        }

        expanded?.let { studentId ->
            val student = groups
                .flatMap { it.second }
                .find { it.id == studentId }

            if (student != null) {
                CommonDialog(
                    expanded = true,
                    onDismiss = { expanded = null },
                    content = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(16.dp)
                        ) {
                            CommonMediumText(
                                text = "Выберите причину отсутствия",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            AbsenceReason.entries.forEach { reason ->
                                CommonButton(
                                    onClick = {
                                        screenModel.updateAbsenceReason(studentId, reason.name)
                                        expanded = null
                                    },
                                    content = {
                                        CommonRegularText(
                                            text = reason.toCustomString(),
                                            color = AppTheme.colors.white,
                                            modifier = Modifier
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                )
                            }
                        }
                    },
                )
            }
        }
    }
}
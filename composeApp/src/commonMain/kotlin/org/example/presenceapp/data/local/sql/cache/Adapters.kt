package org.example.presenceapp.data.local.sql.cache

object Adapters {
    val scheduleAdapter = Schedule.Adapter(
        idAdapter = IntAdapter(),
        lessonNumberAdapter = IntAdapter(),
        dayOfWeekAdapter = IntAdapter(),
        subjectIdAdapter = IntAdapter()
    )
    val subjectAdapter = Subject.Adapter(
        idAdapter = IntAdapter()
    )
}
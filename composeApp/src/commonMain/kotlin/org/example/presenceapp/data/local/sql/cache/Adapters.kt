package org.example.presenceapp.data.local.sql.cache

object Adapters {
    val scheduleAdapter = Schedule.Adapter(
        scheduleRowIdAdapter = IntAdapter(),
        dayOfWeekAdapter = IntAdapter()
    )
    val subjectAdapter = Subject.Adapter(
        idAdapter = IntAdapter()
    )
    val scheduleInfoAdapter = ScheduleInfo.Adapter(
        subjectIdAdapter = IntAdapter(),
        lessonNumberAdapter = IntAdapter(),
        idAdapter = IntAdapter()
    )
}
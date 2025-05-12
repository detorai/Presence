package org.example.presenceapp.data.local.sql.cache

import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.ScheduleInfo
import org.example.presenceapp.domain.entities.Subject

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = PresenceDatabase(
       ScheduleAdapter = Adapters.scheduleAdapter,
       SubjectAdapter = Adapters.subjectAdapter,
       ScheduleInfoAdapter = Adapters.scheduleInfoAdapter,
       driver = databaseDriverFactory.createDriver()
    )
    private val dbQuery = database.presenceDatabaseQueries

    internal fun setSubject(subject: Subject) {
        dbQuery.transaction {
            if (dbQuery.checkSubjectExists(subject.id).executeAsOne()) {
                dbQuery.updateSubject(
                    name = subject.name,
                    id = subject.id
                )
            } else {
                dbQuery.insertSubject(
                    id = subject.id,
                    name = subject.name
                )
            }
        }
    }

    internal fun setScheduleInfo(scheduleInfo: ScheduleInfo) {
        dbQuery.transaction {
            setSubject(scheduleInfo.subject)

            if (dbQuery.checkScheduleInfoExists(scheduleInfo.id).executeAsOne()) {
                dbQuery.updateScheduleInfo(
                    lessonNumber = scheduleInfo.lessonNumber,
                    audience = scheduleInfo.audience,
                    subjectId = scheduleInfo.subject.id,
                    id = scheduleInfo.id
                )
            } else {
                dbQuery.insertScheduleInfo(
                    id = scheduleInfo.id,
                    lessonNumber = scheduleInfo.lessonNumber,
                    audience = scheduleInfo.audience,
                    subjectId = scheduleInfo.subject.id
                )
            }
        }
    }

    internal fun setSchedule(schedule: Schedule) {
        dbQuery.transaction {
            schedule.scheduleInfo.forEach { scheduleInfo ->
                setScheduleInfo(scheduleInfo)

                if (!dbQuery.checkScheduleExists(
                        dayOfWeek = schedule.dayOfWeek,
                        scheduleRowId = scheduleInfo.id
                    ).executeAsOne()) {
                    dbQuery.insertSchedule(
                        dayOfWeek = schedule.dayOfWeek,
                        scheduleRowId = scheduleInfo.id
                    )
                }
            }
        }
    }

    // Получение всего расписания
    internal fun getAllSchedules(): List<Schedule> {
        val scheduleMap = mutableMapOf<Int, MutableList<ScheduleInfo>>()

        dbQuery.getAllSchedules().executeAsList().forEach { row ->
            val scheduleInfo = ScheduleInfo(
                id = row.scheduleRowId,
                lessonNumber = row.lessonNumber,
                audience = row.audience,
                subject = Subject(
                    id = row.subjectId,
                    name = row.subjectName
                )
            )

            scheduleMap.getOrPut(row.dayOfWeek) { mutableListOf() }.add(scheduleInfo)
        }

        return scheduleMap.map { (dayOfWeek, scheduleInfo) ->
            Schedule(
                dayOfWeek = dayOfWeek,
                scheduleInfo = scheduleInfo
            )
        }
    }

    internal fun getSchedulesByDay(dayOfWeek: Int): Schedule? {
        val scheduleInfos = dbQuery.getSchedulesByDay(dayOfWeek)
            .executeAsList()
            .map { row ->
                ScheduleInfo(
                    id = row.scheduleRowId,
                    lessonNumber = row.lessonNumber,
                    audience = row.audience,
                    subject = Subject(
                        id = row.subjectId,
                        name = row.subjectName
                    )
                )
            }

        return if (scheduleInfos.isNotEmpty()) {
            Schedule(
                dayOfWeek = dayOfWeek,
                scheduleInfo = scheduleInfos
            )
        } else {
            null
        }
    }
}
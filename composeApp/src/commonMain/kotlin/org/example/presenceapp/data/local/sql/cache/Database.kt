package org.example.presenceapp.data.local.sql.cache

import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Subject

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = PresenceDatabase(
       ScheduleAdapter = Adapters.scheduleAdapter,
       SubjectAdapter = Adapters.subjectAdapter,
       driver = databaseDriverFactory.createDriver()
    )
    private val dbQuery = database.presenceDatabaseQueries

    internal fun setSubjects( subject: Subject ){
        dbQuery.transaction{
            val exists = dbQuery.checkSubjectExists(subject.id).executeAsOne()
            if (!exists) {
                dbQuery.insertSubject(
                    id = subject.id,
                    name = subject.name
                )
            }
        }
    }

    internal fun setSchedule( schedule: Schedule ){
        dbQuery.transaction {
            val exists = dbQuery.checkScheduleExists(schedule.id).executeAsOne()
            if (!exists) {
                dbQuery.insertSchedule(
                    id = schedule.id,
                    lessonNumber = schedule.lessonNumber,
                    audience = schedule.audience,
                    dayOfWeek = schedule.dayOfWeek,
                    subjectId = schedule.subject.id
                )
            }
        }
    }

    internal fun getAllSchedule(): List<Schedule> {
        return dbQuery.getAllSchedules(::mapSchedule).executeAsList()
    }

    private fun mapSchedule(
        schedule_id: Int,
        lessonNumber: Int,
        audience: String,
        dayOfWeek: Int,
        subject_id: Int,
        subject_name: String
    ): Schedule{
        return Schedule(
            id = schedule_id,
            lessonNumber = lessonNumber,
            audience = audience,
            dayOfWeek = dayOfWeek,
            subject = Subject(
                subject_id, subject_name
            )
        )
    }



//    internal fun setUserInfo( loginResponse: LoginResponse ) {
//        dbQuery.transaction {
//            dbQuery.removeUserInfo()
//            dbQuery.insertUserInfo(
//                userName = loginResponse.user.fio,
//                userGroup = loginResponse.user.responsible.first().group.name,
//                userRole = loginResponse.user.responsible.first().responsibleType.name
//            )
//        }
//    }
//    internal fun getUserInfo(): UserInfo {
//        return dbQuery.selectUserInfo(::mapUserInfo).executeAsOne()
//    }
//
//    private fun mapUserInfo(
//        userName: String,
//        userGroup: String,
//        userRole: String
//    ): UserInfo {
//        return UserInfo(
//            userName, userGroup, userRole
//        )
//    }
}
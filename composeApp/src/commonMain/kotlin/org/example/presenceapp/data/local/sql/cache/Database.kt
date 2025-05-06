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
    internal fun setScheduleInfo( scheduleInfo: ScheduleInfo ){
        dbQuery.transaction {
            val exists = dbQuery.checkScheduleInfoExists(scheduleInfo.id).executeAsOne()
            if (!exists) {
                dbQuery.insertScheduleInfo(
                    id = scheduleInfo.id,
                    lessonNumber = scheduleInfo.lessonNumber,
                    audience = scheduleInfo.audience,
                    subjectId = scheduleInfo.subject.id
                )
            }
        }
    }

//    internal fun setSchedule (schedule: Schedule) {
//        dbQuery.transaction {
//            val exists = dbQuery.checkScheduleExists(schedule.dayOfWeek, schedule.scheduleInfo.id).executeAsOne()
//            if (!exists) {
//                dbQuery.insertSchedule(
//                    dayOfWeek = schedule.dayOfWeek,
//                    scheduleRowId = schedule.scheduleInfo.id
//                )
//            }
//        }
//    }
//    internal fun getAllSchedule(): List<Schedule> {
//        return dbQuery.getAllSchedules(::mapSchedule).executeAsList()
//    }

//    internal fun setSchedule( schedule: Schedule ){
//        dbQuery.transaction {
//            val exists = dbQuery.checkScheduleExists(schedule.scheduleInfo.id).executeAsOne()
//            if (!exists) {
//                dbQuery.insertSchedule(
//                    id = schedule.scheduleInfo.id,
//                    lessonNumber = schedule.scheduleInfo.lessonNumber,
//                    audience = schedule.scheduleInfo.audience,
//                    dayOfWeek = schedule.dayOfWeek,
//                    subjectId = schedule.scheduleInfo.subject.id
//                )
//            }
//        }
//    }e
//
//    internal fun getAllSchedule(): List<Schedule> {
//        return dbQuery.getAllSchedules(::mapSchedule).executeAsList()
//    }
//
//    private fun mapSchedule(
//    dayOfWeek: Int,
//    scheduleRowId: Int,
//    lessonNumber: Int,
//    audience: String,
//    subjectId: Int,
//    subjectName: String
//    ): Schedule{
//        return Schedule(
//            dayOfWeek = dayOfWeek,
//            scheduleInfo = ScheduleInfo(
//            subject = Subject(
//                subjectId, subjectName
//            ),
//            id = scheduleRowId,
//            lessonNumber = lessonNumber,
//            audience = audience,
//            )
//        )
//    }



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
package org.example.presenceapp.data.local.sql.cache

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
            val exists = dbQuery.checkSubjectExists(subject.id)
                .executeAsOne()
            dbQuery.insertSubject(
                id = subject.id,
                name = subject.name
            )
        }
    }

    internal fun setSchedule(){}



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
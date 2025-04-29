package org.example.presenceapp.data.remote.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import org.example.presenceapp.data.common.dto.student.StudentRequestDto

interface StudentApi {
    @POST ("api/v1/student/{student_id}/presetting")
    fun setStudentPresetting(@Path student_id: Int, @Body studentRequestDto: StudentRequestDto)
}
package com.dynamo.task.data

import com.dynamo.task.data.model.Course
import com.dynamo.task.data.model.CoursesResponse
import io.reactivex.Single
import retrofit2.http.GET


interface Webservice {

    @GET("courses")
    fun getCourses(): Single<CoursesResponse>

}

package com.dynamo.task.data.api

import com.dynamo.task.data.Webservice
import com.dynamo.task.data.model.Course
import com.dynamo.task.data.model.CoursesResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val webservice: Webservice) {

    fun getCourses() : Single<CoursesResponse> {
        return webservice.getCourses()
    }

}
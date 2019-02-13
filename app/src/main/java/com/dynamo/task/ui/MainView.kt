package com.dynamo.task.ui

import com.dynamo.task.data.model.Course

interface MainView {
    fun showCourses(courses : List<Course>)
    fun showErrorLoadingData()
}
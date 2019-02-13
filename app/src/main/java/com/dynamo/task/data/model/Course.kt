package com.dynamo.task.data.model

import java.io.Serializable

data class Course(val title: String,
                  val subtitle: String,
                  val image: String,
                  val expected_learning: String,
                  val instructors: List<Instructor>) : Serializable


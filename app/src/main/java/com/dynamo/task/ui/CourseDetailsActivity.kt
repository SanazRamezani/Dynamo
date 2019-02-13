package com.dynamo.task.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dynamo.task.R
import com.dynamo.task.data.model.Course
import com.dynamo.task.util.App
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_details.*
import kotlinx.android.synthetic.main.course_layout.*

class CourseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        (application as App).inject(this)
        val course = getIntent().getExtras().getSerializable(MainActivity.EXTRA_COURSE) as? Course

        courseName.text = course?.title ?: ""
        if(course?.instructors?.size!! > 0){
            instructorName.text = course?.instructors?.get(0)?.name
            instructorBio.text = course?.instructors?.get(0)?.bio
            showImage(course?.instructors?.get(0)?.image);
        }

    }

    private fun showImage(url: String){
        if(!url.isEmpty()){
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.icon_course)
                .into(imageView)
        } else {
            Picasso.get()
                .load(R.drawable.icon_course)
                .placeholder(R.drawable.icon_course)
                .into(imageView)
        }
    }
}
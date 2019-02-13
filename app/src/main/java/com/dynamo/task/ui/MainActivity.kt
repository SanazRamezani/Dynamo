package com.dynamo.task.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dynamo.task.R
import com.dynamo.task.data.model.Course
import com.dynamo.task.util.App
import com.dynamo.task.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() , MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var recyclerAdapter: CourseListRecyclerAdapter

    companion object {
        const val EXTRA_COURSE = "ExtraCourse"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).inject(this)

        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerAdapter = CourseListRecyclerAdapter({course : Course -> courseItemClicked(course)})
        recyclerView.adapter = recyclerAdapter

        swipeRefreshView.setOnRefreshListener {fetchCourses()}

        presenter.onCreate(this)
        fetchCourses()
    }

    override fun showCourses(courses: List<Course>) {
        recyclerAdapter.setItems(courses)
        recyclerAdapter.notifyDataSetChanged()
        swipeRefreshView.isRefreshing = false
    }

    override fun showErrorLoadingData() {
        swipeRefreshView.isRefreshing = false
        showToast(R.string.error_generic)
    }

    private fun fetchCourses(){
        swipeRefreshView.isRefreshing = true
        presenter.fetchCourses()
    }

    private fun courseItemClicked(course : Course) {
        // Launch detail activity, pass course
        val showDetailActivityIntent = Intent(this, CourseDetailsActivity::class.java)
        showDetailActivityIntent.putExtra(EXTRA_COURSE, course)
        startActivity(showDetailActivityIntent)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}

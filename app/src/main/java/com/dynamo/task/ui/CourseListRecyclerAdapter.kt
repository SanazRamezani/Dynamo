package com.dynamo.task.ui

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dynamo.task.R
import com.dynamo.task.data.model.Course
import com.squareup.picasso.Picasso
import java.util.ArrayList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter



class CourseListRecyclerAdapter(val clickListener: (Course) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = ArrayList<Course>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return CourseViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val courseHolder = holder as CourseViewHolder
        val course = items[position]

        courseHolder.titleTextView.text = course.title
        courseHolder.subtitleTextView.text = course.subtitle
        setImage(courseHolder.itemView.context, course.image, courseHolder.imageView)

        courseHolder.itemView.setOnClickListener { clickListener(course)}

    }

    private fun setImage(context: Context, url: String, imageView: ImageView){
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        if(!url.isEmpty()){
            Picasso.get()
                .load(url)
                .placeholder(circularProgressDrawable)
                .into(imageView)
        } else {
            Picasso.get()
                .load(R.drawable.icon_course)
                .placeholder(circularProgressDrawable)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Course>) {
        this.items.clear()
        this.items.addAll(items)
    }

    private inner class CourseViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        internal var titleTextView: TextView = v.findViewById(R.id.titleTextView)
        internal var subtitleTextView: TextView = v.findViewById(R.id.subtitleTextView)
        internal var imageView: ImageView = v.findViewById(R.id.imageView)
    }
}

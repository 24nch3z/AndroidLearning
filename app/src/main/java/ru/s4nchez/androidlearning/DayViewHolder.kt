package ru.s4nchez.androidlearning

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(day: Int) {
        (itemView as TextView).text = day.toString()
    }
}
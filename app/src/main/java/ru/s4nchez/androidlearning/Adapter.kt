package ru.s4nchez.androidlearning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val days: Int) : RecyclerView.Adapter<DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false))
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE / 2
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(position % days + 1)
    }
}
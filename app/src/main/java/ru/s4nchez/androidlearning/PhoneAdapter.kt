package ru.s4nchez.androidlearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_phone.view.*
import ru.s4nchez.androidlearning.database.entity.Phone

class PhoneAdapter : RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    private var list = ArrayList<Phone>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItems(newItems: List<Phone>) {
        list = ArrayList(newItems)
        notifyDataSetChanged()
    }

    class PhoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(phone: Phone) {
            itemView.title_view.text = phone.number
        }
    }
}
package com.e.paginatorkotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.paginatorkotlindemo.model.DataModel
import com.e.paginatorkotlindemo.R
import kotlinx.android.synthetic.main.item_list.view.*

class PaginationAdapter(
    private val list: ArrayList<DataModel>
) : RecyclerView.Adapter<PaginationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textView1.text = list[position].text1
        holder.itemView.textView2.text = list[position].text2
    }

    fun addItems(list: ArrayList<DataModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

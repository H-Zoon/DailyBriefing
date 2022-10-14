package com.example.dailybriefing

import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import java.util.ArrayList

internal class InfoRecyclerView
constructor(private val arrayList: ArrayList<AdapterItem>) :
    RecyclerView.Adapter<InfoRecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recyclerview, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = arrayList[position].title
        viewHolder.main.text = arrayList[position].main
        //viewHolder.icon.setImageIcon(arrayList[position].icon)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val adapterItem: AdapterItem = payloads[0] as AdapterItem

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val main: TextView
        //val icon: ImageView

        //ViewHolder
        init {
            title = view.findViewById<View>(R.id.title) as TextView
            main = view.findViewById<View>(R.id.main) as TextView
            //icon = view.findViewById<View>(R.id.icon) as ImageView
        }
    }
}
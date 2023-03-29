package com.example.loginandsignup.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginandsignup.Lost
import com.example.loginandsignup.Models.Item
import com.example.loginandsignup.R

class MyAdapter(val context: Context, private val itemList: ArrayList<Item> = ArrayList<Item>(), private val onItemClickListener: Lost) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    private val headings = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = itemList[position]

        holder.topic.text = currentitem.heading
        Glide.with(context).load(currentitem.image).into(holder.imageView)

        holder.itemView.setOnClickListener{
            onItemClickListener.onItemItemClicked(position)
        }

        currentitem.heading?.let { headings.add(it) }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateItemList(itemList : List<Item>){

        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(currentitem: Item) {

        }

        val topic : TextView = itemView.findViewById(R.id.topic)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)



    }

}
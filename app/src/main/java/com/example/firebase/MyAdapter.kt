package com.example.firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter( private var retrieveList: ArrayList<Retrieve>, var listen: OnItemClickListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var nListener: OnItemClickListener

    interface OnItemClickListener{

        fun onItemClick(position:Int,value: View?)
    }
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        nListener = listener
//    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  itemView= LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent, false)
        return MyViewHolder(itemView,listen)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     var currentItem = retrieveList[position]
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.number.text = currentItem.number
//        holder.firstName.setOnClickListener {
//            holder.listener.onItemClick(position,it)
//        }
    }

    override fun getItemCount(): Int {
    return retrieveList.size
    }

  inner  class MyViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){

       // val listener = listener
        var firstName : TextView = itemView.findViewById(R.id.fName)
        var lastName : TextView = itemView.findViewById(R.id.lName)
        var number : TextView = itemView.findViewById(R.id.my_age)
   init {
       itemView.setOnClickListener {
           listener.onItemClick(adapterPosition,itemView)
       }
   }
    }
}
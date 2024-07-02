package com.harjot.ordermenuapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class OrderAdpater(var array: ArrayList<MenuModel>, var orderClickInterface: OrderClickInterface) :
    RecyclerView.Adapter<OrderAdpater.ViewHolder> () {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var etItemName: TextView = view.findViewById(R.id.tvOrderName)
//        var etItemPrice: TextView = view.findViewById(R.id.tvOrderprice)
        var ibtnOrderAdd: ImageButton = view.findViewById(R.id.ibtnOrderAdd)
        var ibtnOrderSub: ImageButton = view.findViewById(R.id.ibtnOrderSub)
        var tvOrderCount:TextView = view.findViewById(R.id.tvOrderCount)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var initview =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_show_add_items, parent, false)
        return ViewHolder(initview)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etItemName.setText(array[position].name.toString())
//        holder.etItemPrice.setText(array[position].totalPrice.toString())
        holder.tvOrderCount.setText(array[position].counter.toString())
        holder.ibtnOrderAdd.setOnClickListener{
            orderClickInterface.addCounter(position)
        }
        holder.ibtnOrderSub.setOnClickListener {
            orderClickInterface.removeCounter(position)
        }



    }
    override fun getItemCount(): Int {
        Log.e("TAG"," order list size ${array.size}")
        return array.size
    }
}
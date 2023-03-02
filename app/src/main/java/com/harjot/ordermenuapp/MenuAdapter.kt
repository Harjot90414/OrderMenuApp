package com.harjot.ordermenuapp;

import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MenuAdapter(var menulist: ArrayList<MenuModel>, var menuClickInterface: MenuClickInterface) : RecyclerView.Adapter<MenuAdapter.ViewHolder> () {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var etItemName: TextView = view.findViewById(R.id.tvmenuitem)
        var etItemPrice: TextView = view.findViewById(R.id.tvmenuprice)
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var initview =
                LayoutInflater.from(parent.context).inflate(R.layout.layout_menuitem, parent, false)
            return ViewHolder(initview)
        }



        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.etItemName.setText(menulist[position].name.toString())
            holder.etItemPrice.setText(menulist[position].price.toString())
            holder.view.setOnClickListener { menuClickInterface.removeCliCK(menulist[position], position) }
        }
        override fun getItemCount(): Int {
            return menulist.size
        }

}



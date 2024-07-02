package com.harjot.ordermenuapp

import java.text.FieldPosition

interface MenuClickInterface {
    fun removeCliCK(menuModel: MenuModel, position: Int)
}
interface OrderClickInterface{
    fun addCounter(position: Int)
    fun removeCounter(position: Int)
}
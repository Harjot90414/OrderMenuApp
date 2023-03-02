package com.harjot.ordermenuapp

data class MenuModel(var name:String?=null, var price: Double? = 0.0, var quantity:Int=1){
    override fun toString(): String {
        return "$name"
    }
    var totalPrice = 0.0
    get() {
        return (price?:0.0).times(quantity)
    }
}





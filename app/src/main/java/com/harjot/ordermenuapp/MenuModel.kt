package com.harjot.ordermenuapp

data class MenuModel(
    var name:String?=null,
//    var price: Int? = 0,
    var quantity:Int=0,
    var counter:Int=1
)
{
    override fun toString(): String {
        return "$name"
    }
//    var totalPrice = 0
//    get() {
//        return (price?:0).times(quantity)
//    }
}





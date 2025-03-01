package com.example.domain.model

data class Product(
    val id :Long,
    val title:String,
    val price:Double,
    val category:String,
    val image:String,
    val description:String
){
    val priceString:String
        get() = "$$price"
}
package com.example.data.model

import com.example.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
class DataProductModel(
    val id :Long,
    val title:String,
    val price:Double,
    val category:String,
    val image:String,
    val description:String
) {

    fun toProduct()=Product(
        id=id,
        title=title,
        price=price,
        category=category,
        description = description,
        image = image
    )
}
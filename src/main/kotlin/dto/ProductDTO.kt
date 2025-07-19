package com.mafiarosa.dto

import com.mafiarosa.db.entity.Image
import com.mafiarosa.db.entity.Product
import com.mafiarosa.dto.ImageDTO.Companion.data

@Suppress
class ProductDTO(val name: String, val price: Float, val description: String, val images: List<ImageDTO>) {

    companion object {
        fun Product.data() = ProductDTO(name, price, description, images.map { it.data() })
    }
}
package com.mafiarosa.dto

import com.mafiarosa.db.entity.Image
import kotlinx.serialization.Serializable

@Serializable
class ImageDTO(val uri: String, val description: String) {

    companion object {
        fun Image.data() = ImageDTO(uri, description)
    }
}
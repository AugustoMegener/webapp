package com.mafiarosa.dto

import kotlinx.serialization.Serializable

@Serializable
data class IndexDTO(val newsletterEmail: String = "",
                    val newsletterInfo: String = "",
                    val contactName: String = "",
                    val contactEmail: String = "",
                    val contactMessage: String = "",
                    val contactInfo: String = "")
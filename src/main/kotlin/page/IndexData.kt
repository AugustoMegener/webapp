package com.mafiarosa.page

import kotlinx.serialization.Serializable

@Serializable
data class IndexData(val newsletterEmail: String = "",
                     val newsletterInfo: String = "",
                     val contactName: String = "",
                     val contactEmail: String = "",
                     val contactMessage: String = "",
                     val contactInfo: String = "")
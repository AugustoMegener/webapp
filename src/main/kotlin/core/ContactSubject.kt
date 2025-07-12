package com.mafiarosa.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ContactSubject {
    @SerialName("product") PRODUCT,
    @SerialName("order") ORDER,
    @SerialName("sponsor") SPONSOR,
    @SerialName("other") OTHER
}
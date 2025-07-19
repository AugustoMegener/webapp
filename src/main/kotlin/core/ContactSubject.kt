package com.mafiarosa.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ContactSubject {
    PRODUCT, ORDER, SPONSOR, OTHER
}
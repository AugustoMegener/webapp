package com.mafiarosa.core

import kotlinx.serialization.Serializable

@Serializable
enum class ContactSubject {
    PRODUCT, ORDER, SPONSOR, OTHER
}
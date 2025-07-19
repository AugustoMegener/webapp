package com.mafiarosa.core

import kotlinx.serialization.Serializable

@Serializable
enum class ContactStatus {
    UNREAD, READ, OPEN, SOLVED, ARCHIVED
}
package com.mafiarosa.dto

import com.mafiarosa.core.ContactSubject
import kotlinx.serialization.Serializable

@Serializable
data class ContactFormData(val name: String, val email: String, val subject: String, val message: String)
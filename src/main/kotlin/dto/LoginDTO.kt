package com.mafiarosa.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDTO(val loginInfo: String = "", val signingInfo: String = "")
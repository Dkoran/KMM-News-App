package com.dkoran.newsappkmm.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("name")
    val name: String? = null
)
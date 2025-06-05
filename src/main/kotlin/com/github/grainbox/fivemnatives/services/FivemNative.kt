package com.github.grainbox.fivemnatives.services

data class FivemNative(
    val name: String,
    val params: List<Pair<String, String>>,
    val returnType: String?,
    val doc: String
)
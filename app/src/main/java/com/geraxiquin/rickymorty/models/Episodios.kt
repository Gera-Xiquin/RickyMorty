package com.geraxiquin.rickymorty.models


data class Episodios(
    var id: Int=-1,
    var name: String? = "",
    var air_date: String? = "",
    var episode: String? = "",
    var characters: List<String>? = listOf(),
    var url: String? = "",
    var created: String? = ""
)



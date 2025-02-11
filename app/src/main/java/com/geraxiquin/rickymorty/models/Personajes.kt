package com.geraxiquin.rickymorty.models


data class Personajes(
    var id: Int=-1,
    var name: String? = "",
    var status: String? = "",
    var species: String? = "",
    var type: String? = "",
    var gender: String? = "",
    var origin: Extra = Extra(),
    var location: Extra = Extra(),
    var image: String? = "",
    var episode: List<String>? = listOf(),
    var url: String? = "",
    var created: String? = ""
)

data class Extra(
    var name: String? = "",
    var url: String? = ""
)



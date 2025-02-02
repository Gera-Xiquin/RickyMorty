package com.geraxiquin.rickymorty.models

data class Ubicacion(
    var id:Int=0,
    var name: String?="",
    var type:String?="",
    var dimension:String?="",
    var residents:List<String>?= listOf(),
    var url:String?="",
    var created:String?=""
)

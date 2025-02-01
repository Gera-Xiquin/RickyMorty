package com.geraxiquin.rickymorty.models

import com.google.gson.annotations.SerializedName

data class ApiRespuesta(
    @SerializedName("info") var info:Info= Info(),
    @SerializedName("results") var results:Any? = null
)
data class Info(
    var count:Int=-0,
    var pages:Int=-1,
    var next:String?="",
    var prev:String?=""
)
data class genericGet(
    var active: Boolean?
)

package com.geraxiquin.rickymorty.Controlador.Red


import com.geraxiquin.rickymorty.models.ApiRespuesta
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceKotlin {

    @GET
    suspend fun getApi(
        @Url url: String
    ): Response<ApiRespuesta>?

    @GET
    suspend fun getApiQuery(
        @Url url: String,
        @Query("page") page: Int
    ): Response<ApiRespuesta>?

}
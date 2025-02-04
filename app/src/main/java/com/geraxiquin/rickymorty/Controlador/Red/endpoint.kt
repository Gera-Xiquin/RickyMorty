package com.geraxiquin.rickymorty.Controlador.Red


import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.geraxiquin.rickymorty.models.ApiRespuesta
import com.geraxiquin.rickymorty.models.Ubicacion
import com.geraxiquin.rickymorty.models.genericGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class endpoint {
    val url_server = "https://rickandmortyapi.com/api/"
    val ENDPOINT_EPISODIOS = "episode"
    val ENDPOINT_PERSONAJES = "character"
    val ENDPOINT_UBICACION = "location/"

    lateinit var endpoint: String
    lateinit var params: Any
    lateinit var url: String
    lateinit var api: ApiServiceKotlin
    var contador = 0
    var error: Boolean = false

    suspend fun performApiCall(
        endpointP: String,
        paramsP: Any?,
        urlP: String?,
    ): Any {
        var retorno = Any()
        retorno = performApiCallSuspend<genericGet>(
            endpointP,
            paramsP,
            urlP
        )
        if (error) {
            Log.i("error contador", contador.toString())
            if ((contador < 4) && ((retorno.toString().contains("timeout")) || (retorno.toString()
                    .contains("timed out")) || (retorno.toString()
                    .contains("No addres")))
            ) {
                contador += 1
                return this.performApiCallSuspend<genericGet>(
                    endpointP,
                    paramsP,
                    urlP
                )
            } else {
                try {
                    // Toast.makeText(activityP,"error de red",Toast.LENGTH_SHORT).show()
                } catch (_: Exception) {
                }
                return retorno
            }
        } else {
            return retorno
        }

    }


    suspend inline fun <reified T> performApiCallSuspend(
        endpointP: String,
        paramsP: Any?,
        urlP: String?,
        contadorP: Int? = 0
    ): Any {
        endpoint = endpointP
        params = paramsP ?: Any()
        url = urlP ?: ""
        contador = contadorP ?: 0
        var retorno = Any()
        try {
            api = APIConecction().getData().create<ApiServiceKotlin>()
            Log.i("peticion endpoint $endpoint", params.toString())

            retorno = when (endpoint) {
                "episodio" -> {
                    withContext(Dispatchers.IO) {
                        val endpointResponse =
                            api.getApiQuery(ENDPOINT_EPISODIOS, params.toString().toInt())?.body()
                        if (endpointResponse?.info?.count != 0) {
                            endpointResponse ?: ApiRespuesta()
                        } else {
                            error(
                                "",
                                ENDPOINT_EPISODIOS,
                                params.toString()
                            )
                            endpointResponse
                        }
                    }
                }

                "personajes" -> {
                    withContext(Dispatchers.IO) {
                        val endpointResponse =
                            api.getApiQuery(ENDPOINT_PERSONAJES, params.toString().toInt())?.body()
                        if (endpointResponse?.info?.count != 0) {
                            endpointResponse ?: ApiRespuesta()
                        } else {
                            error(
                                "",
                                ENDPOINT_PERSONAJES,
                                params.toString()
                            )
                            endpointResponse
                        }
                    }
                }

                "ubicacion" -> {
                    withContext(Dispatchers.IO) {
                        val endpointResponse =
                            api.getApiUbicacion(ENDPOINT_UBICACION + params.toString().toInt())
                                ?.body()
                        if (endpointResponse?.id != 0) {
                            endpointResponse ?: Ubicacion()
                        } else {
                            error(
                                "",
                                ENDPOINT_UBICACION,
                                params.toString()
                            )
                            Ubicacion()
                        }
                    }
                }

                else -> throw IllegalArgumentException("Tipo endpoint no admitido: $endpoint")
            }
            Log.i("respuesta endpoint $endpoint", retorno.toString())
            error = false
            return retorno
        } catch (e: Exception) {
            error = true
            retorno = e.message ?: "error timeout"
            error(e.message ?: "", endpoint, params.toString())
            Log.e("respuesta endpoint $endpoint", e.message ?: "")
            return retorno
        }
    }

    fun error(message: String, endpoint: String, toString: String) {
        //Conexion a firebase
        Log.e("endpoint ${endpoint}", message + " " + toString)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
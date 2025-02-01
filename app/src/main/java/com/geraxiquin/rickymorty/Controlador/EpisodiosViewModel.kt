package com.geraxiquin.rickymorty.Controlador

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraxiquin.rickymorty.Controlador.Red.endpoint
import com.geraxiquin.rickymorty.models.ApiRespuesta
import com.geraxiquin.rickymorty.models.Episodios
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodiosViewModel(activity: Activity) : ViewModel() {
    private val _episodios = MutableStateFlow<List<Episodios>>(emptyList()) // Estado de la UI
    val episodios: StateFlow<List<Episodios>> = _episodios
    private val _isLoading = MutableStateFlow<Boolean>(false) // Estado de la UI
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 1

    init {
        obtenerEpisodios(activity)
    }

    fun obtenerEpisodios(activity: Activity) {
        if (_isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val respueta = (endpoint().performApiCall(
                    "episodio",
                    activity,
                    currentPage,
                    null
                )) as ApiRespuesta
                    if (respueta.info.pages >= currentPage) {
                        currentPage++
                        _episodios.value = Gson().fromJson(
                            Gson().toJson(respueta.results),
                            Array<Episodios>::class.java
                        ).toList()
                        Log.i("respuesta", _episodios.value.toString())
                        _isLoading.value = false
                    } else {
                        Toast.makeText(
                            activity,
                            "Has cargado toda la información",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        _isLoading.value = false
                    }


            } catch (e: Exception) {
                Log.e("EpisodiosViewModel", "Error al obtener episodios", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}

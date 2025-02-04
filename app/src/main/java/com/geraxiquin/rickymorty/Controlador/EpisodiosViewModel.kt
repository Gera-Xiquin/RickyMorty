package com.geraxiquin.rickymorty.Controlador


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraxiquin.rickymorty.Controlador.Red.endpoint
import com.geraxiquin.rickymorty.models.ApiRespuesta
import com.geraxiquin.rickymorty.models.Episodios
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodiosViewModel : ViewModel() {
    private val _episodios =
        MutableStateFlow<MutableList<Episodios>>(mutableListOf()) // Estado de la UI
    val episodios: StateFlow<MutableList<Episodios>> = _episodios
    private val _isLoading = MutableStateFlow<Boolean>(false) // Estado de la UI
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _todo = MutableStateFlow<Boolean>(false) // Estado de la UI
    val todo: StateFlow<Boolean> = _todo

    private var currentPage = 1

    init {
        obtenerEpisodios()
    }

    fun obtenerEpisodios() {
        if (_isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val respueta = (endpoint().performApiCall(
                    "episodio",
                    currentPage,
                    null
                )) as ApiRespuesta
                if (respueta.info.pages >= currentPage) {
                    _todo.value = false
                    currentPage++
                    _episodios.value.addAll(
                        Gson().fromJson(
                            Gson().toJson(respueta.results),
                            Array<Episodios>::class.java
                        ).toMutableList()
                    )
                    Log.i("respuesta", _episodios.value.size.toString())
                } else {
                    _todo.value = true
                }
            } catch (e: Exception) {
                Log.e("EpisodiosViewModel", "Error al obtener episodios", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}

package com.geraxiquin.rickymorty.Controlador

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraxiquin.rickymorty.Controlador.Red.endpoint
import com.geraxiquin.rickymorty.models.ApiRespuesta
import com.geraxiquin.rickymorty.models.Personajes
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(activity: Activity): ViewModel()  {
    private val _characters =
        MutableStateFlow<MutableList<Personajes>>(mutableListOf())
    val characters: StateFlow<MutableList<Personajes>> = _characters
    private var currentPage = 1
    private val _isLoading = MutableStateFlow<Boolean>(false) // Estado de la UI
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadCharacters(activity)
    }

    fun loadCharacters(activity: Activity) {
        if (_isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val respueta = (endpoint().performApiCall(
                    "personajes",
                    activity,
                    currentPage,
                    null
                )) as ApiRespuesta
                if (respueta.info.pages >= currentPage) {
                    currentPage++
                    _characters.value += Gson().fromJson(
                        Gson().toJson(respueta.results),
                        Array<Personajes>::class.java
                    ).toMutableList()
                    Log.i("respuesta", _characters.value.size.toString())
                } else {
                    Toast.makeText(
                        activity,
                        "Has cargado toda la información",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Log.e("EpisodiosViewModel", "Error al obtener personajes", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}

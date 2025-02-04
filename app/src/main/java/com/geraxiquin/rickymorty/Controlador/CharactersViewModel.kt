package com.geraxiquin.rickymorty.Controlador

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraxiquin.rickymorty.Controlador.Red.endpoint
import com.geraxiquin.rickymorty.models.ApiRespuesta
import com.geraxiquin.rickymorty.models.Personajes
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel()  {
    private val _characters =
        MutableStateFlow<MutableList<Personajes>>(mutableListOf())
    val characters: StateFlow<MutableList<Personajes>> = _characters
    private var currentPage = 1
    private val _isLoading = MutableStateFlow<Boolean>(false) // Estado de la UI
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _todo = MutableStateFlow<Boolean>(false) // Estado de la UI
    val todo: StateFlow<Boolean> = _todo

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        if (_isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val respueta = (endpoint().performApiCall(
                    "personajes",
                    currentPage,
                    null
                )) as ApiRespuesta
                if (respueta.info.pages >= currentPage) {
                    _todo.value = false
                    currentPage++
                    _characters.value += Gson().fromJson(
                        Gson().toJson(respueta.results),
                        Array<Personajes>::class.java
                    ).toMutableList()
                    Log.i("respuesta", _characters.value.size.toString())
                } else {
                    _todo.value = true
                }
            } catch (e: Exception) {
                Log.e("EpisodiosViewModel", "Error al obtener personajes", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}

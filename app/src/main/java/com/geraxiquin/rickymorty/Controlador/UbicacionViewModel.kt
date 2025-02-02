package com.geraxiquin.rickymorty.Controlador

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geraxiquin.rickymorty.Controlador.Red.endpoint
import com.geraxiquin.rickymorty.MainActivity
import com.geraxiquin.rickymorty.models.ApiRespuesta
import com.geraxiquin.rickymorty.models.Ubicacion
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UbicacionViewModel(activity: MainActivity,idUbicacion : Int): ViewModel() {
    private val _ubicacion =
        MutableStateFlow<Ubicacion>(Ubicacion()) // Estado de la UI
    val ubicacion: StateFlow<Ubicacion> = _ubicacion
    private val _isLoading = MutableStateFlow<Boolean>(false) // Estado de la UI
    val isLoading: StateFlow<Boolean> = _isLoading


    init {
        obtenerUbicacion(activity,idUbicacion)
    }
    fun obtenerUbicacion(activity: Activity,ubicaion:Int) {
        if (_isLoading.value) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val respueta = (endpoint().performApiCall(
                    "ubicacion",
                    activity,
                    ubicaion,
                    null
                )) as Ubicacion
                    _ubicacion.value = respueta
                    Log.i("respuesta", _ubicacion.value.toString())
            } catch (e: Exception) {
                Log.e("EpisodiosViewModel", "Error al obtener episodios", e)
            }finally {
                _isLoading.value = false
            }
        }
    }
}

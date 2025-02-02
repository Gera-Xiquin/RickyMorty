package com.geraxiquin.rickymorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geraxiquin.rickymorty.Controlador.CharactersViewModel
import com.geraxiquin.rickymorty.Controlador.EpisodiosViewModel
import com.geraxiquin.rickymorty.Controlador.UbicacionViewModel
import com.geraxiquin.rickymorty.ui.EpisodiosUi
import com.geraxiquin.rickymorty.ui.Home
import com.geraxiquin.rickymorty.ui.PersonajesUi
import com.geraxiquin.rickymorty.ui.UbicacionUi

class MainActivity : ComponentActivity() {
    var ubicacionSelected = "0"

    @RequiresApi(35)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vistas()
        }
    }

    @RequiresApi(35)
    @Preview
    @Composable
    fun Vistas() {
        val navController = rememberNavController()
        val episodiosUI = EpisodiosUi()
        val personajesUI = PersonajesUi()
        val ubicacionUi = UbicacionUi()


        NavHost(navController = navController, startDestination = "home") {
            composable("home") { Home().HomeScreen(navController) }
            composable("episodios") {
                episodiosUI.IrEpisodios(
                    navController,
                    EpisodiosViewModel(this@MainActivity), this@MainActivity
                )
            }
            composable("detailsEpisodio") { episodiosUI.DetalleEpisodio(navController) }
            composable("verUbicacion") {
                ubicacionUi.UbicacionScreen(
                    this@MainActivity,
                    UbicacionViewModel(this@MainActivity,ubicacionSelected.toInt()), navController
                )
            }
            composable("personajes") {
                personajesUI.CharactersScreen(
                    this@MainActivity,
                    CharactersViewModel(this@MainActivity), navController
                )
            }
        }
    }




}



package com.geraxiquin.rickymorty.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.geraxiquin.rickymorty.Controlador.UbicacionViewModel
import com.geraxiquin.rickymorty.MainActivity
import com.geraxiquin.rickymorty.R

class UbicacionUi {

    @Composable
    fun UbicacionScreen(
        activity: MainActivity,
        ubicacionViewModel: UbicacionViewModel,
        navController: NavHostController
    ) {
        val ubicacion = ubicacionViewModel.ubicacion.collectAsState().value
        val isLoading = ubicacionViewModel.isLoading.collectAsState()

        VistasCompartidas().Atras(navController, stringResource(id = R.string.ubicacion_nombre))
        if (isLoading.value) {
            VistasCompartidas().Carga()
        } else {
            var personajes =""
            ubicacion.residents?.map {personajes = personajes + it.split("/").last()+","}
            Column(
                modifier = VistasCompartidas()
                    .ModifierLazy()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "No.: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = ubicacion.id.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Nombre: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = ubicacion.name.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Tipo: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = ubicacion.type.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Personajes: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = personajes.substring(0,personajes.length-1),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = ubicacion.residents?.toList().toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "url: ", fontSize = 14.sp, fontWeight = FontWeight.Normal)
                    Text(
                        text = ubicacion.url.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

}

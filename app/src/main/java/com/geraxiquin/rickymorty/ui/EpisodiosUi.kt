package com.geraxiquin.rickymorty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.geraxiquin.rickymorty.Controlador.EpisodiosViewModel
import com.geraxiquin.rickymorty.MainActivity
import com.geraxiquin.rickymorty.R
import com.geraxiquin.rickymorty.models.Episodios


class EpisodiosUi {

    var episodioClase = Episodios()

    @Composable
    fun IrEpisodios(
        navController: NavHostController,
        viewModel: EpisodiosViewModel, activity: MainActivity
    ) {
        val episodios = viewModel.episodios.collectAsState()
        val isLoading = viewModel.isLoading.collectAsState() // Obtenemos el estado de carga

        val listState = rememberLazyListState()
        VistasCompartidas().Atras(navController, stringResource(id = R.string.episodios_nombre))

        // Detectar cuando faltan 5 elementos para el final
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
                .collect { lastVisibleItemIndex ->
                    val totalItems = episodios.value.size
                    if (totalItems - lastVisibleItemIndex <= 5) {
                        viewModel.obtenerEpisodios(activity)
                    }
                }
        }
        if (isLoading.value) {
          VistasCompartidas().Carga()
        } else {
            LazyColumn(
                modifier = VistasCompartidas().ModifierLazy(),
                state = listState
            ) {
                items(episodios.value) { episodio ->
                    AppItem(
                        episodio = episodio, navController
                    )
                }
            }
        }
    }

    @Composable
    fun AppItem(episodio: Episodios, navController: NavHostController) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    episodioClase = episodio
                    navController.navigate("detailsEpisodio")
                },
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.gray))
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
                    text = episodio.id.toString(),
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
                    text = "Nombre del Episodio: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = episodio.name.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Fecha de Emisión: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = episodio.air_date.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


    @Composable
    fun DetalleEpisodio(navController: NavHostController) {
        VistasCompartidas().Atras(navController, episodioClase.name.toString())
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
                    text = episodioClase.id.toString(),
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
                    text = "Nombre del Episodio: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = episodioClase.name.toString(),
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
                    text = "Código Episodio: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = episodioClase.episode.toString(),
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
                    text = episodioClase.characters?.toList().toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Fecha de Emisión: ", fontSize = 14.sp, fontWeight = FontWeight.Normal)
                Text(
                    text = episodioClase.air_date.toString(),
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
                    text = episodioClase.url.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


package com.geraxiquin.rickymorty.ui

import android.app.Activity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.geraxiquin.rickymorty.Controlador.CharactersViewModel
import com.geraxiquin.rickymorty.MainActivity
import com.geraxiquin.rickymorty.R
import com.geraxiquin.rickymorty.models.Personajes

class PersonajesUi {
    @RequiresApi(35)
    @Composable
    fun CharactersScreen(
        activity: MainActivity,
        viewModel: CharactersViewModel,
        navController: NavHostController
    ) {
        val characters = viewModel.characters.collectAsState()
        val isLoading = viewModel.isLoading.collectAsState() // Obtenemos el estado de carga

        val listState = rememberLazyListState()
        VistasCompartidas().Atras(navController, stringResource(id = R.string.personajes_nombre))

        // Detectar cuando faltan 5 elementos para el final
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
                .collect { lastVisibleItemIndex ->
                    val totalItems = characters.value.size
                    if (totalItems - lastVisibleItemIndex <= 5) {
                        viewModel.loadCharacters(activity)
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
                items(characters.value) { characters ->
                    AppItem(personaje = characters, navController,activity)
                }
            }
        }
    }

    @RequiresApi(35)
    @Composable
    fun AppItem(personaje: Personajes, navController: NavHostController,activity: MainActivity) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.gray))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = personaje.image,
                    contentDescription = "Imagen desde URL",
                    modifier = Modifier
                        .padding(4.dp)
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp)), // Esquinas redondeadas
                    contentScale = ContentScale.Crop
                )
            }
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
                    text = personaje.id.toString(),
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
                    text = "Nombre : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = personaje.name.toString(),
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
                    text = "Estado : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = personaje.status.toString(),
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
                    text = "Especie : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = personaje.species.toString(),
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
                    text = "Genero : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = personaje.gender.toString(),
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
                    text = "Origen : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = personaje.origin.name.toString(),
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
                    text = "Ubicación : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = personaje.location.name.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 32.dp)
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    activity.ubicacionSelected =  personaje.location.url?.split("/")?.last()?:"0"
                    navController.navigate("verUbicacion") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = VistasCompartidas().ModifierButton()
                ) {
                    Text("Ver Ubicación")
                }
            }
        }
    }


}
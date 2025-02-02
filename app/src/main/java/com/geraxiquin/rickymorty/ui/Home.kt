package com.geraxiquin.rickymorty.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.geraxiquin.rickymorty.R

class Home {
    @Composable
    fun HomeScreen(navController: NavHostController) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = colorResource(id = R.color.teal_700)),
                contentAlignment = Alignment.Center,
            )
            {
                Column(  modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.black),
                )

            }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.icon),
                        contentDescription = "imagen",
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(5.dp)) // Esquinas redondeadas
                            .border(1.dp, Color.Black, RoundedCornerShape(5.dp)), // Borde negro
                        contentScale = ContentScale.FillWidth // Ajusta la imagen sin distorsionarla
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.saludo_home),
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.pregunta_home),
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.black),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigate("episodios") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = VistasCompartidas().ModifierButton()
                    ) {
                        Text(stringResource(id = R.string.episodios_nombre))
                    }
                    Button(
                        onClick = { navController.navigate("personajes") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = VistasCompartidas().ModifierButton()
                    ) {
                        Text(stringResource(id = R.string.personajes_nombre))
                    }
                }
                Column(  modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Rick y Morty by Gerardo Xiquin",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.black),
                    )

                    Text(
                        text = "Código Fuente en https://github.com/Gera-Xiquin/RickyMorty ",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.black),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }


        }
    }
}
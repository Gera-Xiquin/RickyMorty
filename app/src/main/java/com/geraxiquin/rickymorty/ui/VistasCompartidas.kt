package com.geraxiquin.rickymorty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.geraxiquin.rickymorty.R

class VistasCompartidas {

    @Composable
    fun Atras(navController: NavHostController,texto:String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.teal_0)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_0))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Botón imagen",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = texto,
                color = Color.White,
                fontSize = 20.sp,
            )
        }
    }

    @Composable
    fun ModifierLazy(): Modifier {
        return Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(colorResource(id = R.color.teal_700))
            .padding(PaddingValues(start = 16.dp, end = 16.dp, top = 55.dp, bottom = 16.dp))
    }

    @Composable
    fun Carga() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ModifierButton(): ButtonColors {
       return ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_0),      // Cambia el color de fondo
            contentColor = Color.White       // Cambia el color del texto
        )
    }
}

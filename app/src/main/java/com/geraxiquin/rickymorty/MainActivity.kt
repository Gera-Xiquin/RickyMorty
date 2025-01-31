package com.geraxiquin.rickymorty

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geraxiquin.rickymorty.ui.theme.RickYMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickYMortyTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = R.color.teal_200))
                ) { innerPadding ->
                    BotonesEnColumna(innerPadding)
                }
            }
        }
    }


    @Composable
    fun BotonesEnColumna(innerPadding: PaddingValues? = null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .background(color = colorResource(id = R.color.white)),
            contentAlignment = Alignment.Center, // Centra el contenido en la pantalla
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageView(
                    this@MainActivity,
                )
                Text(
                    text = "Hola!",
                    fontSize = 18.sp,

                    )
                Text(
                    text = "Elige que quieres ver",
                    fontSize = 14.sp,

                    )
                Button(
                    onClick = { IrEpisodios() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Episodios")
                }
                Button(
                    onClick = { IrPersonajes() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Personajes")
                }
            }
        }
    }

    fun IrEpisodios() {
        Toast.makeText(this, "episodios", Toast.LENGTH_SHORT).show()
    }

    fun IrPersonajes() {
        Toast.makeText(this, "personajes", Toast.LENGTH_SHORT).show()
    }

    @Preview
    @Composable
    fun ver() {
        BotonesEnColumna()
    }
}



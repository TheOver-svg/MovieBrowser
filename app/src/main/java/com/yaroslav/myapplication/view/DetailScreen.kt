package com.yaroslav.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieId: Int,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Деталі фільму $movieId", color = Color.White) }, // TODO замінити на назву
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBack)
            )
        },
        containerColor = DarkBack
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            //TODO Інформація про фільм
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        movieId = 550,
        onBackClick = {}
    )
}
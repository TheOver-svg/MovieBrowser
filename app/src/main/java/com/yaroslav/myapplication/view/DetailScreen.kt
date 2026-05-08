package com.yaroslav.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.yaroslav.myapplication.data.MovieDetails
import com.yaroslav.myapplication.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: DetailsViewModel = viewModel()
) {
    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    DetailsContent(
        isLoading = viewModel.isLoading,
        details = viewModel.movieDetails,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    isLoading: Boolean,
    details: MovieDetails?,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(details?.title ?: "Завантаження...", color = Color.White) },
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
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MovieRed
                )
            } else if (details != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = details.posterUrl,
                        contentDescription = details.title,
                        modifier = Modifier.fillMaxWidth().height(400.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = details.title,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = details.rating, color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = "Рік: ${details.releaseDate}", color = Color.LightGray)
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Опис", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = details.overview, color = Color.LightGray, fontSize = 16.sp, lineHeight = 24.sp)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    DetailsContent(
        isLoading = false,
        details = MovieDetails(
            title = "TEST NAME",
            overview = "test",
            posterUrl = "",
            releaseDate = "03-10-2008",
            rating = "10.0"
        ),
        onBackClick = {}
    )
}
package com.example.comics.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.comics.ui.home.util.ComicItem
import com.example.comics.ui.home.util.HomeAction
import com.example.comics.ui.home.util.HomeState
import com.example.comics.ui.home.util.ShimmerComicItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(viewModel: HomeViewModel = koinViewModel()) {
    val state = viewModel.comics.collectAsState().value
    HomeScreen(state, viewModel::onEvent)

}

@Composable
private fun HomeScreen(state: HomeState, onEvent: (HomeAction) -> Unit){
    when(state){
        is HomeState.Error -> {
            //Acionar o evento em caso de erro.
        }
        is HomeState.Loading -> {
            LazyColumn {
                items(5) {
                    ShimmerComicItem()
                }
            }
        }
        is HomeState.Success -> {
            LazyColumn {
                items(state.data) {
                    ComicItem(
                        comic = it,
                        onClick = { title ->
                            //O ideal seria pegar o ID do componente, como não vou fazer o detail
                            //não vou precisar dele agora.
                            onEvent(HomeAction.Navigate(title))
                        }
                    )
                }
            }
        }
    }
}


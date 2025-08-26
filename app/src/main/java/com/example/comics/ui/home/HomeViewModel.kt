package com.example.comics.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.ui.home.util.HomeAction
import com.example.comics.ui.home.util.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(val comicRepository: ComicRepository) : ViewModel() {

    private val _comics = MutableStateFlow<HomeState>(HomeState.Loading)
    val comics: StateFlow<HomeState> = _comics.asStateFlow()

    init {
        getComics()
    }

    private fun getComics() {
        viewModelScope.launch {
            _comics.value = HomeState.Loading

            comicRepository.getComics()
                .onSuccess { list ->
                    _comics.value = HomeState.Success(list)
                }
                .onFailure { error ->
                    _comics.value = HomeState.Error(error)
                }
        }
    }

    fun onEvent(action: HomeAction){
        //Captura os eventos enviados pela UI e administra eles.
        //eu pretendia implementar a tela de detalhes para fazer bom uso do navHost e dessa função
        //Mas pelo tempo, vou deixar assim e deixar esse espaço aqui simbólico.
    }

}
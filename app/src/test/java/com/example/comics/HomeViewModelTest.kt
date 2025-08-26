package com.example.comics

import com.example.comics.domain.model.Comic
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.ui.home.util.HomeState
import com.example.comics.ui.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var comicRepository: ComicRepository
    private lateinit var viewModel: HomeViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        comicRepository = mockk<ComicRepository>()
        viewModel = HomeViewModel(comicRepository)

        //Necessário por causa da chamada realizar no init da viewModel.
        coEvery { comicRepository.getComics() } returns Result.success(
            listOf(makeComic())
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getComics succeeds should emit Success`() = runTest {
        val fakeComics = listOf(
            makeComic()
        )
        coEvery { comicRepository.getComics() } returns Result.success(fakeComics)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.comics.value
        assert(state is HomeState.Success)
        assertEquals(fakeComics, (state as HomeState.Success).data)
    }

    @Test
    fun `when getComics fails should emit Error`() = runTest {
        val error = Throwable("Failed to load comics")
        coEvery { comicRepository.getComics() } returns Result.failure(error)

        //Necessário por causa da chamada realizar no init da viewModel.
        val viewModel = HomeViewModel(comicRepository)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.comics.value
        assert(state is HomeState.Error)
        assertEquals(error, (state as HomeState.Error).throwable)
    }


    private fun makeComic(
        title: String = "Spider-Man",
        description: String = "Hero",
        thumbnailUrl: String = "http://image1.jpg"
    ) = Comic(title, description, thumbnailUrl)
}
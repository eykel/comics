package com.example.comics

import com.example.comics.data.mappers.toComic
import com.example.comics.data.mappers.toModel
import com.example.comics.data.model.ResultResponse
import com.example.comics.data.model.ThumbnailResponse
import com.example.comics.data.util.toUrl
import org.junit.Test
import org.junit.Assert.*

class MapperTest {

    @Test
    fun `toComic maps ResultResponse to Comic correctly`() {
        val response = makeResultResponse()

        val comic = response.toComic()

        assertEquals(response.title, comic.title)
        assertEquals(response.description, comic.description)
        assertEquals(response.thumbnail.toUrl(), comic.thumbnailUrl)

    }

    @Test
    fun `toComic handles null fields`() {
        val response = ResultResponse(
            title = null,
            description = null,
            thumbnail = null
        )

        val comic = response.toComic()

        assertEquals("", comic.title)
        assertEquals("", comic.description)
        assertEquals("", comic.thumbnailUrl)
    }

    @Test
    fun `toModel maps list of ResultResponse to list of Comic`() {
        val list = listOf(
            makeResultResponse(),
            makeResultResponse(
                title = "Iron Man",
                description = "Tech hero",
                thumbnailResponse = ThumbnailResponse(path = "http://image2", extension = "png")
            )
        )

        val comics = list.toModel()

        assertEquals(2, comics.size)
        assertEquals(list[0].title, comics[0].title)
        assertEquals(list[0].thumbnail.toUrl(), comics[0].thumbnailUrl)
        assertEquals(list[1].title, comics[1].title)
        assertEquals(list[1].thumbnail.toUrl(), comics[1].thumbnailUrl)
    }

    private fun makeResultResponse(
        title: String = "Spider-Man",
        description: String = "Hero",
        thumbnailResponse: ThumbnailResponse = ThumbnailResponse("http://image1", "jpg")
    ) = ResultResponse(title, description, thumbnailResponse)
}
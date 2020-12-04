package com.project.segunfrancis.coolmovies.data.remote

import com.project.segunfrancis.coolmovies.MainCoroutineRule
import com.project.segunfrancis.coolmovies.TestSetup.responseBody
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by SegunFrancis
 */

class RemoteTest {

    private val server: MockWebServer = MockWebServer()
    private lateinit var testApi: MovieApi

    @get:Rule
    val testRule = MainCoroutineRule()

    @Before
    fun init() {
        server.start(8080)
        testApi = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Test
    fun `test response is properly parsed`() = runBlocking(testRule.testDispatcher) {
        server.apply {
            enqueue(MockResponse().setBody(responseBody))
        }
        val response = testApi.getTopRatedMovies("", 1)
        assertNotNull(response)
    }

    @Test
    fun `test response has list of movies`() = runBlocking(testRule.testDispatcher)  {
        server.apply {
            enqueue(MockResponse().setBody(responseBody))
        }
        val response = testApi.getTopRatedMovies("", 1)
        assert(response.results.isNotEmpty())
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
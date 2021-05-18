package com.example.myassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myassignment.di.CoinModule.viewModelModule
import com.example.myassignment.utils.MockResponse
import com.example.myassignment.viewModel.HomeListActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class HomeActivityViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val mockWebServer: MockWebServer by lazy { MockWebServer() }


    private val homeListActivityViewModel = HomeListActivityViewModel()
    @ExperimentalCoroutinesApi
    @Test
    fun fetchToDoListFromServer() {


        startKoin { modules(viewModelModule) }
        Dispatchers.setMain(Dispatchers.IO)
        mockWebServer.enqueue(
            MockResponse.createMockResponse(
                "aqi_success",
                HttpURLConnection.HTTP_OK
            )
        )
        homeListActivityViewModel.connectWebSocketAndFetchAQI()

        //Todo proper solution is to be implemented
        Thread.sleep(5000)

        Assert.assertTrue(homeListActivityViewModel.aqiResponeDataForUI.get()!=null)
        Dispatchers.resetMain()
    }


    @Before
    @Throws(Exception::class)
    fun setUp() {
        startMockWebserver()
    }

    /**
     * Method which starts the mockwebserver
     */
    private fun startMockWebserver() {
        mockWebServer.start(1234)
    }

    /**
     * Method which stops the mock webserver
     */
    @After
    fun stopMockWebserver() {

        mockWebServer.shutdown()
    }
}
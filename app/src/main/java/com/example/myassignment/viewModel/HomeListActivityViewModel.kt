package com.example.myassignment.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassignment.BR
import com.example.myassignment.R
import com.example.myassignment.dataModel.AqiResponseData
import com.example.myassignment.dataModel.AqiResponseDataItem
import com.example.myassignment.interfaces.OnToDoItemClickListner
import com.google.gson.Gson
import kotlinx.coroutines.*
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import java.net.URISyntaxException

class HomeListActivityViewModel() : ViewModel() {


    var toDoItemClickListner = MutableLiveData<Void>()

    var aqiResponeDataForUI = ObservableField<AqiResponseData>()


    val toDoItemBinding = ItemBinding.of<AqiResponseDataItem> { itemBinding, _, _ ->
        itemBinding.set(BR.itemViewModel, R.layout.recycle_view_item)

        itemBinding.bindExtra(BR.itemClickListener, object : OnToDoItemClickListner {
            override fun termsAndConditionsOnItemClick(item: AqiResponseDataItem) {
                toDoItemClickListner.postValue(null)
            }

        })
    }

    private var mWebSocketClient: WebSocketClient? = null
     fun connectWebSocketAndFetchAQI() {
        val uri: URI
        uri = try {
            URI("ws://city-ws.herokuapp.com/")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            return
        }
        mWebSocketClient = object : WebSocketClient(uri) {
            override fun onOpen(serverHandshake: ServerHandshake) {
                Log.i("Websocket", "Opened")
            //    mWebSocketClient!!.send()
            }

            override fun onMessage(s: String) {
                // parse the response
                Log.d("data",s)
                aqiResponeDataForUI.set(Gson().fromJson<AqiResponseData>(s,AqiResponseData::class.java))
            }

            override fun onClose(i: Int, s: String, b: Boolean) {
                Log.i("Websocket", "Closed $s")
            }

            override fun onError(e: Exception) {
                Log.i("Websocket", "Error " + e.message)
            }
        }
        (mWebSocketClient as WebSocketClient).connect()
    }





    @ExperimentalCoroutinesApi
    fun onReloadClick() {

        connectWebSocketAndFetchAQI()
    }


}
package com.example.myassignment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myassignment.R
import com.example.myassignment.databinding.ActivityMainBinding
import com.example.myassignment.viewModel.HomeListActivityViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity() {


    val viewModel: HomeListActivityViewModel by inject { parametersOf(this) }


    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.viewModel = viewModel
        setSupportActionBar(toolbar)
        observeListners()
        checkAndLoadToDoList()
    }

    @ExperimentalCoroutinesApi
    private fun checkAndLoadToDoList() {
        if (viewModel.aqiResponeDataForUI.get() == null || viewModel.aqiResponeDataForUI.get().isNullOrEmpty() )
            viewModel.connectWebSocketAndFetchAQI()

    }

    private fun observeListners() {
        viewModel.toDoItemClickListner.observe(this, Observer {
            Toast.makeText(this, "TODO Add Graph", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }




}


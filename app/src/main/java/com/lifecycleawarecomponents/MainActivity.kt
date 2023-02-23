package com.lifecycleawarecomponents

import ViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    var count:Int=0
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(Observer())
        Log.d("Main","Activity - OnCreate")

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.listFruits.observe(this, androidx.lifecycle.Observer {
            Log.d("Main",it.toString())
        })



    }

    override fun onStart() {
        super.onStart()
        Log.d("Main","Activity - OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Main","Activity - OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Main","Activity - OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Main","Activity - OnStop")
    }
}
package com.lifecycleawarecomponents

import counterViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.lifecycleawarecomponents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: counterViewModel
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(Observer())
        Log.d("Main","Activity - OnCreate")

        viewModel = ViewModelProvider(this,ViewModelFactory(10)).get(counterViewModel::class.java)

        viewModel.listFruits.observe(this, androidx.lifecycle.Observer {
            Log.d("Main",it.toString())
        })


        setText()

        binding.btnIncrease.setOnClickListener {
            increase()
        }

    }

    fun setText(){
        binding.counter.text=viewModel.count.toString()
    }
    private fun increase() {
        viewModel.icrease()
        setText()
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
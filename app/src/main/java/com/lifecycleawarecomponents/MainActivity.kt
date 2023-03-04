package com.lifecycleawarecomponents

import android.content.Intent
import counterViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.lifecycleawarecomponents.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: counterViewModel
    private lateinit var binding: ActivityMainBinding

    val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(Observer())
        Log.d("Main", "Activity - OnCreate")

        viewModel = ViewModelProvider(this, ViewModelFactory(10)).get(counterViewModel::class.java)

        viewModel.listFruits.observe(this, androidx.lifecycle.Observer {
            Log.d("Main", it.toString())
        })

        //Few changes

        setText()

        binding.btnIncrease.setOnClickListener {
            increase()
        }

        CoroutineScope(Dispatchers.Main).launch {
            getUserNames().forEach {
                Log.d("FLOWS", it)
            }
        }

        produce()
        consume()

        GlobalScope.launch {
            val data:Flow<Int> = flowProducer()
            data.collect{
                Log.d("FLOWS",it.toString())
            }
        }

        binding.btnFlow.setOnClickListener {
            val intent = Intent(this@MainActivity,FlowActivity::class.java)
            startActivity(intent)
        }
    }

    private fun consume() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("FLOWS",channel.receive().toString())
            Log.d("FLOWS",channel.receive().toString())
        }
    }

    private fun produce() {
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }

    }

    fun flowProducer() = flow<Int>{
        val list = listOf(1,2,3,4,5,6,7,8,9)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }

    private suspend fun getUserNames(): List<String> {
        val list = mutableListOf<String>()
        list.add(getUser(1))
        list.add(getUser(2))
        list.add(getUser(3))
        return list
    }

    private suspend fun getUser(id: Int): String {
        delay(1000)
        return "User$id"
    }

    fun setText() {
        binding.counter.text = viewModel.count.toString()
    }

    private fun increase() {
        viewModel.icrease()
        setText()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Main", "Activity - OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Main", "Activity - OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Main", "Activity - OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Main", "Activity - OnStop")
    }
}
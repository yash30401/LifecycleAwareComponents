package com.lifecycleawarecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lifecycleawarecomponents.databinding.ActivityFlowBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Flow

class FlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        flowConsumer()

        consumePersonflow()

    }

    private fun flowConsumer() {
        GlobalScope.launch {
            val listNumbers = flowProducer()
            listNumbers.collect {
                GlobalScope.launch(Dispatchers.Main) {
                    binding.textFlow.text = it.toString()
                }
            }
        }
    }

    private fun flowProducer() = flow<Int> {
        val listOfnumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8)

        listOfnumbers.forEach {
            delay(1000)
            emit(it)
        }
    }

    private fun producePersonFlow() = flow<person> {

        val person1 = person("Yash", 21)
        val person2 = person("Mohit", 19)
        val person3 = person("Sandeep", 27)
        val person4 = person("Akshat", 20)

        val listOfPersons = listOf<person>(person1, person2, person3, person4)

        listOfPersons.forEach {
            delay(1000)
            emit(it)
            throw Exception("ERROR In Emitter")
        }



    }.catch {
        Log.d("Error","Error In Producer")
        emit(person("null",-1))
    }

    private fun consumePersonflow() {
        GlobalScope.launch {
            Log.d("THREAD", "consumePersonflow: ${Thread.currentThread().name}")
            try {
                val personList = producePersonFlow()
                personList
                    .map {
                        person(
                            it.personName.toString().toUpperCase(),
                            it.personAge
                        ) //This map and filter operator executes on IO Thread
                    }
                    .filter {
                        it.personAge!! > 20
                    }
                    .flowOn(Dispatchers.IO)

                    .collect {
                        GlobalScope.launch(Dispatchers.Main) {
                            binding.personName.text = it.personName.toString()
                            binding.personAge.text = it.personAge.toString()
                        }
                    }
            } catch (e: Exception) {
                Toast.makeText(this@FlowActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}
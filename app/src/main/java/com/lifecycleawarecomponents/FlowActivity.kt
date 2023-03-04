package com.lifecycleawarecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lifecycleawarecomponents.databinding.ActivityFlowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class FlowActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFlowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        flowProducer()
        flowConsumer()


    }

    private fun flowConsumer() {
        GlobalScope.launch {
            val listNumbers = flowProducer()
            listNumbers.collect {
                GlobalScope.launch(Dispatchers.Main) {
                binding.textFlow.text=it.toString()
                }
            }
        }
    }

    private fun flowProducer()=flow<Int> {
        val listOfnumbers = listOf(1,2,3,4,5,6,7,8)

        listOfnumbers.forEach {
            delay(1000)
            emit(it)
        }
    }
}
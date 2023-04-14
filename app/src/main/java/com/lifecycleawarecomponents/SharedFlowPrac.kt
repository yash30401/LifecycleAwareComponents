package com.lifecycleawarecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lifecycleawarecomponents.databinding.ActivitySharedFlowPracBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SharedFlowPrac : AppCompatActivity() {

    private lateinit var binding: ActivitySharedFlowPracBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedFlowPracBinding.inflate(layoutInflater)
        setContentView(binding.root)


        shareFlowConsumer()
    }

    private fun shareFlowConsumer() {
        GlobalScope.launch(Dispatchers.Main) {
            val numberList = shareFlowProducer()
            numberList.collect {
                Log.d("SHARE1", it.toString())
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val numberList2 = shareFlowProducer()
            delay(2500)
            numberList2.collect {

                Log.d("SHARE2", it.toString())
            }
        }
    }

    private fun shareFlowProducer(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>()
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7)

        GlobalScope.launch {
            list.forEach {
                delay(1000)
                mutableSharedFlow.emit(it)

            }
        }
        return mutableSharedFlow
    }
}
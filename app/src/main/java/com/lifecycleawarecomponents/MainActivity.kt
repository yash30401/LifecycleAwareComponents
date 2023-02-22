package com.lifecycleawarecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(Observer())
        Log.d("Main","Activity - OnCreate")
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
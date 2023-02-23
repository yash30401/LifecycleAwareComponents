package com.lifecycleawarecomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import counterViewModel

class ViewModelFactory(val counter:Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return counterViewModel(counter) as T
    }
}
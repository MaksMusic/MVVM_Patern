package com.music.mvvm_patern.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.music.mvvm_patern.data.repository.MainRepository
import com.music.mvvm_patern.viewModel.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory constructor(
    private val repository: MainRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("view model not found")
        }
    }
}
package com.music.mvvm_patern.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.mvvm_patern.data.model.Users
import com.music.mvvm_patern.data.repository.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val usersList = MutableLiveData<ArrayList<Users>>()
    var job: Job? = null

    private fun exceptionHandler() = CoroutineExceptionHandler { _, thowable ->
        onError("Exception handler ${thowable.localizedMessage})")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    fun getAllUsers(){
        job = CoroutineScope(Dispatchers.IO+exceptionHandler()).launch {
            loading.postValue(true)
            val call = mainRepository.getAllUsers()
            withContext(Dispatchers.Main){
                if (call.isSuccessful){ //если запрос успешен
                    usersList.postValue(call.body())
                    loading.value = false
                }else{
                    onError("ERROR: ${call.message()}")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
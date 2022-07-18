package com.music.mvvm_patern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.music.mvvm_patern.Adapters.AdapterUser
import com.music.mvvm_patern.data.RetrofitServices
import com.music.mvvm_patern.data.factory.MainViewModelFactory
import com.music.mvvm_patern.data.model.Users
import com.music.mvvm_patern.data.repository.MainRepository
import com.music.mvvm_patern.databinding.ActivityMainBinding
import com.music.mvvm_patern.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var mainRepository: MainRepository
    lateinit var retrofitServices: RetrofitServices

    private var listUsers = arrayListOf<Users>()
    private var adapter = AdapterUser(listUsers)

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter

        init()

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        getLoadData(viewModel)
    }

    private fun init() {
        retrofitServices = RetrofitServices.getInstance()
        mainRepository = MainRepository(retrofitServices)
    }

    private fun getLoadData(viewModel: MainViewModel) {
        viewModel.usersList.observe(this) { data ->
            data.forEach { it ->
                adapter.addUserOne(it)
            }
        }
        viewModel.getAllUsers()

    }
}
package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.NewsAdapter
import com.example.myapplication.base.BaseCoroutineActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.intent.MainIntent
import com.example.myapplication.repo.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseCoroutineActivity<HomeContract.State, HomeContract.ViewEvent, HomeContract.Intent>() {
    val mainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        observeData()

            lifecycleScope.launch {
                //mainViewModel.mainIntent.send(MainIntent.GetNews)
            }

    }

    override fun render(state: HomeContract.State){

    }

    override fun handleViewEvent(event: HomeContract.ViewEvent){

    }


    private fun observeData() {
//        lifecycleScope.launch {
//            mainViewModel.state.collect {
//                when (it) {
//                    is ApiState.Loading -> {
//                        // load some progress bar
//                    }
//
//                    is ApiState.Success -> {
//                        val recyclerView = binding.dummyTest
//                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
//
//                        val adapter = NewsAdapter(it.data.articles)
//                        recyclerView.adapter= adapter
//
//                    }
//
//                    is ApiState.Error -> {
//                        // show exception
//                    }
//                }
//            }
//        }
    }
}
package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.intent.MainIntent
import com.example.myapplication.repo.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.w3c.dom.Text

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val mainViewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory)[MainViewModel::class.java]
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        observeData()

        binding.dummyTest.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.mainIntent.send(MainIntent.GetNews)
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            mainViewModel.state.collect{
                when(it){
                    is ApiState.Loading ->{

                    }
                    is ApiState.Success ->{

                    }
                    is ApiState.Error ->{

                    }
                }
            }
        }
    }


}
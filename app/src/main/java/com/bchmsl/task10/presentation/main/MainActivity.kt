package com.bchmsl.task10.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bchmsl.task10.R
import com.bchmsl.task10.common.Resource
import com.bchmsl.task10.common.model.MessageResponseDto
import com.bchmsl.task10.databinding.ActivityMainBinding
import com.bchmsl.task10.presentation.adapter.MessagesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val messagesAdapter by lazy { MessagesAdapter() }
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        start()
    }

    private fun start() {
        setupRecycler()
        observe()
    }

    private fun setupRecycler() {
        binding.rvMessages.apply {
            adapter = messagesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observe() {
        lifecycleScope.launch{
            viewModel.fetchMessages()
            viewModel.uiState.collect{
                when(it){
                    is Resource.Success -> handleSuccess(it.data)
                    is Resource.Error -> handleError(it.error)
                    else -> {}
                }
            }
        }
    }
    private fun handleSuccess(data: List<MessageResponseDto>){
        messagesAdapter.submitList(data)
    }

    private fun handleError(e: Throwable){
        Snackbar.make(binding.root, "${e.message}", Snackbar.LENGTH_LONG).show()
    }
}
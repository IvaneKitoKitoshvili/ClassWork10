package com.example.classwork10.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classwork10.R
import com.example.classwork10.databinding.FragmentChatBinding
import com.example.classwork10.databinding.SinglechatItemBinding
import com.example.classwork10.ui.adapter.ChattingAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatFragment : Fragment()

    private lateinit var binding: FragmentChatBinding
    private lateinit var chatAdapter: ChattingAdapter
    private val viewmodel: ChattingViewModel by viewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentChatBinding.inflate(this, R.layout.singlechat_item, false)

        setupAdapter()
        setupObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewmodel.getChatInfo()
        }
    }

    private fun setupObservers() {
        launchStarted {
            viewmodel.state.collect {
                binding.swipeRefreshLayout.isRefreshing = it.loading
                controlRefresh()
                when {
                    it.data.isNotEmpty() -> chatAdapter.submitList(it.data)
                    it.errorMsg.isNotEmpty() -> {
                        Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun controlRefresh() {
        if (binding.swipeRefreshLayout.isRefreshing) {
            binding.rvChatMain.visibility = View.GONE
        } else {
            binding.rvChatMain.visibility = View.VISIBLE
        }
    }

    private fun setupAdapter() {
        chatAdapter = ChattingAdapter()
        binding.rvChatMain.adapter = chatAdapter
        binding.rvChatMain.layoutManager = LinearLayoutManager(this)
    }
}
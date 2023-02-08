package com.example.testceiba.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testceiba.databinding.ActivityMainBinding
import com.example.testceiba.view.main.adapter.UsersAdapter
import com.example.testceiba.view.publications.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val adapter: UsersAdapter = UsersAdapter(onItemClick = ::onPublicationClick)

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.adapter = adapter

        handleState()
        filterUser()
    }

    private fun filterUser(){
        binding.svFilterUsers.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { return true }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) viewModel.filterUsers(newText)
                return true
            }
        })
    }

    private fun handleState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.onLoadUser(state.users)
                }
            }
        }
    }

    private fun onPublicationClick(userId: Int, userName: String, userMail: String, userPhone:String) {
        val intent = Intent(this, PublicationsActivity::class.java)
        intent.putExtra(USER_ID_KEY, userId)
        intent.putExtra(USER_NAME, userName)
        intent.putExtra(USER_MAIL, userMail)
        intent.putExtra(USER_PHONE, userPhone)
        startActivity(intent)
    }
}
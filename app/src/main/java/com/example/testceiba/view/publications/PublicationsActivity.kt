package com.example.testceiba.view.publications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testceiba.databinding.ActivityPublicationsBinding
import com.example.testceiba.view.publications.adapter.PublicationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PublicationsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: PublicationsActivityViewModel.PublicationsActivityViewModelFactory

    private val adapter: PublicationAdapter = PublicationAdapter()

    private lateinit var binding: ActivityPublicationsBinding

    private val viewModel: PublicationsActivityViewModel by lazy {
        factory.create(
            userId = intent.getIntExtra(USER_ID_KEY, 0)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPublication.adapter = adapter
        bindHeader()

        handleState()
    }

    private fun bindHeader(){
        binding.tvNamePublication.text = intent.getStringExtra(USER_NAME)
        binding.tvMailPublication.text = intent.getStringExtra(USER_MAIL)
        binding.tvPhonePublication.text = intent.getStringExtra(USER_PHONE)
    }

    private fun handleState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.onLoadPublication(state.publications)
                }
            }
        }
    }
}

const val USER_ID_KEY = "userId"
const val USER_NAME = "userName"
const val USER_MAIL = "userMail"
const val USER_PHONE = "userPhone"
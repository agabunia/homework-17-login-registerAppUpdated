package com.example.homework_17_loginregisterappupdated.presentation.homePage

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework_17_loginregisterappupdated.BaseFragment
import com.example.homework_17_loginregisterappupdated.databinding.FragmentHomePageBinding
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    override fun setUp() {
        receiveDataFromLogin()
    }

    override fun setListeners() {
        binding.btnLogout.setOnClickListener {
            sendToLoginPage()
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.email.collect {
                    if(it.isNotEmpty()) {
                        binding.tvEmail.text = it
                    } else {
                        receiveDataFromLogin()
                    }
                }
            }
        }
    }

    private fun receiveDataFromLogin() {
        setFragmentResultListener("requestEmail") { _, bundle ->
            val receivedMail = bundle.getString("bundleUserEmail")
            binding.tvEmail.text = receivedMail
        }
    }

    private fun sendToLoginPage() {
        findNavController().navigate(HomeFragmentDirections.actionHomePageToLoginPage())
    }

}

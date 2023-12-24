package com.example.homework_17_loginregisterappupdated.presentation.registerPage

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework_17_loginregisterappupdated.BaseFragment
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.databinding.FragmentRegisterPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterPage :
    BaseFragment<FragmentRegisterPageBinding>(FragmentRegisterPageBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels()

    override fun setUp() {
        // No Implementation
    }

    override fun setListeners() {
        binding.btnRegister.setOnClickListener {
            registrationProcess()
        }

        binding.btnBack.setOnClickListener {
            sendToLoginPage()
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            if (it.data.id == 4) {
                                showToast("Registration succeeded")
                                sendUserData()
                                sendToLoginPage()
                            }
                        }

                        is Resource.Fail -> {
                            if (it.errorMessage.isNotBlank()) {
                                showToast("Registration Failed: ${it.errorMessage}")
                            }
                        }

                        is Resource.Loading -> {
                            if (it.isLoading) {
                                binding.progressBar.visibility = View.VISIBLE
                            } else {
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun registrationProcess() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()
        viewModel.register(email, password, repeatPassword)
    }

    private fun sendToLoginPage() {
        findNavController().navigate(RegisterPageDirections.actionRegisterPageToLoginPage())
    }

    private fun sendUserData() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val result = bundleOf().apply {
            putString("userDataEmail", email)
            putString("userDataPassword", password)
        }
        setFragmentResult("requestUserData", result)
    }

}

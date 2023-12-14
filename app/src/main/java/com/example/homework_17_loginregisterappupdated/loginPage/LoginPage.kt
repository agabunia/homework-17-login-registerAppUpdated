package com.example.homework_17_loginregisterappupdated.loginPage

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.homework_17_loginregisterappupdated.BaseFragment
import com.example.homework_17_loginregisterappupdated.R
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.databinding.FragmentLoginPageBinding
import kotlinx.coroutines.launch

class LoginPage : BaseFragment<FragmentLoginPageBinding>(FragmentLoginPageBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun setUp() {
        receiveDataFromRegister()
    }

    override fun setListeners() {
        binding.tvRegisterNow.setOnClickListener {
            sendToRegisterPage()
        }

        binding.btnLogin.setOnClickListener {
            loginProcess()
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            if (it.data.token.isNotBlank()) {
                                showToast("Registration succeeded")
                                saveUserEmail()
                                sendEmailAddress()
                                sendToHomePage()
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

    private fun sendEmailAddress() {
        val email: String = binding.etEmail.text.toString()
        setFragmentResult("requestEmail", bundleOf("bundleUserEmail" to email))
    }

    private fun sendToRegisterPage() {
        Navigation.findNavController(binding.root).navigate(R.id.action_loginPage_to_registerPage)
    }

    private fun sendToHomePage() {
        Navigation.findNavController(binding.root).navigate(R.id.action_loginPage_to_homePage)
    }

    private fun loginProcess() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.login(email, password)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun receiveDataFromRegister() {
        setFragmentResultListener("requestUserData") { _, bundle ->
            val receivedMail = bundle.getString("userDataEmail")
            val receivedPassword = bundle.getString("userDataPassword")

            if (!receivedMail.isNullOrBlank()) {
                binding.etEmail.setText(receivedMail)
                binding.etPassword.setText(receivedPassword)
            }
        }
    }

    private fun saveUserEmail() {
        if (binding.checkbox.isChecked) {
            viewModel.setEmail(binding.etEmail.text.toString())
        }
    }

}

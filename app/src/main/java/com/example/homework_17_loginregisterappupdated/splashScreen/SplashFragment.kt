package com.example.homework_17_loginregisterappupdated.splashScreen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.homework_17_loginregisterappupdated.BaseFragment
import com.example.homework_17_loginregisterappupdated.R
import com.example.homework_17_loginregisterappupdated.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun setUp() {
        viewModel.readSession()
    }

    override fun setListeners() {
        // ცარიელია
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mailFlow.collect {
                    redirectToFragment(it)
                }
            }
        }
    }

    private fun redirectToFragment(session: Boolean) {
        if (session) {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_splashFragment_to_homePage)
        } else {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_splashFragment_to_loginPage)
        }
    }

}

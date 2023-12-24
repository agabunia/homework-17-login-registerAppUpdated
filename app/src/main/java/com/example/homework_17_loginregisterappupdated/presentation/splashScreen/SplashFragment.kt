package com.example.homework_17_loginregisterappupdated.presentation.splashScreen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.homework_17_loginregisterappupdated.BaseFragment
import com.example.homework_17_loginregisterappupdated.R
import com.example.homework_17_loginregisterappupdated.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//@AndroidEntryPoint
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
                viewModel.sessionFlow.collect {
                    redirectToFragment(it)
                }
            }
        }
    }

    private fun redirectToFragment(session: Boolean) {
        if (session) {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomePage())
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginPage())
        }
    }

}

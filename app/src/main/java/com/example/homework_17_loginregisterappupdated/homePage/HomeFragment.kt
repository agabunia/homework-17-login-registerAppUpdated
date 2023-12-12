package com.example.homework_17_loginregisterappupdated.homePage

import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import com.example.homework_17_loginregisterappupdated.BaseFragment
import com.example.homework_17_loginregisterappupdated.R
import com.example.homework_17_loginregisterappupdated.databinding.FragmentHomePageBinding

class HomeFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {
    override fun setUp() {
        receiveDataFromLogin()
    }

    override fun setListeners() {
        binding.btnLogout.setOnClickListener {
            sendToLoginPage()
        }
    }

    override fun observeData() {
        // No Implementation
    }

    private fun receiveDataFromLogin() {
        setFragmentResultListener("requestEmail") { _, bundle ->
            val receivedMail = bundle.getString("bundleUserEmail")
            binding.tvEmail.text = receivedMail
        }
    }

    private fun sendToLoginPage() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homePage_to_loginPage)
    }

}

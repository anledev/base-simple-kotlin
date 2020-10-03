package com.anledev.basekotlinsimple.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.anledev.basekotlinsimple.data.network.AuthApi
import com.anledev.basekotlinsimple.data.network.Resource
import com.anledev.basekotlinsimple.data.repository.AuthRepository
import com.anledev.basekotlinsimple.databinding.FragmentLoginBinding
import com.anledev.basekotlinsimple.ui.base.BaseFragment
import com.anledev.basekotlinsimple.ui.enable
import com.anledev.basekotlinsimple.ui.home.HomeActivity
import com.anledev.basekotlinsimple.ui.startNewActivity
import com.anledev.basekotlinsimple.ui.visible

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(false)
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.user.access_token)
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.editTextTextEmailAddress.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            //@todo add input validations
            binding.progressbar.visible(true)
            viewModel.login(email, password)
        }
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AuthRepository =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}
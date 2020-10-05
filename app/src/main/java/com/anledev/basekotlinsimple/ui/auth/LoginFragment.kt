package com.anledev.basekotlinsimple.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.anledev.basekotlinsimple.data.network.AuthApi
import com.anledev.basekotlinsimple.data.network.Resource
import com.anledev.basekotlinsimple.data.repository.AuthRepository
import com.anledev.basekotlinsimple.databinding.FragmentLoginBinding
import com.anledev.basekotlinsimple.ui.base.BaseFragment
import com.anledev.basekotlinsimple.ui.enable
import com.anledev.basekotlinsimple.ui.handleApiError
import com.anledev.basekotlinsimple.ui.home.HomeActivity
import com.anledev.basekotlinsimple.ui.startNewActivity
import com.anledev.basekotlinsimple.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.user.access_token)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it)
            }
        })

        binding.editTextTextEmailAddress.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        //@todo add input validations
        viewModel.login(email, password)
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AuthRepository =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}
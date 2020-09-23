package com.anledev.basekotlinsimple.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anledev.basekotlinsimple.databinding.FragmentLoginBinding
import com.anledev.basekotlinsimple.network.AuthApi
import com.anledev.basekotlinsimple.repository.AuthRepository
import com.anledev.basekotlinsimple.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AuthRepository = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

}
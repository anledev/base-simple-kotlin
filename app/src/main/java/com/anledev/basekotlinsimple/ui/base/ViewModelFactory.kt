package com.anledev.basekotlinsimple.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anledev.basekotlinsimple.data.repository.AuthRepository
import com.anledev.basekotlinsimple.data.repository.BaseRepository
import com.anledev.basekotlinsimple.data.repository.UserRepository
import com.anledev.basekotlinsimple.ui.auth.AuthViewModel
import com.anledev.basekotlinsimple.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}
package com.hanyeop.songforyou.view.login.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentLoginBinding
import com.hanyeop.songforyou.view.login.UserViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val userViewModel by viewModels<UserViewModel>()
    override fun init() {
        initClickListener()
    }
    private fun initClickListener() {
        binding.apply {
            btnSignup.setOnClickListener{
                val action = LoginFragmentDirections.actionLoginFragmentToJoinFragment()
                findNavController().navigate(action)
            }
            btnLogin.setOnClickListener{
                if(userViewModel.email.value.isNullOrBlank() || userViewModel.password.value.isNullOrBlank()){

                }

            }
        }
    }
}
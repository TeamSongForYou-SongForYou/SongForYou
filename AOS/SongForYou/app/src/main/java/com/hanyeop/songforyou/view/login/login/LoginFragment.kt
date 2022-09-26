package com.hanyeop.songforyou.view.login.login

import androidx.navigation.fragment.findNavController
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
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

            }
        }
    }
}
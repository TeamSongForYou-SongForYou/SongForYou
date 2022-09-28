package com.hanyeop.songforyou.view.login.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentLoginBinding
import com.hanyeop.songforyou.di.ApplicationClass
import com.hanyeop.songforyou.utils.JWTUtils
import com.hanyeop.songforyou.view.login.UserViewModel
import com.hanyeop.songforyou.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var token : String
    override fun init() {

        binding.loginVM = userViewModel

        initClickListener()
        initViewModelCallback()
    }
    private fun initViewModelCallback() = with(binding){
        lifecycleScope.launch {
            userViewModel.isLoginChecked.collectLatest {
                Intent(requireContext(), MainActivity::class.java).apply{
//                                putExtra("user", user)
                                startActivity(this)
                                requireActivity().finish()
                            }
                // 로그인 성공시
//                if(it){
//                    token = userViewModel.token.value!!
//                    // 토큰이 있다면
//                    if(token != null) {
//                       ApplicationClass.sharedPreferencesUtil.saveToken(token)
//                        // 토큰 저장
//                        val user = JWTUtils.decoded(token)
//                        if(user != null){
//                            Intent(requireContext(), MainActivity::class.java).apply{
//                               // putExtra("user", user)
//                                startActivity(this)
//                                requireActivity().finish()
//                            }
//                        }else{
//                            makeToast("유저 정보 획득에 실패하였습니다")
//                        }
//                    }
//                }
            }
        }
    }
    private fun initClickListener() = with(binding) {
        btnSignup.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToJoinFragment()
            findNavController().navigate(action)
        }
        btnLogin.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {

                // email나 password가 입력되지 않았을 때
                if(userViewModel.loginEmail.value.isNullOrBlank() || userViewModel.loginPassword.value.isNullOrBlank()){
                    makeToast("아이디와 비밀번호를 입력해주세요")
                    return@launch
                }

                var map = HashMap<String, String>()
                map["id"] = tfLoginId.toString()
                map["pass"] = tfLoginPassword.toString()

                userViewModel.loginUser(map)

            }
        }


    }
    private fun makeToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }
}
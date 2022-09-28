package com.hanyeop.songforyou.view.login.join

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentJoinBinding
import com.hanyeop.songforyou.view.login.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "JoinFragment___"

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding>(R.layout.fragment_join) {
    private val userViewModel by viewModels<UserViewModel>()

    // 소셜로그인인지 아닌지 확인
    private val type by lazy { arguments?.getString("type") ?: "" }

    override fun init(){
        Log.d(TAG, "111")
        binding.joinVM = userViewModel

        Log.d(TAG, "222")
        initObserver()

//        initView()
//
        initClickListener()

        setTextChangedListener()

        initViewModelCallback()

        setItemSelectedListener()
    }

    private fun initObserver() {
        userViewModel.message.observe(viewLifecycleOwner) {

            // viewModel에서 Toast메시지 Event 발생시
            it.getContentIfNotHandled()?.let {
                makeToast(it)
            }
        }
    }
    private fun initViewModelCallback() = with(binding){
        lifecycleScope.launch {
            userViewModel.isEmailChecked.collectLatest {
                if(it){
                    userViewModel.requestEmailAuthNumber(tilEmail)
                }
            }
        }
        lifecycleScope.launch {
            // 인증 번호 전송 성공 시
            userViewModel.emailAuthNumber.collectLatest {
                btnEmailAuthNumberCheck.visibility = View.VISIBLE
            }
        }
        lifecycleScope.launch {
            userViewModel.isEmailAuthChecked.collectLatest {
                // 인증 번호 인증 성공 시
                if(it){
                    tilEmail.isEnabled = false
                    btnEmailAuthNumberRequest.isEnabled = false
                    tilEmailAuthNumber.editText?.setText("인증성공")
                    tilEmailAuthNumber.isEnabled = false
                    btnEmailAuthNumberCheck.isEnabled = false
                }
            }
        }
        lifecycleScope.launch {
            userViewModel.isNicknameChecked.collectLatest {
                // 사용가능한 닉네임인 경우
                if(it){
                    btnNicknameCheck.text = "사용가능"
                    btnNicknameCheck.isEnabled = false
                }
            }
        }
        lifecycleScope.launch {

            userViewModel.isJoinChecked.collectLatest {
                Log.d(TAG, it.toString())
                // 회원가입에 성공한 경우
                if(it){
                    findNavController().navigate(R.id.action_joinFragment_to_loginFragment)
                }else{
                    // 실패한 경우
                }
            }
        }

    }
    private fun initClickListener() = with(binding) {

        // 인증번호 전송(본인확인) 클릭시
        btnEmailAuthNumberRequest.setOnClickListener {
            userViewModel.checkEmail(tilEmail)
        }

        // 인증번호 확인 클릭시
        btnEmailAuthNumberCheck.setOnClickListener {
            userViewModel.checkEmailAuthNumber(tilEmailAuthNumber)
        }

        // 닉네임 중복확인 클릭시
        btnNicknameCheck.setOnClickListener {
            userViewModel.checkNickname(tilNickname)
        }

        // 회원가입 클릭시
        btnJoin.setOnClickListener {

            // 닉네임 중복 검사 확인
            if(!userViewModel.isNicknameChecked.value){
                makeTextInputLayoutError(tilNickname, "닉네임 중복검사를 해주세요")
                makeToast("닉네임 중복검사를 해주세요")
                return@setOnClickListener
            }

            // 비밀번호 유효성 검사 확인
            if (!userViewModel.validatePassword(tilPassword, tilPasswordCheck)) {
                return@setOnClickListener
            }

            // 출생년도 선택 확인
            if(userViewModel.year.value == null){
                makeToast("출생 년도를 선택해주세요")
                return@setOnClickListener
            }

            // 성별 선택 확인
            if(userViewModel.gender.value == null){
                makeToast("성별을 선택해주세요")
                return@setOnClickListener
            }
            // 검사를 모두 통과할 경우 회원가입 요청
            // 소셜인 경우

            // 일반 회원가입인 경우
            userViewModel.signUpUser()
        }
    }

    private fun setTextChangedListener() = with(binding) {

        // id 이메일 입력창 에러 비활성화
        tilEmail.editText?.addTextChangedListener {
            tilEmail.isErrorEnabled = false
        }

        // 이메일 인증창 에러 비활성화
        tilEmailAuthNumber.editText?.addTextChangedListener {
            tilEmailAuthNumber.isErrorEnabled = false
        }

        // 닉네임 입력창 에러 비활성화
        tilNickname.editText?.addTextChangedListener {
            tilNickname.isErrorEnabled = false
        }

        // 비밀번호 입력창 에러 비활성화
        tilPassword.editText?.addTextChangedListener {
            tilPassword.isErrorEnabled = false
        }

        // 비밀번호 재확인 입력창 에러 비활성화
        tilPasswordCheck.editText?.addTextChangedListener {
            tilPasswordCheck.isErrorEnabled = false
        }
    }

    private fun setItemSelectedListener() = with(binding) {

        // 스피너 선택 리스너 설정
        spinnerYear.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newItem ->
            Log.d(TAG, "setItemSelectedListener: $newItem")
            userViewModel.yearChanged(newItem)
        }
        spinnerGender.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newItem ->
            Log.d(TAG, "setItemSelectedListener: $newItem")
            userViewModel.genderChanged(newItem)
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    fun makeTextInputLayoutError(textInputLayout: TextInputLayout, msg: String) {
        textInputLayout.error = msg
        textInputLayout.isErrorEnabled = true
    }
}
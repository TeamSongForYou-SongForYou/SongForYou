package com.hanyeop.songforyou.view.login.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentLoginBinding
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

        Glide.with(this)
            .asGif()
            .load(R.raw.songforyou_logo)
            .listener(listener)
            .into(binding.tvLoginTitle)
        initClickListener()
        initViewModelCallback()
    }

    val listener = object: RequestListener<GifDrawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<GifDrawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: GifDrawable?,
            model: Any?,
            target: Target<GifDrawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            resource!!.setLoopCount(1)
            return false
        }
    }
    private fun initViewModelCallback() = with(binding){
        lifecycleScope.launch {
            userViewModel.isLoginChecked.collectLatest {
                if(it) {
                    Intent(requireContext(), MainActivity::class.java).apply {
//                                putExtra("user", user)
                        startActivity(this)
                        requireActivity().finish()
                    }
                }
            }
        }

        userViewModel.joinEvent.observe(viewLifecycleOwner) {
            val action = LoginFragmentDirections.actionLoginFragmentToSocialJoinFragment(it)
            findNavController().navigate(action)
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
        btnGoogleLogin.setOnClickListener {
//            googleSignIn()
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

//    private fun googleSignIn() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestScopes(Scope(Scopes.EMAIL))
//            .requestServerAuthCode(resources.getString(R.string.google_client_key))
//            .requestEmail()
//            .requestIdToken(getString(R.string.google_client_key))
//            .build()
//
//        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
//        val signInIntent: Intent = mGoogleSignInClient.signInIntent
//        googleSignInResult.launch(signInIntent)
//        Log.d("test5", "googleSignIn: ")
//    }
//
//    private val googleSignInResult: ActivityResultLauncher<Intent> = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()) {
//
//        Log.d("test5", ": 들어옴")
//
//        if (it.resultCode == Activity.RESULT_OK) {
//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                val accessToken = account.idToken!!
//
//                Log.d("test5", "$accessToken:--------- ")
//                userViewModel.googleLogin(accessToken)
//            } catch (e: ApiException) {
//                Log.w("test5", "signInResult:failed code=" + e.statusCode)
//            }
//        } else {
//            Log.d("test5", "$it: ")
//        }
//    }
}
package com.astar.mvvmlogin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.astar.mvvmlogin.R
import com.astar.mvvmlogin.databinding.FragmentLoginBinding
import com.astar.mvvmlogin.logic.UserLogic
import com.astar.mvvmlogin.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginBinding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val loginLogic = UserLogic(loginViewModel, this)
        val mainActivity = activity as MainActivity
        loginBinding.loginViewModel = loginViewModel
        loginBinding.loginLogic = loginLogic

        val isLoginObserver = Observer<Boolean> {
            if (it)
                mainActivity.onFragmentChange(2)
        }
        loginViewModel.isLogin.observe(viewLifecycleOwner, isLoginObserver)

        val account = loginLogic.getLoginRepository().getSavedAccount()
        if(account != null) {
            loginViewModel.userId.value = account.userId
            loginViewModel.password.value = account.password
            loginViewModel.checkRemember.value = true
        }
        return loginBinding.root
    }

    fun showErrorMessage(errorCode: Int) {
        val errorMessage: String = when (errorCode) {
            1 -> "아이디를 입력해주세요."
            2 -> "비밀번호를 입력해주세요."
            3 -> "아이디 또는 비밀번호가 올바르지 않습니다."
            4 -> "서버와 연결을 할 수 없습니다."
            else -> "알 수 없는 에러가 발생했습니다."
        }
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
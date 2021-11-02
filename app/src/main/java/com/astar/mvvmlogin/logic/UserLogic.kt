package com.astar.mvvmlogin.logic

import com.astar.mvvmlogin.dao.AppDatabase
import com.astar.mvvmlogin.info.UserInfo
import com.astar.mvvmlogin.model.Account
import com.astar.mvvmlogin.repository.UserRepository
import com.astar.mvvmlogin.view.LoginFragment
import com.astar.mvvmlogin.viewmodel.LoginViewModel

class UserLogic(
    private val loginViewModel: LoginViewModel,
    private val loginFragment: LoginFragment
) {
    private val loginRepository = UserRepository(
        AppDatabase.getInstance(loginFragment.requireContext())!!
    )

    fun onLogin() {
        val userId = loginViewModel.userId.value
        val password = loginViewModel.password.value

        if (userId?.isEmpty() == true) {
            loginFragment.showErrorMessage(1)
            return
        } else if (password?.isEmpty() == true) {
            loginFragment.showErrorMessage(2)
            return
        }
        loginRepository.login(userId!!, password!!) { code: Int ->
            if (code == 0) {
                UserInfo.userId = userId
                UserInfo.password = password
                UserInfo.isLogin = true
                loginViewModel.isLogin.value = true
                if (loginViewModel.checkRemember.value == true) {
                    if (loginRepository.getSavedAccount() == null)
                        loginRepository.saveAccount(Account(userId, password))
                } else {
                    loginRepository.deleteAccount()
                }
            } else {
                loginFragment.showErrorMessage(code)
            }
        }
    }

    fun getLoginRepository(): UserRepository {
        return loginRepository
    }
}
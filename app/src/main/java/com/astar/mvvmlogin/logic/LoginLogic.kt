package com.astar.mvvmlogin.logic

import com.astar.mvvmlogin.dao.AppDatabase
import com.astar.mvvmlogin.dao.UserDao
import com.astar.mvvmlogin.info.UserInfo
import com.astar.mvvmlogin.model.Account
import com.astar.mvvmlogin.repository.LoginRepository
import com.astar.mvvmlogin.view.LoginFragment
import com.astar.mvvmlogin.viewmodel.LoginViewModel

class LoginLogic(
    private val loginViewModel: LoginViewModel,
    private val loginFragment: LoginFragment
) {
    private val loginRepository = LoginRepository.getInstance(
        AppDatabase.getInstance(loginFragment.requireContext())!!.userDao()
    )

    fun onLogin() {
        val userId = loginViewModel.userId
        val password = loginViewModel.password

        if (userId.value?.isEmpty() == true) {
            loginFragment.showErrorMessage(1)
            return
        } else if (password.value?.isEmpty() == true) {
            loginFragment.showErrorMessage(2)
            return
        }
        Thread {
            val response = loginRepository.login(userId.value!!, password.value!!)
            if (response == 0) {
                val userId = loginViewModel.userId.value
                val password = loginViewModel.password.value
                UserInfo.userId = userId!!
                UserInfo.password = password!!
                UserInfo.isLogin = true
                loginViewModel.isLogin.value = true
                if (loginViewModel.checkRemember.value == true) {
                    if (getUserDao().getAccount() == null)
                        getUserDao().insert(Account(userId, password))
                } else {
                    getUserDao().delete()
                }
            } else {
                loginFragment.showErrorMessage(3)
            }
        }.start()
    }

    fun getUserDao(): UserDao {
        return loginRepository.userDao
    }
}
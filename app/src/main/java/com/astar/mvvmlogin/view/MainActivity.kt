package com.astar.mvvmlogin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.astar.mvvmlogin.R
import com.astar.mvvmlogin.info.UserInfo

class MainActivity : AppCompatActivity() {
    private lateinit var loginFragment: LoginFragment
    private lateinit var welcomeFragment: WelcomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginFragment = LoginFragment()
        welcomeFragment = WelcomeFragment()

        if (!UserInfo.isLogin) {
            onFragmentChange(1)
        }
    }

    fun onFragmentChange(index: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (index) {
            1 -> fragmentTransaction.replace(R.id.container, loginFragment)
            2 -> fragmentTransaction.replace(R.id.container, welcomeFragment)
        }
        fragmentTransaction.commit()
    }
}
package com.astar.mvvmlogin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.astar.mvvmlogin.R
import com.astar.mvvmlogin.databinding.FragmentWelcomeBinding
import com.astar.mvvmlogin.info.UserInfo

class WelcomeFragment : Fragment() {

    private lateinit var welcomeBinding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        welcomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);
        welcomeBinding.userInfo = UserInfo
        return welcomeBinding.root
    }
}
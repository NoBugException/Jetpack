package com.yunchong.jetpack.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.yunchong.jetpack.R


class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // binding = FragmentLoginBinding.inflate(inflater)
        // 或
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding?.root
    }

}
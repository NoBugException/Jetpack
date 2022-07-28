package com.yunchong.jetpack.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.yunchong.jetpack.R
import kotlinx.android.synthetic.main.fragment_three.view.*
import kotlinx.android.synthetic.main.fragment_three.view.back

class ThreeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val name: String? = arguments?.getString("name") // 获取上级页面传递过来的文本
        val view = inflater.inflate(R.layout.fragment_three, container, false)
        view.tv_fragment_three.text = "上级页面传递的数据：$name"
        view.back.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
            // 或者
            // findNavController().popBackStack()
        }
        return view
    }
}
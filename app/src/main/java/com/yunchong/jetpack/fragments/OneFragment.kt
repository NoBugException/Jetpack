package com.yunchong.jetpack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.yunchong.jetpack.R
import kotlinx.android.synthetic.main.fragment_one.view.*

class OneFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        view.back.setOnClickListener {
            activity?.finish()
        }
        view.next.setOnClickListener {
            // 设置动画参数
//            val navOption = navOptions {
//                anim {
//                    enter = R.anim.slide_in_right
//                    exit = R.anim.slide_out_left
//                    popEnter = R.anim.slide_in_left
//                    popExit = R.anim.slide_out_right
//                }
//            }

            val bundle = OneFragmentDirections.actionOneFragmentToTwoFragment(name = "hello zhangsan").arguments
            // 参数设置
//            val bundle = Bundle()
//            bundle.putString("name","zhangsan")

            Navigation.findNavController(view).navigate(R.id.action_oneFragment_to_twoFragment, bundle, null)
            // 或者
            // findNavController().navigate(R.id.action_oneFragment_to_twoFragment, bundle, navOption)
        }
        return view
    }
}
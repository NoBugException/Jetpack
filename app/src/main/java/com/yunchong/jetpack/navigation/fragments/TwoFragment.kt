package com.yunchong.jetpack.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.yunchong.jetpack.R
import kotlinx.android.synthetic.main.fragment_two.view.*

class TwoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         val name: String? = arguments?.getString("name") // 获取上级页面传递过来的文本
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        val safeArgs: TwoFragmentArgs by navArgs()
        val name = safeArgs.name
        view.tv_name_fragmengt_two.text = "上级页面传递的数据：$name"
        view.back.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
            // 或者
            // findNavController().popBackStack()
        }
        view.next.setOnClickListener {
            // 设置动画参数
            val navOption = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }

            // 参数设置
            val bundle = Bundle()
            bundle.putString("name","lisi")

             Navigation.findNavController(view).navigate(R.id.action_twoFragment_to_threeFragment, bundle, navOption)
            // 或者
            // findNavController().navigate(R.id.action_twoFragment_to_threeFragment, null)
        }
        return view
    }
}
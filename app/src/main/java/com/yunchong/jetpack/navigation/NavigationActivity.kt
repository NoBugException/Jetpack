package com.yunchong.jetpack.navigation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.R
import com.yunchong.jetpack.ScopedActivity
import com.yunchong.jetpack.databinding.ActivityNavigationBinding

// Navigation 官方文档：
// https://developer.android.google.cn/guide/navigation/navigation-getting-started?hl=zh-cn

// Navigation 是用来管理 Fragment 的切换，并且可以通过可视化的方式，看见App的交互流程
//  优点:
//  1、处理 Fragment 的切换（上文已说过）
//  2、默认情况下正确处理 Fragment 的前进和后退
//  3、为过渡和动画提供标准化的资源
//  4、实现和处理深层连接
//  5、可以绑定 Toolbar 、 BottomNavigationView 和 ActionBar 等
//  6、SafeArgs （Gradle插件） 数据传递时提供类型安全性
//  7、ViewModel 支持

// Navigation 组件由三个关键部分组成，这三个部分协同工作。它们是：
// 1、导航图（新 XML 资源），创建 navigation 文件夹，在 navigation 文件夹中新建xml导航图
// 2、NavHostFragment，在 NavigationActivity 中加入 NavHostFragment（当前fragment的容器）
// 3、NavController，导航的控制者



class NavigationActivity : ScopedActivity() {

    private lateinit var binding : ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityNavigationBinding>(this@NavigationActivity,
            R.layout.activity_navigation
        )
    }
}
package com.yunchong.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.yunchong.jetpack.databinding.ActivityLivedataBinding
import com.yunchong.jetpack.model.UserModel
import com.yunchong.jetpack.model.UserModelByMediator

class LiveDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLivedataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLivedataBinding>(this@LiveDataActivity, R.layout.activity_livedata)
        val userModel = ViewModelProvider(this@LiveDataActivity).get(UserModel::class.java)

        // 使用 observe 会让观察者随着生命周期的活跃而活跃，当生命周期处于活跃状态时LiveData才会更新数据，反之不会更新数据
        // 不需要主动调用 removeObserver(Observer) 移除观察者
//        userModel.userLiveData.observe(this@LiveDataActivity, Observer<User> {
//            binding.userinfo.text = "大家好，我叫" + it.userName + "，今年" + it.age + "岁了！"
//        })

        // 使用 observeForever，会导致观察者一直处于活跃状态
        // 我们需要主动调用 removeObserver(Observer) 移除观察者
//        userModel.userLiveData.observeForever {
//            binding.userinfo.text = "大家好，我叫" + it.userName + "，今年" + it.age + "岁了！"
//        }

        // 使用map函数，将User类型转成Int类型，Observer的返回值也从User类型转换成Int类型
        // Function 第一个参数表示输入，第二个参数表示输出
        // map 第一个参数表示输入数据，第二个参数Function的返回值是输出数据
//        Transformations.map(userModel.userLiveData, Function<User, Int> {
//            it.age
//        }).observe(this@LiveDataActivity, Observer<Int> {
//            binding.userinfo.text = "大家好，我今年 $it 岁了！"
//        })

        // 使用switchMap函数，将User类型转成LiveData<Int>类型，Observer的返回值从User类型转换成Int类型
        // Function 第一个参数表示输入，第二个参数表示输出
        // map 第一个参数表示输入数据，第二个参数Function的返回值是输出数据
//        Transformations.switchMap(userModel.userLiveData, Function<User, LiveData<Int>> {
//            val userLiveData = MutableLiveData<Int>()
//            val age = userModel.userLiveData.value?.age
//            userLiveData.postValue(age)
//            userLiveData
//        }).observe(this@LiveDataActivity, Observer<Int> {
//            binding.userinfo.text = "大家好，我今年 $it 岁了！"
//        })

        val userModelByMediator = ViewModelProvider(this@LiveDataActivity).get(UserModelByMediator::class.java)
        userModelByMediator.mediatorLiveData.observe(this@LiveDataActivity) {
            when(it.userName) {
                "张三" -> binding.userinfo1.text = "大家好，我叫${it.userName}，今年${it.age}岁了！"
                "李四" -> binding.userinfo2.text = "大家好，我叫${it.userName}，今年${it.age}岁了！"
            }
        }

        binding.activityDataButton.setOnClickListener {
//             userModel.updateUserInfo()
            userModelByMediator.updateUserInfo1()
            userModelByMediator.updateUserInfo2()
        }
    }

}
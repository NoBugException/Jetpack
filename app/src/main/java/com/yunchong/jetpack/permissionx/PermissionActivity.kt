package com.yunchong.jetpack.permissionx

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.permissionx.guolindev.PermissionX
import com.yunchong.jetpack.R
import com.yunchong.jetpack.ScopedActivity
import com.yunchong.jetpack.databinding.ActivityPermissionxBinding

class PermissionActivity : ScopedActivity() {

    private lateinit var binding : ActivityPermissionxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPermissionxBinding>(this@PermissionActivity,
            R.layout.activity_permissionx
        )

        binding.activityDataButton.setOnClickListener {
            // 获取权限
            PermissionX.init(this@PermissionActivity)
                .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE)
                .onExplainRequestReason { scope, deniedList ->
                    var permissions : String = ""
                    for (permission : String in deniedList) {
                        when(permission) {
                            Manifest.permission.READ_CONTACTS -> permissions += if (permissions.isEmpty()) "联系人权限" else "、联系人权限"
                            Manifest.permission.CAMERA -> permissions += if (permissions.isEmpty()) "相机权限" else "、相机权限"
                            Manifest.permission.CALL_PHONE -> permissions += if (permissions.isEmpty()) "电话权限权限" else "、电话权限"
                            else -> permissions
                        }
                    }
                    scope.showRequestReasonDialog(deniedList,
                        "请再次确认是否需要 $permissions。\n如果不需要，部分功能无法使用。", "确认", "取消")
                }
                .explainReasonBeforeRequest()
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "打开设置页面，手动打开权限", "OK", "Cancel")
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
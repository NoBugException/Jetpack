package com.yunchong.jetpack.room

import android.os.Bundle
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.yunchong.jetpack.ScopedActivity
import com.yunchong.jetpack.databinding.ActivityRoomBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch

class RoomActivity : ScopedActivity() {

    private lateinit var binding : ActivityRoomBinding
    private var userDao: UserDao? = null
    private var bookDao: BookDao? = null
    private var hobbyDao: HobbyDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDao = AppDataBase.getInstance(applicationContext).userDao()
        bookDao = AppDataBase.getInstance(applicationContext).bookDao()
        hobbyDao = AppDataBase.getInstance(applicationContext).hobbyDao()
        binding.userInsert.setOnClickListener { // 添加用户
            launch(Dispatchers.Default + coroutineExceptionHandler) { // 数据库操作不能放在主线程
                val address = Address("xxx街道", "xxxStatus", "xxx城市", "xxx邮编")
                val userList = ArrayList<User>()
                userList.add(User(1, "NobugException1", "123456", "zhangsan1", address, 1))
                userList.add(User(2, "NobugException2", "123456", "zhangsan2", address, 1))
                userList.add(User(3, "NobugException3", "123456", "zhangsan3", address, 1))
                userList.add(User(4, "NobugException4", "123456", "zhangsan4", address, 1))
                userList.add(User(5, "NobugException5", "123456", "zhangsan5", address, 1))
                userList.add(User(6, "NobugException6", "123456", "zhangsan6", address, 1))
                userList.add(User(7, "NobugException7", "123456", "zhangsan7", address, 1))
                for (index in userList.indices) {
                    userDao?.addUser(userList[index])
                }
            }
        }

        binding.deleteUser.setOnClickListener { // 删除一个用户
            launch(Dispatchers.Default + coroutineExceptionHandler) { // 数据库操作不能放在主线程
                userDao?.deleteUserById(1)
            }
        }

        binding.bookInsert.setOnClickListener { // 插入书籍
            launch(Dispatchers.Default + coroutineExceptionHandler) {
                val bookList = ArrayList<Book>()
                bookList.add(Book(1, "Java疯狂讲义", 58F))
                bookList.add(Book(2, "C++从入门到放弃", 78F))
                bookList.add(Book(3, "智能家居入门篇", 68F))
                for (index in bookList.indices) {
                    bookDao?.addBook(bookList[index])
                }
            }
        }

        binding.hobbyInsert.setOnClickListener { // 插入喜好
            launch(Dispatchers.Default + coroutineExceptionHandler) {
                val hobbyList = ArrayList<Hobby>()
                hobbyList.add(Hobby(1, 1, 1))
                hobbyList.add(Hobby(2, 2, 2))
                hobbyList.add(Hobby(3, 3, 3))
                hobbyList.add(Hobby(4, 4, 1))
                hobbyList.add(Hobby(5, 5, 2))
                hobbyList.add(Hobby(6, 6, 3))
                hobbyList.add(Hobby(7, 7, 1))
                for (index in hobbyList.indices) {
                    hobbyDao?.addHobby(hobbyList[index])
                }
            }
        }

        binding.searchByBobby.setOnClickListener { // 根据喜好，查询用户名和图书名
            launch(Dispatchers.Default + coroutineExceptionHandler) {
                // 已知喜好数据
                val hobby = Hobby(5, 5, 2)
                // 根据喜好查询用户名
                val user = userDao?.getUserById(hobby.user_id)
                val book = bookDao?.getBookById(hobby.book_id)
                if (user == null || book == null) {
                    binding.textContent.text = "查找不到数据"
                    return@launch
                }
                binding.textContent.text = user?.name + "喜欢的书籍是：《" + book.name + "》"
            }
        }
    }
}
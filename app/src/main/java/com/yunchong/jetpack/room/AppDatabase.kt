package com.yunchong.jetpack.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


/**
 * 数据库文件
 *
 * @Database 数据库注解
 * entities 数据库中表格集合
 * version 数据库版本号
 * exportSchema 默认为true
 *     true：将数据库的配置导出来，存储到 ”/当前模块/schemas/AppDateBase路径/1.json“， 1为数据库版本号version
 *     false：数据库的配置不会导出来
 */
@Database(entities = [User::class, Book::class, Hobby::class],version = 3,exportSchema = true)
abstract class AppDataBase:RoomDatabase() {

    // 得到UserDao
    abstract fun userDao():UserDao
    // 得到BookDao
    abstract fun bookDao():BookDao
    // 得到HobbyDao
    abstract fun hobbyDao():HobbyDao

    companion object{

        @Volatile
        private var instance:AppDataBase? = null

        fun getInstance(context:Context):AppDataBase{
            return instance?: synchronized(this) {
                instance?:buildDataBase(context)
                    .also {
                        instance = it
                    }
            }
        }

        // 当数据库第一次被操作时，并且满足1-2升级条件时，则从1升级到2
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("yunchong", "==新增字段user_temp===")
                //执行升级相关操作，新增user_temp字段
                database.execSQL("ALTER TABLE user ADD COLUMN user_temp INTEGER NOT NULL DEFAULT 1")
            }
        }

        // 当数据库第一次被操作时，并且满足2-3升级条件时，则从2升级到3
        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("yunchong", "==删除user_temp字段===")
                //执行升级相关操作，删除user_temp字段

                // 创建user_temp表，user_temp字段要去掉
                database.execSQL("CREATE TABLE user_temp (" +
                        "user_id INTEGER PRIMARY KEY NOT NULL, " +
                        "user_account TEXT NOT NULL, " +
                        "user_pwd TEXT NOT NULL, " +
                        "user_name TEXT NOT NULL, " +
                        "street TEXT NOT NULL, " +
                        "state TEXT NOT NULL, " +
                        "city TEXT NOT NULL, " +
                        "postCode TEXT NOT NULL, " +
                        "user_status INTEGER NOT NULL)")
                // 将user中的数据复制到user_temp表
                database.execSQL("INSERT INTO user_temp (user_id, user_account, user_pwd, user_name, street, state, city, postCode, user_status) " +
                        "SELECT user_id, user_account, user_pwd, user_name, street, state, city, postCode, user_status " +
                        "FROM user")
                // 删除user表
                database.execSQL("DROP TABLE user")
                // 将user_temp表重命名成user
                database.execSQL("ALTER TABLE user_temp RENAME TO user")
            }
        }

        private fun buildDataBase(context: Context):AppDataBase{
            return Room
                .databaseBuilder(context,AppDataBase::class.java,"room.db")
                // .allowMainThreadQueries() // 是否允许在主线程中执行数据库的增删改查
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .fallbackToDestructiveMigration()
                .createFromAsset("db/student.db")
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // 1、在表格中添加数据，如果不存在数据库，则新建数据库，执行回调
                        // 2、在表格中添加数据，如果已存在数据库，则不执行回调
                    }
                })
                .build()
        }
    }
}
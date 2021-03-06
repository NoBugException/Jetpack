/**
 * 测试专用
 */
fun main() {

    // 1、runBlocking：阻塞主线程
    // 2、GlobalScope.launch：不阻塞主线程
    // 3、在协程中使用 launch 新建多个子协程(同一线程池)，launch 和 launch之间是并发执行
    // 4、挂起函数关键字：suspend
    // 5、coroutineScope{}必须在挂起函数或者协程里使用，直接外部协程会被挂起，coroutineScope{}执行完之后才会执行外部协程
    // 6、CoroutineScope 可以将协程的作用域加到类中，class MyClass: CoroutineScope by CoroutineScope(Dispatchers.Default)
    // 7、取消协程的方法：
    //    val job = GlobalScope.launch {  }
    //    job.cancel() // 取消协程
    //
    //    runBlocking {
    //        val  job = launch {  }
    //        job.cancel()
    //    }
    //
    //    val job = Job()
    //    val scope = CoroutineScope(job)
    //    scope.launch {
    //        // 处理具体的逻辑
    //    }
    //    job.cancel()
    // 8、async 方法会将当前协程阻塞住，直到返回结果为止
    //    多个async并发执行：
    //    val result1 = async {}.await()
    //    val result2 = async {}.await()
    //    多个async并行执行:
    //    val deferred1 = async {}
    //    val deferred2 = async {}
    //    val res1 = deferred1.await()
    //    val res2 = deferred2.await()
    // 9、withContext async的简化版
    //    val result = withContext(Dispatchers.Default) {}
    //    withContext 具有超时机制：
    //       withTimeout(500)：超时抛出TimeoutCancellationException异常
    //       withTimeoutOrNull(500)：超时返回null
    // 10、suspendCoroutine 让回调方法有返回值
    //     val response = request("B")
    //     suspend fun request(address: String): String {
    //         return suspendCoroutine { continuation ->
    //             sendHttpRequest(address, object : HttpCallbackListener {
    //                 override fun onFinish(response: String) {
    //                     continuation.resume(response) // 释放
    //                 }
    //                 override fun onError(e: Exception) {
    //                     continuation.resumeWithException(e) // 释放
    //                 }
    //             })
    //         }
    //     }
    //    fun sendHttpRequest(address: String, callbackListener: HttpCallbackListener) {
    //        when(address) {
    //            "A" -> callbackListener.onFinish("finish")
    //            "B" -> callbackListener.onError(RuntimeException("error"))
    //        }
    //    }
    // 11、Android项目中常用的写法
    // val coroutineScope = CoroutineScope(Dispatchers.Main)
    // coroutineScope.launch { } : 非阻塞，返回值是 Job，Job可以取消协程
    // coroutineScope.async { }：非阻塞，返回值是 Deferred<T>
    // val str: String = coroutineScope.async { "" }.await()：加入await之后就变成阻塞方法，有返回值
    // 如果有返回值，我们常用 withContext 代替：
    // val str: String = withContext(coroutineScope.coroutineContext) { "" }
    // 12、结合 ViewModel 使用
    // 如果某个类继承ViewModel或AndroidViewModel，那么自带viewModelScope
    // 直接使用：
    //         viewModelScope.launch(Dispatchers.IO) {
    //          }
    // viewModelScope 在 ViewModel 的作用域内执行，
    // 如果 ViewModel 因用户离开屏幕而被销毁，则 viewModelScope 会自动取消，且所有运行的协程也会被取消
    // 13、网络请求的协程用法
    // class LoginViewModel(
    //    private val loginRepository: LoginRepository
    // ): ViewModel() {
    //
    //    fun login(username: String, token: String) {
    //        viewModelScope.launch {
    //            val jsonBody = "{ username: \"$username\", token: \"$token\"}"
    //            val result = loginRepository.makeLoginRequest(jsonBody)
    //            when (result) {
    //                is Result.Success<LoginResponse> -> // Happy path
    //                else -> // Show error in UI
    //            }
    //        }
    //    }
    // }
    //     class LoginRepository(...) {
    //        ...
    //        suspend fun makeLoginRequest(
    //            jsonBody: String
    //        ): Result<LoginResponse> {
    //
    //            // Move the execution of the coroutine to the I/O dispatcher
    //            return withContext(Dispatchers.IO) {
    //                // Blocking network request code
    //            }
    //        }
    //    }
    // 协程可以确保网络请求避免阻塞主线程， makeLoginRequest 方法使用 suspend 修饰，suspend 将当前协程挂起，
    // 等待 makeLoginRequest 执行完成则回复当前协程，并返回指定类型的返回值。类似于阻塞线程，但协程可以不阻塞主线。
}






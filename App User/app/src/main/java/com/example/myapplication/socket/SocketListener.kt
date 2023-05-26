package com.example.myapplication.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.io.IOException
import java.net.URISyntaxException


// Được Trong ví dụ trên, SocketHandler được khai báo là một object, cho phép tạo một thể hiện duy nhất của nó. Bạn có thể truy cập vào thể hiện duy nhất này thông qua tên lớp SocketHandler.
class SocketHandler:Service() {


    lateinit var mSocket: Socket
    override fun onCreate() {
        super.onCreate()

        setSocket()
        establishConnection()
        handleReceiver()
        mSocket.on("confirmSocket", Emitter.Listener {
                val intent = Intent("confirmSocket")
                intent.putExtra("confirmSocket", "Bạn có đơn hàng cần xác nhận")
                this.sendBroadcast(intent)
        })

    }

    companion object {
        @Volatile
        private var INSTANCE: SocketHandler? = null

        fun getInstance(): SocketHandler =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SocketHandler().also { INSTANCE = it }
            }
    }



    @Synchronized
    fun setSocket() {
        try {
// "http://10.0.2.2:3000" is the network your Android emulator must use to join the localhost network on your computer
// "http://localhost:3000/" will not work
// If you want to use your physical phone you could use the your ip address plus :3000
// This will allow your Android Emulator and physical device at your home to connect to the server
            mSocket = IO.socket("http://10.0.2.2:3000")



        } catch (e: URISyntaxException) {

        }
    }
    fun handleReceiver(){
        mSocket.emit("login","41")
    }


    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mSocket.connect()
        // Trả về giá trị START_STICKY để Service tiếp tục chạy khi bị đóng bởi hệ thống
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            mSocket.disconnect();
            mSocket.off("confirmSocket");
            mSocket.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}
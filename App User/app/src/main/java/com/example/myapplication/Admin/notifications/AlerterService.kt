package com.example.myapplication.Admin.notifications

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AlerterService: Service() {

    override fun onCreate() {
        super.onCreate()
        // Khởi tạo và cấu hình máy chủ ở đây



    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Bắt đầu hoặc tiếp tục hoạt động của máy chủ ở đây


        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Giải phóng tài nguyên và dừng máy chủ ở đây
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Trả về null nếu không sử dụng giao tiếp liên quan đến Binder
        return null
    }
}
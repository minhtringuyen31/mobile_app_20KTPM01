package com.example.myapplication.Admin.pages.dashboard

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.modals.DashboardItems
import com.example.myapplication.R
import com.example.myapplication.pages.activities.user.Login
import com.example.myapplication.socket.SocketHandler
import com.tapadoo.alerter.Alerter


class SocketReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "confirmSocket") {
            val data = intent.getStringExtra("confirmSocket")
            // Xử lý dữ liệu nhận được từ Service tại đây
            Alerter.create(context as Activity)
                .setTitle("Đơn hàng")
                .setDuration(10000)
                .enableSwipeToDismiss()
                .setIcon(R.drawable.icon_order)
                .setText(data.toString())
                .setBackgroundColorRes(cn.pedant.SweetAlert.R.color.main_blue_color) //
                .show()
        }
    }
}
class Dashboard : AppCompatActivity() {
    private val dashboardItems = ArrayList<DashboardItems>()
    private lateinit var logo:ImageView
    private val socketReceiver = SocketReceiver()

    private fun showNotification() {
////         Create an explicit intent for an Activity in your app
//        val intent = Intent(this, MainActivity::class.java).apply {
//            intent.putExtra("FragmentToOpen", "Activities") // Truyền tên Fragment cần mở
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent: PendingIntent =
//            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//        createNotificationChannel()
//        val builder = NotificationCompat.Builder(this, "123")
//            .setSmallIcon(R.drawable.baseline_notifications_active_24)
//            .setContentTitle("My notification")
//            .setContentText("Hello World!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            // Set the intent that will fire when the user taps the notification
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    this@Dashboard,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@Dashboard,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    11111
                )
            }
//            notify(0, builder.build())
        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test Notification"
            val descriptionText = "Decription for test"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("123", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val REQUEST_CODE_NOTIFICATION_PERMISSION = 11111
        when (requestCode) {
            REQUEST_CODE_NOTIFICATION_PERMISSION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Quyền được cấp, tiến hành hiển thị notification
                    showNotification()
                } else {
                    // Quyền bị từ chối, thông báo cho người dùng và thoát ứng dụng
                    Toast.makeText(
                        this,
                        "Bạn cần cấp quyền truy cập notification để sử dụng tính năng này",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                return
            }
        }
    }
    private fun isNotificationPermissionGranted(): Boolean {
        // Kiểm tra xem quyền thông báo đã được cấp hay chưa
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        return notificationManagerCompat.areNotificationsEnabled()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

//        val intent = Intent(this, SocketHandler::class.java)
//        startService(intent)

        val intent = Intent(this, SocketHandler::class.java)
        startService(intent)

        val intentFilter = IntentFilter("confirmSocket")
        registerReceiver(socketReceiver, intentFilter)

        if (!isNotificationPermissionGranted()) {
            createNotificationChannel()
            showNotification()
        }
        dashboardItems.add(DashboardItems("Sản phẩm", R.drawable.baseline_shopping_bag_24))
        dashboardItems.add(DashboardItems("Danh mục", R.drawable.baseline_category_24))
        dashboardItems.add(DashboardItems("Người dùng", R.drawable.baseline_account_circle_24))
        dashboardItems.add(DashboardItems("Đơn hàng", R.drawable.baseline_shopping_cart_24))
        dashboardItems.add(DashboardItems("Món thêm", R.drawable.baseline_fastfood_24))
        dashboardItems.add(DashboardItems("Khuyến mãi", R.drawable.baseline_discount_24))
        dashboardItems.add(DashboardItems("Bình luận", R.drawable.baseline_comment_24))
        dashboardItems.add(DashboardItems("Thống kê", R.drawable.baseline_bar_chart_24))

        logo = findViewById(R.id.logoAvatar)
        logo.setOnClickListener {
            val intent = Intent(
                this,
                Login::class.java
            )
            val sharedPreferences =
                getSharedPreferences("user", MODE_PRIVATE)
            val editor1 = sharedPreferences.edit().remove("role").apply()
            val editor2 = sharedPreferences.edit().remove("userID").apply()
            startActivity(intent)
        }

        val adapter = DashboardAdapter(this, dashboardItems)

        val rvDashboard = findViewById<RecyclerView>(R.id.dashboardRV)

        rvDashboard.adapter = adapter

        rvDashboard.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onDestroy() {
        unregisterReceiver(socketReceiver)
        super.onDestroy()


    }
}
package com.example.composemediaplayer.media.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.composemediaplayer.MainActivity
import com.example.composemediaplayer.R

class AlarmService : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Alarm triggered", Toast.LENGTH_SHORT).show()
        Log.wtf("Broadcast Reciever:", "Alarm triggered")

        context ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            showNotification(context.applicationContext)
        } else {
            context.applicationContext.startActivity(Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
       // PushUtils.showReceiverCalledNotification(context)
    }

    private fun showNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default", "default", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        with(NotificationCompat.Builder(context, "default")) {
            setSmallIcon(R.drawable.ic_baseline_music_note_24)
            setContentTitle("Custom Title")
            setContentText("Tap to start the application")
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            manager.notify(87, build())
        }
    }

}
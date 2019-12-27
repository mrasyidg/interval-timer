package id.example.intervaltimerapp.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.TaskStackBuilder
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import id.example.intervaltimerapp.AppConstants
import id.example.intervaltimerapp.HomeActivity
import com.example.intervaltimerapp.R
import id.example.intervaltimerapp.receiver.TimerNotificationActionReceiver
import java.text.SimpleDateFormat
import java.util.*

class NotificationUtil {
    // To call these method anywhere and not need an instance of notification class
    // Can be used from API 26 (Oreo or later)
    companion object{
        private const val CHANNEL_ID_TIMER = "menu_timer"
        private const val CHANNEL_NAME_TIMER = "Interval Timer App"
        private const val TIMER_ID = 0

        // Timer finished
        fun showTimerExpired(context: Context){

            //To start the timer from the notification
            val startIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            startIntent.action = AppConstants.ACTION_START
            val startPendingIntent = PendingIntent.getBroadcast(context,
                0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val notificationBuilder =
                getBasicNotificationBuilder(
                    context,
                    CHANNEL_ID_TIMER,
                    true
                )
            notificationBuilder.setContentTitle("Timer Expired!")
                .setContentText("Start again?")
                .setContentIntent(
                    getPendingIntentWithStack(
                        context,
                        HomeActivity::class.java
                    )
                )
                .addAction(R.drawable.ic_start, "Start", startPendingIntent)
                .setColorized(true)
                .setColor(204102153)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(
                CHANNEL_ID_TIMER,
                CHANNEL_NAME_TIMER, true)

            notificationManager.notify(TIMER_ID, notificationBuilder.build())
        }

        @TargetApi(16)
        fun showTimerRunning(context: Context, wakeUpTime: Long){
            val stopIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            stopIntent.action = AppConstants.ACTION_STOP
            val stopPendingIntent = PendingIntent.getBroadcast(context,
                0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val pauseIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            pauseIntent.action = AppConstants.ACTION_PAUSE
            val pausePendingIntent = PendingIntent.getBroadcast(context,
                0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

            val notificationBuilder =
                getBasicNotificationBuilder(
                    context,
                    CHANNEL_ID_TIMER,
                    true
                )
            notificationBuilder.setContentTitle("Timer is Running.")
                .setContentText("End: ${df.format(Date(wakeUpTime))}")
                .setContentIntent(
                    getPendingIntentWithStack(
                        context,
                        HomeActivity::class.java
                    )
                )
                .setOngoing(true)
                .addAction(R.drawable.ic_stop, "Stop", stopPendingIntent)
                .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)
                .setColor(204102153)
                .setColorized(true)

            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(
                CHANNEL_ID_TIMER,
                CHANNEL_NAME_TIMER, true)

            nManager.notify(TIMER_ID, notificationBuilder.build())
        }

        @TargetApi(16)
        fun showTimerPaused(context: Context){
            val resumeIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            resumeIntent.action = AppConstants.ACTION_RESUME
            val resumePendingIntent = PendingIntent.getBroadcast(context,
                0, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val notificationBuilder =
                getBasicNotificationBuilder(
                    context,
                    CHANNEL_ID_TIMER,
                    true
                )
            notificationBuilder.setContentTitle("Timer is paused.")
                .setContentText("Resume?")
                .setContentIntent(
                    getPendingIntentWithStack(
                        context,
                        HomeActivity::class.java
                    )
                )
                .setOngoing(true)
                .addAction(R.drawable.ic_start, "Resume", resumePendingIntent)
                .setColor(204102153)
                .setColorized(true)

            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(
                CHANNEL_ID_TIMER,
                CHANNEL_NAME_TIMER, true)

            nManager.notify(TIMER_ID, notificationBuilder.build())
        }

        fun hideTimerNotification(context: Context){
            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.cancel(TIMER_ID)
        }

        private fun getBasicNotificationBuilder(context: Context, channelId: String, playSound: Boolean)
                : NotificationCompat.Builder{
            val notificationSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_timer)
                .setAutoCancel(true)
                .setDefaults(0)
                .setColor(204102153)
                .setColorized(true)
            notificationBuilder.setColor(20410215)
            if (playSound) notificationBuilder.setSound(notificationSound)
            return notificationBuilder
        }

        @TargetApi(16)
        private fun <T> getPendingIntentWithStack(context: Context, javaClass: Class<T>): PendingIntent{
            val resultIntent = Intent(context, javaClass)
            resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(javaClass)
            stackBuilder.addNextIntent(resultIntent)

            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        @TargetApi(26)
        private fun NotificationManager.createNotificationChannel(channelID: String,
                                                                  channelName: String,
                                                                  playSound: Boolean){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelImportance = if (playSound) NotificationManager.IMPORTANCE_DEFAULT
                else NotificationManager.IMPORTANCE_LOW
                val notificationChannel = NotificationChannel(channelID, channelName, channelImportance)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.BLUE
                this.createNotificationChannel(notificationChannel)
            }
        }
    }
}
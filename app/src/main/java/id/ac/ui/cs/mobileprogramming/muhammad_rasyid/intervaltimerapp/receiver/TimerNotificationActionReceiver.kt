package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.AppConstants
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.HomeActivity
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            AppConstants.ACTION_STOP -> {
                HomeActivity.removeAlarm(context)
                PrefUtil.setTimerState(
                    HomeActivity.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = HomeActivity.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                HomeActivity.removeAlarm(context)
                PrefUtil.setTimerState(
                    HomeActivity.TimerState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = HomeActivity.setAlarm(
                    context,
                    HomeActivity.nowSeconds,
                    secondsRemaining
                )
                PrefUtil.setTimerState(
                    HomeActivity.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = HomeActivity.setAlarm(
                    context,
                    HomeActivity.nowSeconds,
                    secondsRemaining
                )
                PrefUtil.setTimerState(
                    HomeActivity.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}

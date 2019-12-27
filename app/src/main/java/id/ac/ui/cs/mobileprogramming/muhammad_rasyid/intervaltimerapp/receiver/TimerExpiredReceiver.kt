package id.example.intervaltimerapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.example.intervaltimerapp.HomeActivity
import id.example.intervaltimerapp.util.NotificationUtil
import id.example.intervaltimerapp.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(HomeActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
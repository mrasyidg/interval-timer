package com.example.intervaltimerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.intervaltimerapp.util.NotificationUtil
import com.example.intervaltimerapp.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(HomeActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
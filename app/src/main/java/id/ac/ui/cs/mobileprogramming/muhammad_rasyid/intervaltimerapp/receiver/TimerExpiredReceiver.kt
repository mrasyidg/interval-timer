package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.activity.HomeActivity
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(
            HomeActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
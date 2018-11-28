package com.nikitvad.oryanmat.trellowidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.StrictMode
import com.nikitvad.oryanmat.trellowidget.di.DaggerAppComponents
import com.nikitvad.oryanmat.trellowidget.util.TrelloAPIUtil
import com.nikitvad.oryanmat.trellowidget.util.getInterval
import com.nikitvad.oryanmat.trellowidget.old.widget.AlarmReceiver
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import java.util.concurrent.Executors

val T_WIDGET = "TWidget"
private val DEBUG = false

class TrelloMobile : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponents.builder().application(this).build()
    }

    override fun onCreate() {
        if (DEBUG) StrictMode.enableDefaults()
        super.onCreate()


        TrelloAPIUtil.init(applicationContext)
        Executors.callable { scheduleAlarm(this) }.call()


    }

    fun scheduleAlarm(context: Context) {
        val interval = context.getInterval().toLong()
        alarmManager(context).setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME, interval, interval, pendingIntent(context))
    }

    fun alarmManager(context: Context) = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun pendingIntent(context: Context): PendingIntent =
            PendingIntent.getBroadcast(context, 0,
                    Intent(context, AlarmReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT)
}
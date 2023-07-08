package com.semihozmen.appwidget

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi

class SoundReceiver  : BroadcastReceiver () {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, p1: Intent?) {

        val views = RemoteViews(context!!.packageName, R.layout.app_widget)
        val audioManager:AudioManager = context.getSystemService(AudioManager::class.java)
        val appWidgetManager = AppWidgetManager.getInstance(context)


            when(audioManager.ringerMode){
            AudioManager.RINGER_MODE_NORMAL ->{
                audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                views.setTextViewText(R.id.appwidget_text, "Aç")
                appWidgetManager.updateAppWidget(
                    ComponentName(context,AppWidget::class.java),views)
            }

            AudioManager.RINGER_MODE_SILENT ->{
                audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                views.setTextViewText(R.id.appwidget_text, "Kapat")
                appWidgetManager.updateAppWidget(
                    ComponentName(context,AppWidget::class.java),views)
            }

                AudioManager.RINGER_MODE_VIBRATE ->{
                    audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                    views.setTextViewText(R.id.appwidget_text, "Kapat")
                    appWidgetManager.updateAppWidget(
                        ComponentName(context,AppWidget::class.java),views)
                }
        }

    }
}
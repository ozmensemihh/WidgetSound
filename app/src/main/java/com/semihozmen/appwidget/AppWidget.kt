package com.semihozmen.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created

        val appWidgetManager = AppWidgetManager.getInstance(context)

        val views = RemoteViews(context.getPackageName(),
            R.layout.app_widget)

        val audioManager: AudioManager = context.getSystemService(AudioManager::class.java)


        when(audioManager.ringerMode){
            AudioManager.RINGER_MODE_NORMAL ->{
                //views.setTextViewText(R.id.appwidget_text, "Kapat")
                views.setImageViewResource(R.id.imageView,R.drawable.baseline_volume_up_24)
                appWidgetManager.updateAppWidget(
                    ComponentName(context,AppWidget::class.java),views)
            }

            AudioManager.RINGER_MODE_SILENT ->{
                //views.setTextViewText(R.id.appwidget_text, "Aç")
                views.setImageViewResource(R.id.imageView,R.drawable.baseline_volume_off_24)
                appWidgetManager.updateAppWidget(
                    ComponentName(context,AppWidget::class.java),views)
            }

            AudioManager.RINGER_MODE_VIBRATE ->{
                // views.setTextViewText(R.id.appwidget_text, "Aç")
                views.setImageViewResource(R.id.imageView,R.drawable.baseline_volume_off_24)
                appWidgetManager.updateAppWidget(
                    ComponentName(context,AppWidget::class.java),views)
            }
        }
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val receiver = Intent(context, SoundReceiver::class.java)
    receiver.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetId);
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        receiver,
        PendingIntent.FLAG_IMMUTABLE
    )

    val views = RemoteViews(context.getPackageName(),
        R.layout.app_widget)

    val audioManager: AudioManager = context.getSystemService(AudioManager::class.java)


    when(audioManager.ringerMode){
        AudioManager.RINGER_MODE_NORMAL ->{
            //views.setTextViewText(R.id.appwidget_text, "Kapat")
            views.setImageViewResource(R.id.imageView,R.drawable.baseline_volume_off_24)
            appWidgetManager.updateAppWidget(
                ComponentName(context,AppWidget::class.java),views)
        }

        AudioManager.RINGER_MODE_SILENT ->{
            //views.setTextViewText(R.id.appwidget_text, "Aç")
            views.setImageViewResource(R.id.imageView,R.drawable.baseline_volume_off_24)
            appWidgetManager.updateAppWidget(
                ComponentName(context,AppWidget::class.java),views)
        }

        AudioManager.RINGER_MODE_VIBRATE ->{
           // views.setTextViewText(R.id.appwidget_text, "Aç")
            views.setImageViewResource(R.id.imageView,R.drawable.baseline_volume_up_24)
            appWidgetManager.updateAppWidget(
                ComponentName(context,AppWidget::class.java),views)
        }
    }
    views.setOnClickPendingIntent(R.id.imageView, pendingIntent);

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
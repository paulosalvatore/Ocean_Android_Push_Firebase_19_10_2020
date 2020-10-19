package com.oceanbrasil.ocean_android_push_firebase_19_10_2020

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationCreator {

    fun create(context: Context, title: String, body: String) {
        // Obtenção do Serviço de Notificação do Android
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Criação do canal, apenas para API maior ou igual ao Android.OREO

        val channelId = "OCEAN_MAIN"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Ocean - Canal Principal"
            val channelDescription = "Ocean - Canal utilizado para as principais notícias do app."

            // Tentamos obter um canal já criado para o channelId informado
            val channel = notificationManager.getNotificationChannel(channelId)

            // Caso esse canal não exista, criamos e registramos no notification manager
            if (channel == null) {
                val newChannel =
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                newChannel.description = channelDescription
                newChannel.enableVibration(true)
                newChannel.enableLights(true)
                newChannel.vibrationPattern = longArrayOf(300, 400, 500, 400, 300)

                notificationManager.createNotificationChannel(newChannel)
            }
        }

        // Criar e enviar a notificação

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)

        val notification = builder.build()
        val notificationId = 1
        notificationManager.notify(notificationId, notification)
    }
}

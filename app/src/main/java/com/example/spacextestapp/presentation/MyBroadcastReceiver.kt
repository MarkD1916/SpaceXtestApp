package com.example.spacextestapp.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        when(intent?.action) {
            "MyAction" -> {
                val toast = Toast.makeText(context,
                    "My action",
                    Toast.LENGTH_LONG)
                toast.show()
            }


        }


    }
}
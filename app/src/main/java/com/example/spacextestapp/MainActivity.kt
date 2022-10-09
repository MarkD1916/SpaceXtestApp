package com.example.spacextestapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.spacextestapp.databinding.ActivityMainBinding
import com.example.spacextestapp.presentation.MyBroadcastReceiver


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        navController = Navigation.findNavController(this, R.id.fragment_container)

//        val myBroadcastReceiver = MyBroadcastReceiver()
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(myBroadcastReceiver, intentFilter)
        sendBroadcast(Intent(this, MyBroadcastReceiver::class.java).setAction("MyAction"))
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
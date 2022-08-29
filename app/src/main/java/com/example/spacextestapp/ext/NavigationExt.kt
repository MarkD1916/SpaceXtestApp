package com.example.spacextestapp.ext

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.spacextestapp.util.UiEvent

fun Fragment.navigateEvent(event: UiEvent.Navigate){
    findNavController().navigate(event.action, event.args)
}
package com.example.spacextestapp.util

import android.os.Bundle

sealed class UiEvent {
    data class Navigate(val action: Int, val args:Bundle): UiEvent()
    object NavigateUp: UiEvent()
}
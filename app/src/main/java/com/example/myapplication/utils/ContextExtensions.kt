package com.example.myapplication.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Context.getStringResource(id: Int): String {
    return this.getString(id)
}

@Composable
fun getStringResource(id: Int): String {
    val context = LocalContext.current
    return context.getString(id)
}

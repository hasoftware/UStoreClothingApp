package com.hasoftware.ustore.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Context.getStringResource(id: Int): String {
    val localizedContext = LanguageManager.applyLanguage(this)
    return localizedContext.getString(id)
}

@Composable
fun getStringResource(id: Int): String {
    val context = LocalContext.current
    val localizedContext = LanguageManager.applyLanguage(context)
    return localizedContext.getString(id)
}

